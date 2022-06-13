package busqueda;

import java.util.ArrayList;
import java.util.List;


import muestra.Muestra;
import muestra.MuestraI;

public class Buscador{
	private static Buscador instance;
	private List<Muestra> muestras;
	private BusquedaI busqueda;
	
	public Buscador() {
		// TODO Auto-generated constructor stub
	}
	
	public Buscador (BusquedaI busqueda, List<Muestra> muestras) {
		this.muestras=muestras;
		this.busqueda= busqueda;
	}

	public ArrayList<MuestraI> buscarMuestra(BusquedaI busqueda,ArrayList<MuestraI> muestras) {
		return busqueda.buscarMuestra(muestras);
	}

	public static Buscador getInstance() {
		if (instance==null) {
			instance=new Buscador();
		}
		return instance;
	}

}
