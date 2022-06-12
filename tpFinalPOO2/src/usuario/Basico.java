package usuario;

import muestra.MuestraI;
import opinion.Opinion;

public class Basico extends CategoriaUsuario {

	@Override
	protected String opinar(Usuario usuario, MuestraI muestra, Opinion opinion) {
		return usuario.opinarDeFormaBasica(muestra,opinion);
	}

}
