package muestra;

import opinion.Opinion;
import usuario.UsuarioI;

public class NadieOpina extends EstadoMuestra {

	@Override
	protected String registrarOpinionNormal(UsuarioI usuario, Opinion opinion, Muestra muestra) {
		return "No se puede opinar ya que esta muestra esta verificada";
	}

	@Override
	protected String registrarOpinionExperta(UsuarioI usuario, Opinion opinion, Muestra muestra) {
		return "No se puede opinar ya que esta muestra esta verificada";
	}

	@Override
	protected String resultadoActual(Muestra muestra) {
		return "Verificada: " + muestra.resultado(muestra.filtrarOpinionesExpertas());
	}

}
