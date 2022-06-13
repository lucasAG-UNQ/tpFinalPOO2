package organizacion;

import java.util.ArrayList;
import java.util.List;
import muestra.Muestra;
import ubicacion.UbicacionI;

public class ZonaDeCobertura implements IZonaDeCobertura {
	
	private String nombre;
	private UbicacionI epicentro;
	private double radio;
	private List<Muestra> muestras;	
	private List<IZonaListener> listeners;
	
	
	public ZonaDeCobertura(String nombre, UbicacionI epicentro, double radio) {
		super();
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
		this.muestras = new ArrayList<Muestra>();
		this.listeners = new ArrayList<IZonaListener>();  
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public double getRadio() {
	
		return radio;
	}

	@Override
	public UbicacionI getEpicentro() {
		return epicentro;
		
	}
	
	@Override
	public List<Muestra> getMuestras(){
		return this.muestras;
	}
	
	public List<IZonaListener> getListeners(){
		return listeners;
	}
	
	
	public boolean verificarSiMuestraPerteneceAZona(Muestra muestra) {
		
		double xZona = this.epicentro.getLatitud();
		double yZona = this.epicentro.getLongitud();
		double xMuestra = muestra.getUbicacion().getLatitud();
		double yMuestra = muestra.getUbicacion().getLongitud();
		
		return (Math.sqrt(Math.pow((xZona - xMuestra),2) + Math.pow((yZona - yMuestra),2))<= radio);
						
		
	}
	
	@Override
	public List<ZonaDeCobertura> zonasSolapadas(List<ZonaDeCobertura>zonas) {
		
		
		List<ZonaDeCobertura> zonasSolapadas = new ArrayList<ZonaDeCobertura>();
		
		for(ZonaDeCobertura zona : zonas) {
			
			double distancia = zona.getEpicentro().distanciaHasta(this.getEpicentro());
			
			if(distancia < this.getRadio()+zona.getRadio()) {
				zonasSolapadas.add(zona);
			}
		}
		
			return zonasSolapadas;
			
		
	}
	
	@Override
	public void addMuestra(Muestra muestra) {
		if(this.verificarSiMuestraPerteneceAZona(muestra)) {
			muestras.add(muestra);
			this.notificarNuevaMuestra(muestra);
		}
	}
	

	///FUNCIONALIDAD DE OBSERVABLE///
	
	public void removeListener(IZonaListener listener) {
		listeners.remove(listener);
		
	}

	public void addListener(IZonaListener listener) {
		listeners.add(listener);
		
	}
	
	private void notificarNuevaMuestra(Muestra muestra) {
		for(IZonaListener listener : listeners) {
			listener.muestraRegistrada(muestra, this);
		}
	}
	
	@Override
	public void notificarMuestraValidada(Muestra muestra) {
		if(this.verificarSiMuestraPerteneceAZona(muestra)) {
			for(IZonaListener listener : listeners) {
				listener.muestraValidada(muestra, this);
			}
		}
	}
		

}
