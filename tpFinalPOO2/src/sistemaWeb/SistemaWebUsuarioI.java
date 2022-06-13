package sistemaWeb;

import muestra.MuestraI;
import usuario.Usuario;
import usuario.UsuarioI;

public interface SistemaWebUsuarioI {

	String registrarMuestra(MuestraI muestra1);

	Boolean verificar10MuestrasUsuario(UsuarioI usuario);

	String registrarUsuario(UsuarioI usuario);
	
}
