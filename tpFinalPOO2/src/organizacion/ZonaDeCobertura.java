package organizacion;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import muestra.Muestra;
import muestra.UbicacionI;

public class ZonaDeCobertura extends Observable implements IZonaDeCobertura {
	
	private String nombre;
	private UbicacionI epicentro;
	private float radio;
	private List<Muestra> muestras;
	private List<Observer> observers;

}
