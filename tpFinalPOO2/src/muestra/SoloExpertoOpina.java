package muestra;

import opinion.Opinion;
import usuario.UsuarioI;

public class SoloExpertoOpina extends EstadoMuestra{

	@Override
	protected String registrarOpinionNormal(UsuarioI usuario, Opinion opinion, Muestra muestra) {
		// TODO Auto-generated method stub
		return "No puede opinar ya que un experto opino";
	}

	@Override
	protected String registrarOpinionExperta(UsuarioI usuario, Opinion opinion, Muestra muestra) {
		return muestra.vistoBuenoRegistroExperto(usuario,opinion);
	}

	@Override
	protected String resultadoActual(Muestra muestra) {
		// TODO Auto-generated method stub
		return "Solo pueden opinar expertos, estado actual: " + muestra.resultado();
	}

}
