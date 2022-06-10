package organizacion;

import java.util.ArrayList;
import java.util.List;
import muestra.Muestra;
import muestra.UbicacionI;

public class ZonaDeCobertura implements IZonaDeCobertura {
	
	private String nombre;
	private UbicacionI epicentro;
	private float radio;
	private List<Muestra> muestras;	
	private List<IZonaListener> listeners;
	
	
	public ZonaDeCobertura(String nombre, UbicacionI epicentro, float radio) {
		super();
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
		this.muestras = new ArrayList<Muestra>();
		this.listeners = new ArrayList<IZonaListener>();  
	}
	
	
	private void addMuestra(Muestra muestra) {
		muestras.add(muestra);
	}
	
	public List<Muestra> getMuestras(){
		return this.muestras;
	}
	
	public boolean muestraPerteneceAZona(Muestra muestra) {
		//VERIFICAR COMO SABER SI PERTENECE A LA ZONA
		return muestra.getUbicacion()
	}

	///FUNCIONALIDAD DE OBSERVABLE///
	
	public void deleteListener(IZonaListener listener) {
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
