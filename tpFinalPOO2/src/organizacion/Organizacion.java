package organizacion;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import muestra.Muestra;
import ubicacion.UbicacionI;

public class Organizacion implements IOrganizacion, IZonaListener{
	
	private UbicacionI ubicacion;
	private TipoDeOrganizacion tipo;
	private int cantDeTrabajadores;
	private IFuncionalidadExterna pluginRegistro;
	private IFuncionalidadExterna pluginValidacion;
	private List<ZonaDeCobertura> zonasDeInteres;
	
	
	public Organizacion(UbicacionI ubicacion, TipoDeOrganizacion tipo, int cantDeTrabajadores,
			IFuncionalidadExterna pluginRegistro, IFuncionalidadExterna pluginValidacion,
			List<ZonaDeCobertura> zonasDeInteres) {
		super();
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.cantDeTrabajadores = cantDeTrabajadores;
		this.pluginRegistro = pluginRegistro;
		this.pluginValidacion = pluginValidacion;
		this.zonasDeInteres = zonasDeInteres;
		this.suscribirZonas();
		
	}


	private void suscribirZonas() {
		for(ZonaDeCobertura zona : zonasDeInteres) {
			zona.addListener(this);
		}
	}
	
	private void agregarZonaDeInteres(ZonaDeCobertura zona) {
		zonasDeInteres.add(zona);
		zona.addListener(this);
	}
	
	private void quitarZonaDeInteres(ZonaDeCobertura zona) {
		zonasDeInteres.remove(zona);
		zona.removeListener(this);;
	}


	@Override
	public void muestraRegistrada(Muestra muestra, ZonaDeCobertura zona) {
		this.pluginRegistro.nuevoEvento(this, zona , muestra);
	}


	@Override
	public void muestraValidada(Muestra muestra, ZonaDeCobertura zona) {
		this.pluginValidacion.nuevoEvento(this, zona, muestra);
	}
	
	
	
	
}
