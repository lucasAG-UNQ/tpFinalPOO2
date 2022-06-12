package usuario;

import java.time.LocalDate;
import java.util.HashMap;

import muestra.MuestraI;
import opinion.Opinion;
import sistemaWeb.SistemaWeb;
import sistemaWeb.SistemaWebUsuarioI;

public class Usuario implements UsuarioI{

	private SistemaWebUsuarioI sistemaWeb;
	private CategoriaUsuario categoriaUsuario;
	private HashMap <MuestraI,LocalDate> historialOpinion;
	
	
	public Usuario() {
		this.sistemaWeb= SistemaWeb.getInstance();
		this.categoriaUsuario= new Basico();
		this.historialOpinion= new HashMap <MuestraI,LocalDate> ();
	}
	
	public Usuario(CertificadoExternoI certificado) {
		this();
		this.setCategoriaUsuario(new Especialista());
	}


	protected void setCategoriaUsuario(CategoriaUsuario categoria) {
		this.categoriaUsuario= categoria;
		
	}

	@Override
	public String opinar(MuestraI muestra,Opinion opinion) {
		return this.categoriaUsuario.opinar(this,muestra,opinion);
	}

	@Override
	public String enviarMuestra(MuestraI muestra) {
		return this.sistemaWeb.registrarMuestra(muestra);
	}

	public CategoriaUsuario getCategoriaUsuario() {
		return this.categoriaUsuario;
	}

	public void presentarCertificado(CertificadoExternoI certificado) {
		this.setCategoriaUsuario(new Especialista());
	}

	public void setSistema(SistemaWebUsuarioI sistema) {
		this.sistemaWeb=sistema;
	}

	protected String opinarDeFormaExperta(MuestraI muestra, Opinion opinion) {
		opinion.setEsExperta();
		return muestra.registrarOpinionExperta(this, opinion);
	}

	protected String opinarDeFormaBasica(MuestraI muestra, Opinion opinion) {
		return muestra.registrarOpinionNormal(this, opinion);
	}

	public Boolean cumpleRequisito20Opiniones() {
		return this.historialOpinion.entrySet().stream()
				.filter(e->e.getValue().isAfter(LocalDate.now().minusDays(30)))
				.toList()
				.size()>20;
	}

}
