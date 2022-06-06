package muestra;

import opinion.Opinion;
import usuario.UsuarioI;

public interface MuestraI {

	public String registrarOpinionNormal(UsuarioI usuario, Opinion opinion);
	public String registrarOpinionExperta(UsuarioI usuario, Opinion opinion);
	
}
