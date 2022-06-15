package busqueda;

import java.util.List;

import muestra.Muestra;

public class BOr implements BusquedaCompleta{
	private BusquedaI busqueda1;
	private BusquedaI busqueda2;
	
	public BOr(List<BusquedaI> childBusqueda,BusquedaI busqueda) {
		super(childBusqueda, busqueda);
		
	}
	
	    @Override
	    public void buscarMuestra(List<Muestra> muestras ) {
		
		
		  //this.childBusqueda.forEach();       
		
	}
	
	

}
