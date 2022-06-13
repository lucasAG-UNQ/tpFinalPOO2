package muestra;

import opinion.Opinion;
import usuario.UsuarioI;
import ubicacion.UbicacionI;

public interface MuestraI {

	public String registrarOpinionNormal(UsuarioI usuario, Opinion opinion);
	public String registrarOpinionExperta(UsuarioI usuario, Opinion opinion);
	public UsuarioI getFuente();
	public Double distanciaHasta(MuestraI muestra);
	public UbicacionI getUbicacion();
	
}
