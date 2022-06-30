package busqueda;


import java.time.LocalDate;
import java.util.List;
import muestra.Muestra;
import muestra.MuestraI;



public  class BFechaUltimaVotacion implements BusquedaI {

	private LocalDate fechaUltima;
	
	/**
	 * 
	 * Crea una Lista de muestras con la última fecha de votación solicitada
	 * 
	 * @param lista de muestras del tipo Muestra
	 * @param fecha de votacion de una muestra
	 * 
	 */
	public BFechaUltimaVotacion(LocalDate fechaUltima) {
		this.fechaUltima= fechaUltima;
		
		
	}
	
	@Override
	public List<MuestraI> buscarMuestra(List<MuestraI> muestras ) {
		return muestras.stream()
						.filter(m->m.getFechaUltimaOpinion().isAfter(this.fechaUltima))
						.toList();

	}
}
