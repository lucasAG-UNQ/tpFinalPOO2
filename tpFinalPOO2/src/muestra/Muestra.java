package muestra;

import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import opinion.Opinion;
import opinion.Vinchuca;
import ubicacion.UbicacionI;
import usuario.UsuarioI;

public class Muestra implements MuestraI{

	private Vinchuca especieFotografiada;
	private UbicacionI ubicacion;
	private UsuarioI fuente;
	private HashMap <UsuarioI,Opinion> opiniones;
	private Calendar fechaEnvio;
	private EstadoMuestra estadoMuestra;
	
	/**
	 * 
	 * Crea una instancia de Muestra registrada con la fecha de hoy
	 * 
	 * @param especie Una especie de la clase Vinchuca
	 * @param ubicacion Una ubicacion que entienda el protocolo de UbicacionI
	 * @param usuario Un usuario que entienda el protocolo de UsuarioI
	 */
	public Muestra(Vinchuca especie, UbicacionI ubicacion, UsuarioI usuario) {
		this.especieFotografiada=especie;
		this.ubicacion=ubicacion;
		this.fuente=usuario;
		this.opiniones= new HashMap<UsuarioI,Opinion>();
		this.fechaEnvio= Calendar.getInstance();
		this.estadoMuestra= new TodosOpinan();
		this.opiniones.put(usuario, especie);
	}

	public Vinchuca getEspecieFotografiada() {
		return especieFotografiada;
	}

	public UbicacionI getUbicacion() {
		return ubicacion;
	}

	public UsuarioI getFuente() {
		return fuente;
	}

	public Calendar getFechaEnvio() {
		return fechaEnvio;
	}

	
	/**
	 * 
	 * Devuelve la opinion mas comun en las opiniones registradas
	 * @return
	 */
	
	
	public String resultadoActual() {
		return this.estadoMuestra.resultadoActual(this);
	}

	public String registrarOpinionNormal(UsuarioI usuarioBasicoPepe, Opinion opinion) {
		return this.estadoMuestra.registrarOpinionNormal(usuarioBasicoPepe, opinion, this);
	}

	public String registrarOpinionExperta(UsuarioI usuario, Opinion opinion) {
		return this.estadoMuestra.registrarOpinionExperta(usuario, opinion, this);
	}

	public String vistoBuenoRegistroBasico(UsuarioI usuario, Opinion opinion) {
		this.opiniones.put(usuario, opinion);
		return "Opinion registrada correctamente";
	}

	public String vistoBuenoRegistroExperto(UsuarioI usuario, Opinion opinion) {
		
		if (this.opiniones.containsValue(opinion)){
			this.opiniones.put(usuario, opinion);
			this.setEstadoMuestra(new NadieOpina());
		}else {
			this.opiniones.put(usuario, opinion);
		}

		return "Opinion registrada correctamente";
	}

	public void setEstadoMuestra(EstadoMuestra estado) {
		this.estadoMuestra= estado;
	}

	/**
	 * Devuelve el resultado actual de la muestra, o sea la opinion que mas se
	 * envio, en caso de empate retorna "No Definido"
	 * @return
	 */
	public String resultado() {
		Set<Entry<Opinion, Long>> maxValues=  this.opiniones.values().stream()//los valores del mapa repetidos
							.collect(Collectors.groupingBy(e->e, Collectors.counting())) //nuevo mapa con opinion,cantidadDeEsta
							.entrySet(); //mapa en set de entry(k,v)
		
		Entry<Opinion, Long> max= maxValues.stream().max(Map.Entry.comparingByValue()).get(); //entrada que mas se repite	
		maxValues.stream().filter(e->e.getValue()== max.getValue()); //entradas filtradas con el valor que mas se repite
		String res="";
		
		if (maxValues.size()==1) { 	//Si hay 1 unico valor significa que hay un unico valor maximo
			res= max.getKey().toString();
		} else {
			res= "No Definido";
		}
		
		return res;
	}

	public void reiniciarOpiniones() {
		this.opiniones.clear();
	}


}
