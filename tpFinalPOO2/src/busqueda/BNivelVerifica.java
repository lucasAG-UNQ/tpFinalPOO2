package busqueda;

import java.util.List;
import java.util.stream.Collectors;
import muestra.EstadoMuestra;
import muestra.Muestra;

public  class BNivelVerifica implements BusquedaI {
	
	private List<Muestra> muestras;
	private EstadoMuestra estado;
	
	/**
	 * 
	 * Crea una Lista de muestras con el estado solicitado
	 * 
	 * @param lista de muestras del tipo Muestra
	 * @param estado de una muestra
	 * 
	 */
	
	public BNivelVerifica(List<Muestra> muestras,EstadoMuestra estado) {
		
		this.muestras= muestras;
		this.estado= estado;
	}
	
	
	public List<Muestra> getMuestras() {
		return muestras;
	}


	public void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}
	
	

	public EstadoMuestra getEstado() {
		return estado;
	}


	public void setEstado(EstadoMuestra estado) {
		this.estado = estado;
	}
	
	/**
	 * 
	 * se sobreescribe el método y se consulta si el estado solicitado de la busqueda es "estaVerificada" o sea true,
	 *  hace una lista con todos los estados de las muestras que estan verificados. En caso contrario hace otra lista con los demás
	 * casos que serían los votados.
	 * 
	 */

	@Override
	public void buscarMuestra(List<Muestra> muestras ) {
		
		if (estado.estaVerificada())
			this.muestras.stream().filter(m -> m.getEstadoMuestra().estaVerificada().equals(true)).collect(Collectors.toList());
		else
			this.muestras.stream().collect(Collectors.toList());
			
	
	};

}
