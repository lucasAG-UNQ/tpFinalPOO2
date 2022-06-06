package usuario;

import java.util.HashMap;

import muestra.Muestra;
import muestra.MuestraI;
import opinion.Opinion;
import sistemaWeb.SistemaWebUsuarioI;

public class Usuario implements UsuarioI{

	private SistemaWebUsuarioI sistemaWeb;
	private CategoriaUsuario categoriaUsuario;
	private HashMap <Muestra,Opinion> historialOpinion;
	
	
	public Usuario() {
		this.sistemaWeb= SistemaWebUsuarioI.getInstance();
		this.categoriaUsuario= new Basico();
		this.historialOpinion= new HashMap <Muestra,Opinion> ();
	}
	
	public Usuario(CertificadoExternoI certificado) {
		
	}


	@Override
	public String opinar(MuestraI muestra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enviarMuestra(MuestraI muestra) {
		// TODO Auto-generated method stub
		return null;
	}

}
