package muestra;

import opinion.Opinion;
import usuario.UsuarioI;

public class TodosOpinan extends EstadoMuestra {

	@Override
	protected String registrarOpinionNormal(UsuarioI usuario, Opinion opinion, Muestra muestra) {
		return muestra.vistoBuenoRegistroBasico(usuario, opinion);
	}

	@Override
	protected String registrarOpinionExperta(UsuarioI usuario, Opinion opinion, Muestra muestra) {
		muestra.setEstadoMuestra(new SoloExpertoOpina());
		muestra.reiniciarOpiniones();
		return muestra.vistoBuenoRegistroExperto(usuario, opinion);
	}

	@Override
	protected String resultadoActual(Muestra muestra) {
		return "Estado actual: " + muestra.resultado();
		// TODO Auto-generated method stub

	}

}
