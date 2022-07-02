package busqueda;

import java.util.List;
import muestra.EstadoMuestra;
import muestra.MuestraI;

public  class BNivelVerifica implements BusquedaI {
	private String estado;
	
	/**
	 * 
	 * Crea una Lista de muestras con el estado solicitado
	 * 
	 * @param lista de muestras del tipo Muestra
	 * @param estado String que puede si es "verificado" se filtraran las muestras 
	 * verificadas, caso contrario se filtraran las demas.
	 * 
	 */
	
	public BNivelVerifica(String estado) {
		this.estado= estado;
	}
	
	/**
	 * 
	 * se sobreescribe el método y se consulta si el estado solicitado de la busqueda es "estaVerificada" o sea true,
	 *  hace una lista con todos los estados de las muestras que estan verificados. En caso contrario hace otra lista con los demás
	 * casos que serían los votados.
	 */

	@Override
	public List<MuestraI> buscarMuestra(List<MuestraI> muestras ) {
		boolean compare= this.estado=="verificada";
		
		return muestras.stream()
						.filter(m->m.estaVerificada()==compare)
						.toList();
	}
}
