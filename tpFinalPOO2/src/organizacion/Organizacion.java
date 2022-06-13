package organizacion;

import java.util.ArrayList;
import java.util.List;


import muestra.MuestraI;
import ubicacion.UbicacionI;

public class Organizacion implements IOrganizacion, IZonaListener{
	
	private UbicacionI ubicacion;
	private TipoDeOrganizacion tipo;
	private int cantDeIntegrantes;
	private IFuncionalidadExterna pluginRegistro;
	private IFuncionalidadExterna pluginValidacion;
	private List<ZonaDeCobertura> zonasDeInteres;
	
	
	public Organizacion(UbicacionI ubicacion, TipoDeOrganizacion tipo, int cantDeTrabajadores,
			IFuncionalidadExterna pluginRegistro, IFuncionalidadExterna pluginValidacion) {
		super();
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.cantDeIntegrantes = cantDeTrabajadores;
		this.pluginRegistro = pluginRegistro;
		this.pluginValidacion = pluginValidacion;
		this.zonasDeInteres = new ArrayList<ZonaDeCobertura>();
		
	}
	
	@Override
	public int getCantDeIntegrantes() {
		return cantDeIntegrantes;
	}
	
	@Override
    public TipoDeOrganizacion getTipoDeOrganizacion() {
    	return tipo;
    	
    }
    
	@Override 
    public String direccionDeOrganizacion() {
		return "Latitud: "+ this.ubicacion.getLatitud() +"°" + " - " + "Longitud: " + this.ubicacion.getLongitud()+"°";
	}
    
	public void agregarZonaDeInteres(ZonaDeCobertura zona) {
		zonasDeInteres.add(zona);
		zona.addListener(this);
	}
	
	public void quitarZonaDeInteres(ZonaDeCobertura zona) {
		zonasDeInteres.remove(zona);
		zona.removeListener(this);;
	}


	@Override
	public void muestraRegistrada(MuestraI muestra, ZonaDeCobertura zona) {
		this.pluginRegistro.nuevoEvento(this, zona , muestra);
	}


	@Override
	public void muestraValidada(MuestraI muestra, ZonaDeCobertura zona) {
		this.pluginValidacion.nuevoEvento(this, zona, muestra);
	}


	
	
	
	
	
}
