package muestra;

import opinion.OpinionI;
import usuario.UsuarioI;

public class NadieOpina extends EstadoMuestra {

	@Override
	protected String registrarOpinionNormal(UsuarioI usuario, OpinionI opinion, Muestra muestra) {
		return "No se puede opinar ya que esta muestra esta verificada";
	}

	@Override
	protected String registrarOpinionExperta(UsuarioI usuario, OpinionI opinion, Muestra muestra) {
		return "No se puede opinar ya que esta muestra esta verificada";
	}

	@Override
	protected String resultadoActual(Muestra muestra) {
		return "Verificada: " + muestra.resultado(muestra.filtrarOpinionesExpertas());
	}

	@Override
	public Boolean estaVerificada() {
		return true;
	}

}
