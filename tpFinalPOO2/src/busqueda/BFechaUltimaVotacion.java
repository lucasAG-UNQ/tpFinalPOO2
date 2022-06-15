package busqueda;


import java.time.LocalDate;
import java.util.List;
import muestra.Muestra;



public  class BFechaUltimaVotacion implements BusquedaI {

	private List<Muestra> muestras;
	private LocalDate fechaUltima;
	private List<Muestra> muestras;
	
	/**
	 * 
	 * Crea una Lista de muestras con la última fecha de votación solicitada
	 * 
	 * @param lista de muestras del tipo Muestra
	 * @param fecha de votacion de una muestra
	 * 
	 */
	public BFechaUltimaVotacion(List<Muestra> muestras, LocalDate fechaUltima) {
		this.muestras= muestras;
		this.fechaUltima= fechaUltima;
		
		
	}
	
	public List<Muestra> getMuestras() {
		return muestras;
	}

    
	public void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}

	

	@Override
	public void buscarMuestra(List<Muestra> muestras ) {
		this.muestras.stream().map(m -> m.getFechaEnvio()).max(LocalDate :: compareTo).get(); 
				


		};
}
