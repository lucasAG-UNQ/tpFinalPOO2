package busqueda;

import java.util.ArrayList;
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
		//se copian los resultados de las busquedas debido a una excepcion
		
		List<MuestraI>res= new ArrayList<MuestraI> (busqueda1.buscarMuestra(muestras));
		List<MuestraI>res2= new ArrayList<MuestraI> (busqueda2.buscarMuestra(muestras));
		
		res.addAll(res2);
		return res;
	}
	
	

}
