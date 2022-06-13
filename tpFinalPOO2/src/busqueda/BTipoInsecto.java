package busqueda;

import java.util.List;
import java.util.stream.Collectors;
import muestra.Muestra;
import opinion.Opinion;


public  class BTipoInsecto implements BusquedaI {
	private List<Muestra> muestras;
	private Opinion tipo;
	
	public BTipoInsecto(List<Muestra> muestras,Opinion tipo ){
		this.muestras=muestras;
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
	public void buscarMuestra(List<Muestra> muestras ) {
		this.muestras.stream().filter(m -> m.getEspecieFotografiada().equals(tipo)).collect(Collectors.toList());
	}

	public List<Muestra> getMuestras() {
		return muestras;
	}

	public void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}

	public Opinion getTipo() {
		return tipo;
	}

	public void setTipo(Opinion tipo) {
		this.tipo = tipo;
	};

}
