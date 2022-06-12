package usuario;

import muestra.MuestraI;
import opinion.Opinion;

public interface UsuarioI {

	public String opinar(MuestraI muestra, Opinion opinion);
	public String enviarMuestra(MuestraI muestra);
	
	
}
