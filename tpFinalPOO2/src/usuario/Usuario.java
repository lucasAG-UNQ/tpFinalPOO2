package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import muestra.MuestraI;
import opinion.Opinion;
import opinion.OpinionI;
import sistemaWeb.SistemaWeb;
import sistemaWeb.SistemaWebUsuarioI;

public class Usuario implements UsuarioI{

	private SistemaWebUsuarioI sistemaWeb;
	private CategoriaUsuario categoriaUsuario;
	private List<OpinionI> historialOpinion;
	
	
	public Usuario() {
		this.sistemaWeb= SistemaWeb.getInstance();
		this.categoriaUsuario= new Basico();
		this.historialOpinion= new ArrayList <OpinionI> ();
		sistemaWeb.registrarUsuario(this);
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
		return this.historialOpinion.stream()
				.filter(o->o.getFechaOpinion().isAfter(LocalDate.now().minusDays(31)))
				.toList()
				.size()>=20;
	}

	
	/**
	 * Metodo creado con el fin de testear
	 * @param opiniones20en30Dias
	 */
	public void setHistorialOpinion(List<OpinionI> opiniones20en30Dias) {
		this.historialOpinion=opiniones20en30Dias;
	}

	public void verificarCategoria() {
		Boolean cond= 	this.cumpleRequisito20Opiniones() &&
						this.sistemaWeb.verificar10MuestrasUsuario(this);
		
		this.categoriaUsuario.verificarCategoria(this,cond);
	} 
	
}
