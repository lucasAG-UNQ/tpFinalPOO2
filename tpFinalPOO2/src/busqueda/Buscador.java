package busqueda;

import java.util.List;


import muestra.Muestra;

public class Buscador implements BusquedaI{
	private List<Muestra> muestras;
	private BusquedaI busqueda;
	
	public Buscador (BusquedaI busqueda, List<Muestra> muestras) {
		this.muestras=muestras;
		this.busqueda= busqueda;
	}
	
	@Override
	public void buscarMuestra(List<Muestra> muestras) {
		
	}

}
