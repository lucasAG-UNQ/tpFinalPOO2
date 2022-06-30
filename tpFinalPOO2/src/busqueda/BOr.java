package busqueda;

import java.util.List;

import muestra.MuestraI;

public class BOr implements BusquedaI{
	
	private BusquedaI busqueda1;
	private BusquedaI busqueda2;
	
	public BOr(BusquedaI busqueda1,BusquedaI busqueda2) {
		this.busqueda1=busqueda1;
		this.busqueda2=busqueda2;
	}
	@Override
	public List<MuestraI> buscarMuestra(List<MuestraI> muestras ) {
		List<MuestraI>res= busqueda1.buscarMuestra(muestras);
		res.addAll(busqueda2.buscarMuestra(muestras));
		return res;
	}
	
	

}
