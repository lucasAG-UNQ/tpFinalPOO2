package busqueda;

import java.util.List;
import muestra.Muestra;

public  class BusquedaCompleta implements BusquedaI {

	private List<BusquedaI> childBusqueda;
	private BusquedaI busqueda;
	private boolean logOr;
	private boolean logAnd;
	
	
	public BusquedaCompleta(List<BusquedaI> childBusqueda,BusquedaI busqueda ) {
		this.busqueda= busqueda;
		this.childBusqueda= childBusqueda;
	}
	
			
	public List<BusquedaI> getChildBusqueda() {
		return childBusqueda;
	}


	public void setChildBusqueda(List<BusquedaI> childBusqueda) {
		this.childBusqueda = childBusqueda;
	}


	public BusquedaI getBusqueda() {
		return busqueda;
	}


	public void setBusqueda(BusquedaI busqueda) {
		this.busqueda = busqueda;
	}
	

	@Override
	public void buscarMuestra(List<Muestra> muestras ) {
		
		
		//this.childBusqueda.forEach(childBusqueda:: busqueda);
		
		
		
	}
	
	
	public void addBusqueda(BusquedaI busqueda) {
		childBusqueda.add(busqueda);
	}
	
	public void removeBusqueda(BusquedaI busqueda) {
		childBusqueda.remove(busqueda);
	}
}
