package muestra;

import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Muestra {

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

	public String resultadoActual() {
		return this.opiniones.values().stream()//los valores del mapa repetidos
					.collect(Collectors.groupingBy(e->e, Collectors.counting())) //nuevo mapa con opinion,cantidadDeEsta
					.entrySet()	
					.stream()
					.max(Comparator.comparing(Entry::getValue))
					.get()
					.getKey()
					.toString();
	}

	public void registrarOpinionNormal(UsuarioI usuarioBasicoPepe, Opinion chinchefoliada) {
		// TODO Auto-generated method stub
		
	}


}
