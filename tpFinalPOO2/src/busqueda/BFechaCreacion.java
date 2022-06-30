package busqueda;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import muestra.Muestra;
import muestra.MuestraI;


public class BFechaCreacion implements BusquedaI {
	private LocalDate fechaCreacion;
	
	/**
	 * 
	 * Crea una Lista de muestras con la fecha de creación solicitada
	 * 
	 * @param lista de muestras del tipo Muestra
	 * @param fecha de creación de una muestra
	 * 
	 */
	
	
	public BFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion= fechaCreacion;
	}
	
	@Override
	public List<MuestraI> buscarMuestra(List<MuestraI> muestras) {
	    return muestras.stream()
	    				.filter(m -> m.getFechaEnvio().equals(fechaCreacion))
	    				.toList();
		
	}
}
