package busqueda;

import java.util.List;

import muestra.MuestraI;

public class BAnd implements BusquedaI{
	
	private BusquedaI busqueda1;
	private BusquedaI busqueda2;
	
	public BAnd(BusquedaI busqueda1, BusquedaI busqueda2) {
		this.busqueda1=busqueda1;
		this.busqueda2=busqueda2;
	}
	
	@Override
	public List<MuestraI> buscarMuestra(List<MuestraI> muestras ) {
		List<MuestraI>res= busqueda1.buscarMuestra(muestras);
		res.retainAll(busqueda2.buscarMuestra(muestras));
		return res;
	}
	
	

}
