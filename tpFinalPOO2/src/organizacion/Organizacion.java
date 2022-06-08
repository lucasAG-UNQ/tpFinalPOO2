package organizacion;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import muestra.UbicacionI;

public class Organizacion implements IOrganizacion, Observer{
	
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
	}






	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
