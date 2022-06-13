package busqueda;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import muestra.Muestra;


public class BFechaCreacion implements BusquedaI {
	private LocalDate fechaCreacion;
	private List<Muestra> muestras;
	
	/**
	 * 
	 * Crea una Lista de muestras con la fecha de creación solicitada
	 * 
	 * @param lista de muestras del tipo Muestra
	 * @param fecha de creación de una muestra
	 * 
	 */
	
	
	public BFechaCreacion(List<Muestra> muestras , LocalDate fechaCreacion) {
		this.fechaCreacion= fechaCreacion;
		this.muestras= muestras;
			
	}
		
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
    

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	

	@Override
	public void buscarMuestra(List<Muestra> muestras) {
		
	    this.muestras.stream().filter(m -> m.getFechaEnvio().equals(fechaCreacion)).collect(Collectors.toList());
		
	}
}
