package muestra;

import opinion.OpinionI;
import usuario.UsuarioI;

public abstract class EstadoMuestra {

	protected abstract String registrarOpinionNormal(UsuarioI usuario, OpinionI opinion, Muestra muestra);
	protected abstract String registrarOpinionExperta(UsuarioI usuario, OpinionI opinion, Muestra muestra);
	protected abstract String resultadoActual(Muestra muestra);
	protected abstract Boolean estaVerificada();
	
}
