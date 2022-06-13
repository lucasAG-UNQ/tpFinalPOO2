package organizacion;

import java.util.ArrayList;
import java.util.List;
import muestra.MuestraI;
import ubicacion.UbicacionI;

public class ZonaDeCobertura implements IZonaDeCobertura {
	
	private String nombre;
	private UbicacionI epicentro;
	private double radio;
	private List<MuestraI> muestras;	
	private List<IZonaListener> listeners;
	
	
	public ZonaDeCobertura(String nombre, UbicacionI epicentro, double radio) {
		super();
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
		this.muestras = new ArrayList<MuestraI>();
		this.listeners = new ArrayList<IZonaListener>();  
	}
	
	public String getNombre() {
		return nombre;
	}
	

	public double getRadio() {
	
		return radio;
	}


	public UbicacionI getEpicentro() {
		return epicentro;
		
	}
	
	public List<IZonaListener> getListeners(){
		return listeners;
	}
	
	
	public void addMuestra(MuestraI muestra) {
		if(this.verificarSiMuestraPerteneceAZona(muestra)) {
			muestras.add(muestra);
			this.notificarNuevaMuestra(muestra);
		}
	}
	
	public void muestraVerificada(MuestraI muestra) {
		this.notificarMuestraValidada(muestra);
	}
	
	public List<MuestraI> getMuestras(){
		return this.muestras;
	}
	
	public boolean verificarSiMuestraPerteneceAZona(MuestraI muestra) {
		
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
	
	private void notificarNuevaMuestra(MuestraI muestra) {
		for(IZonaListener listener : listeners) {
			listener.muestraRegistrada(muestra, this);
		}
	}
		
	public void notificarMuestraValidada(MuestraI muestra) {
			for(IZonaListener listener : listeners) {
				listener.muestraValidada(muestra, this);
			}
	}
		

}
