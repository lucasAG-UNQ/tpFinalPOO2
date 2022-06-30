package busqueda;

import java.util.List;
import java.util.stream.Collectors;
import muestra.Muestra;
import muestra.MuestraI;
import opinion.OpinionI;


public  class BTipoInsecto implements BusquedaI{
	private OpinionI tipo;
	
	public BTipoInsecto(OpinionI tipo ){
		this.tipo=tipo;
	}
	
	/**
	 * 
	 * Crea una Lista de muestras con el tipo de especie solicitado
	 * 
	 * @param lista de muestras del tipo Muestra
	 * @param tipo de insecto segun la opinión
	 * @return 
	 * 
	 */
	
	@Override
	public List<MuestraI> buscarMuestra(List<MuestraI> muestras ) {
		return muestras.stream()
						.filter(m -> m.getEspecieFotografiada().equals(tipo))
						.toList();
	}
	
}
