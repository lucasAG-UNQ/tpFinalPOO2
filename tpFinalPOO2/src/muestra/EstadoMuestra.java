package muestra;

import opinion.Opinion;
import usuario.UsuarioI;

public abstract class EstadoMuestra {

	protected abstract String registrarOpinionNormal(UsuarioI usuario, Opinion opinion, Muestra muestra);
	protected abstract String registrarOpinionExperta(UsuarioI usuario, Opinion opinion, Muestra muestra);
	protected abstract String resultadoActual(Muestra muestra);
	
}
