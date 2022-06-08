package organizacion;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import muestra.UbicacionI;

public class Organizacion implements OrganizacionI, Observer{
	
	private UbicacionI ubicacion;
	private TipoDeOrganizacion tipo;
	private int cantDeTrabajadores;
	private IFuncionalidadExterna pluginRegistro;
	private IFuncionalidadExterna pluginValidacion;
	private List<ZonaDeCobertura> zonasDeInteres;
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
