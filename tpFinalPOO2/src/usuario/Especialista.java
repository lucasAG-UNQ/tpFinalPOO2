package usuario;

import muestra.MuestraI;
import opinion.Opinion;

public class Especialista extends CategoriaUsuario {

	@Override
	protected String opinar(Usuario usuario, MuestraI muestra, Opinion opinion) {
		return usuario.opinarDeFormaExperta(muestra, opinion);
	}

}
