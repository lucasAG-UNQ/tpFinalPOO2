package muestra;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import opinion.OpinionI;
import sistemaWeb.SistemaWeb;
import ubicacion.UbicacionI;
import usuario.UsuarioI;

public class Muestra implements MuestraI{

	private OpinionI especieFotografiada;
	private UbicacionI ubicacion;
	private UsuarioI fuente;
	private HashMap <UsuarioI,OpinionI> opiniones;
	private LocalDate fechaEnvio;
	private EstadoMuestra estadoMuestra;
	private SistemaWeb sistema;
	
	/**
	 * 
	 * Crea una instancia de Muestra registrada con la fecha de hoy
	 * 
	 * @param especie Una especie de la clase Vinchuca
	 * @param ubicacion Una ubicacion que entienda el protocolo de UbicacionI
	 * @param usuario Un usuario que entienda el protocolo de UsuarioI
	 */
	public Muestra(OpinionI especie, UbicacionI ubicacion, UsuarioI usuario) {
		this.especieFotografiada=especie;
		this.ubicacion=ubicacion;
		this.fuente=usuario;
		this.opiniones= new HashMap<UsuarioI,OpinionI>();
		this.fechaEnvio= LocalDate.now();
		this.estadoMuestra= new TodosOpinan();
		this.sistema=SistemaWeb.getInstance();
		
	}

	/**
	 *
	 * @return Retorna la especie fotografiada
	 */
	public OpinionI getEspecieFotografiada() {
		return especieFotografiada;
	}

	/**
	 *
	 * @return Retorna la ubicacion donde se avisto la muestra
	 */
	public UbicacionI getUbicacion() {
		return ubicacion;
	}

	/**
	 *
	 * @return Retorna el usuario que envio la muestra
	 */
	public UsuarioI getFuente() {
		return fuente;
	}

	/**
	 * 
	 * @return Retorna la fecha en la cual se tomo la muestra
	 */
	public LocalDate getFechaEnvio() {
		return fechaEnvio;
	}

	
	
	/**
	 * 
	 * @return Retorna la fecha en la cual se tomo la muestra
	 */
	public HashMap<UsuarioI, OpinionI> getOpiniones() {
		return opiniones;
	}

	/**
	 * 
	 * Devuelve la opinion mas comun en las opiniones registradas
	 * en case de empate devuelve "No Definido"
	 * @return
	 */
	public String resultadoActual() {
		return this.estadoMuestra.resultadoActual(this);
	}

	
	/**
	 * Registra la opinion, si puede, de un usuario basico
	 * @return Devuelve un string diciendo si se pudo opinar
	 */
	public String registrarOpinionNormal(UsuarioI usuarioBasicoPepe, OpinionI opinion) {
		return this.estadoMuestra.registrarOpinionNormal(usuarioBasicoPepe, opinion, this);
	}

	/**
	 * Registra la opinion, si puede, de un usuario experto
	 * @return Devuelve un string diciendo si se pudo opinar
	 */
	public String registrarOpinionExperta(UsuarioI usuario, OpinionI opinion) {
		return this.estadoMuestra.registrarOpinionExperta(usuario, opinion, this);
	}

	
	/**
	 * Registra una opinion normal
	 * @param usuario
	 * @param opinion
	 * @return Avisa que el registro fue exitoso
	 */
	protected String vistoBuenoRegistroBasico(UsuarioI usuario, OpinionI opinion) {
		String ret= this.usuarioOpino(usuario);
		this.opiniones.putIfAbsent(usuario, opinion);
		return ret;
	}

	/**
	 * 
	 * @param usuario
	 * @return Retorna si el usuario ya opino en esta muestra en forma de string
	 */
	private String usuarioOpino(UsuarioI usuario) {
		String ret= "Opinion registrada correctamente";
		if (this.getOpiniones().containsKey(usuario)) {
			ret="Usted ya opino en esta muestra";
		}
		return ret;
	}

	/**
	 * Registra una opinion experta, y si ya hubo una opinion experta del mismo tipo
	 * cambia el estado de la muestra a verificada
	 * @param usuario
	 * @param opinion
	 * @return Avisa que el registro fue exitoso
	 */
	protected String vistoBuenoRegistroExperto(UsuarioI usuario, OpinionI opinion) {
		
		if (this.filtrarOpinionesExpertas().containsValue(opinion)){
			this.setEstadoMuestra(new NadieOpina());
			this.sistema.muestraVerificada(this);
		}
		String ret= this.usuarioOpino(usuario);
		this.opiniones.putIfAbsent(usuario, opinion);

		return ret;
	}

	/**
	 * Camia el estado de la muestra al estado pasado por parametro
	 * @param estado
	 */
	protected void setEstadoMuestra(EstadoMuestra estado) {
		this.estadoMuestra= estado;
	}

	/**
	 * 
	 * @return Devuelve el resultado actual de la muestra, o sea la opinion que mas se
	 * envio, en caso de empate retorna "No Definido"
	 */
	protected String resultado(Map<UsuarioI, OpinionI> map) {
		Set<Entry<OpinionI, Long>> maxValues=  map.values().stream()//los valores del mapa repetidos
							.collect(Collectors.groupingBy(e->e, Collectors.counting())) //nuevo mapa con opinion,cantidadDeEsta
							.entrySet(); //mapa en set de entry(k,v)
		
		Entry<OpinionI, Long> max= maxValues
									.stream()
									.max(Map.Entry.comparingByValue())
									.get(); //entrada que mas se repite	
		
		maxValues= maxValues.stream()
					.filter(e->e.getValue()== max.getValue())
					.collect(Collectors.toSet()); //entradas filtradas con el valor que mas se repite
		
		String res="";
		
		if (maxValues.size()==1) { 	//Si hay 1 unico valor significa que hay un unico valor maximo
			res= max.getKey().toString();
		} else {
			res= "No Definido";
		}
		
		return res;
	}

	
	protected Map<UsuarioI, OpinionI> filtrarOpinionesExpertas() {
		Map<UsuarioI, OpinionI> res =this.opiniones.entrySet()
									.stream()
									.filter(e->e.getValue().esOpinionExperta())
									.collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
		
		return res;
	}

	@Override
	public Boolean estaVerificada() {
		return this.estadoMuestra.estaVerificada();
	}

	@Override
	public Double distanciaHasta(MuestraI muestra) {
		return this.ubicacion.distanciaHasta(muestra.getUbicacion());
	}

	public void setSistema(SistemaWeb sistema) {
		this.sistema=sistema;
	}

	@Override
	public LocalDate getFechaUltimaOpinion() {
		return this.opiniones.values().stream()
										.map(o->o.getFechaOpinion())
										.max(LocalDate::compareTo)
										.get();
	}


}
