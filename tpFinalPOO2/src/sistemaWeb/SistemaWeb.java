package sistemaWeb;

import muestra.MuestraI;
import usuario.Usuario;

public class SistemaWeb implements SistemaWebUsuarioI{

	private static SistemaWeb sistema;
	
	
	public static SistemaWebUsuarioI getInstance() {
		if (sistema==null) {
			sistema= new SistemaWeb();
		}
		
		return sistema;
	}


	@Override
	public Boolean verificar10MuestrasUsuario(Usuario usuarioBasicoPepe) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String registrarMuestra(MuestraI muestra1) {
		// TODO Auto-generated method stub
		return null;
	}

}
