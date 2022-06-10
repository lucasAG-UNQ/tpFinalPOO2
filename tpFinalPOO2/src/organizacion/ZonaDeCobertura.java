package organizacion;

import java.util.ArrayList;
import java.util.List;
import muestra.Muestra;
import muestra.UbicacionI;

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
	

	public double getRadio() {
	
		return radio;
	}


	public UbicacionI getEpicentro() {
		return epicentro;
		
	}
	
	
	private void addMuestra(Muestra muestra) {
		muestras.add(muestra);
	}
	
	public List<Muestra> getMuestras(){
		return this.muestras;
	}
	
	public boolean verificarSiMuestraPerteneceAZona(Muestra muestra) {
		
		double xZona = this.epicentro.getLatitud();
		double yZona = this.epicentro.getLongitud();
		double xMuestra = muestra.getUbicacion().getLatitud();
		double yMuestra = muestra.getUbicacion().getLongitud();
		
		return (Math.sqrt(Math.pow((xZona - xMuestra),2) + Math.pow((yZona - yMuestra),2))<= radio);
					 
		
		
	}
	
	public List<ZonaDeCobertura> zonasSolapadas(List<ZonaDeCobertura>zonas) {
		
		List<ZonaDeCobertura> zonasSolapadas = new ArrayList<ZonaDeCobertura>();
		
		for(ZonaDeCobertura zona : zonas) {
			if(this.getEpicentro().distanciaHasta(zona.getEpicentro()) < this.getRadio()+zona.getRadio()) {
				zonasSolapadas.add(zona);
			}
		}
		
			return zonasSolapadas;
			
		
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
		
	private void notificarMuestraValidada(Muestra muestra) {
			for(IZonaListener listener : listeners) {
				listener.muestraValidada(muestra, this);
			}
	}
		

}
