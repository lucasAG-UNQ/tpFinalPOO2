package usuario;

import java.util.HashMap;

import muestra.Muestra;
import muestra.MuestraI;
import opinion.Opinion;
import sistemaWeb.SistemaWeb;
import sistemaWeb.SistemaWebUsuarioI;

public class Usuario implements UsuarioI{

	private SistemaWebUsuarioI sistemaWeb;
	private CategoriaUsuario categoriaUsuario;
	private HashMap <Muestra,Opinion> historialOpinion;
	
	
	public Usuario() {
		this.sistemaWeb= SistemaWeb.getInstance();
		this.categoriaUsuario= new Basico();
		this.historialOpinion= new HashMap <Muestra,Opinion> ();
	}
	
	public Usuario(CertificadoExternoI certificado) {
		this();
		this.setCategoriaUsuario(new Especialista());
	}


	private void setCategoriaUsuario(CategoriaUsuario categoria) {
		this.categoriaUsuario= categoria;
		
	}

	@Override
	public String opinar(MuestraI muestra,Opinion opinion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enviarMuestra(MuestraI muestra) {
		// TODO Auto-generated method stub
		return null;
	}

	public CategoriaUsuario getCategoriaUsuario() {
		return this.categoriaUsuario;
	}

	public void presentarCertificado(CertificadoExternoI certificado) {
		this.setCategoriaUsuario(new Especialista());
	}

}
