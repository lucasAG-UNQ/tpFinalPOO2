package usuario;

import muestra.MuestraI;
import opinion.Opinion;

public abstract class CategoriaUsuario {

	protected abstract String opinar(Usuario usuario, MuestraI muestra, Opinion opinion);

	protected abstract void verificarCategoria(Usuario usuario, Boolean cond);

}
