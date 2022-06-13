package busqueda;

import java.util.List;

import muestra.Muestra;

public class BAnd extends BusquedaCompleta{
	private BusquedaI busqueda1;
	private BusquedaI busqueda2;
	
	public BAnd(List<BusquedaI> childBusqueda,BusquedaI busqueda) {
		super(childBusqueda, busqueda);
		
	}
	
	    @Override
	    public void buscarMuestra(List<Muestra> muestras ) {
		
		
		  //this.childBusqueda.forEach();       
		
	}
	
	

}
