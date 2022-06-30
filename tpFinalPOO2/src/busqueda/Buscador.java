package busqueda;

import java.util.ArrayList;
import java.util.List;


import muestra.Muestra;
import muestra.MuestraI;

public class Buscador{
	private static Buscador instance;
	private List<MuestraI> muestras;
	
	public Buscador() {
		this.muestras= new ArrayList<MuestraI>();
	}

	public List<MuestraI> buscarMuestra(BusquedaI busqueda,List<MuestraI> muestras) {
		return busqueda.buscarMuestra(muestras);
	}

	public static Buscador getInstance() {
		if (instance==null) {
			instance=new Buscador();
		}
		return instance;
	}

}
