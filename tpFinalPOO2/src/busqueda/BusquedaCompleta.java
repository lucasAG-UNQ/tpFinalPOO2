package busqueda;

public class BusquedaCompleta implements BusquedaI {

	private List<Busqueda> childBusqueda;
	
	
	public BusquedaCompleta() {
		
	}
	
		
	public void buscarMuestra(Muestra muestra) {
		
		childBusqueda.forEach (Busqueda::buscarMuestra(Muestra muestra));
		
	}
	
	public void addBusqueda(Busqueda busqueda) {
		childBusqueda.add(busqueda);
	}
	
	public void removeBusqueda(Busqueda busqueda) {
		childBusqueda.remove(busqueda);
	}
}
