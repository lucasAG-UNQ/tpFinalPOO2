package muestra;

import java.time.LocalDate;

import opinion.OpinionI;
import usuario.UsuarioI;
import ubicacion.UbicacionI;

public interface MuestraI {

	public LocalDate getFechaUltimaOpinion();
	public String registrarOpinionNormal(UsuarioI usuario, OpinionI opinion);
	public String registrarOpinionExperta(UsuarioI usuario, OpinionI opinion);
	public UsuarioI getFuente();
	public Double distanciaHasta(MuestraI muestra);
	public UbicacionI getUbicacion();
	public Object getFechaEnvio();
	public Object getEspecieFotografiada();
	public Boolean estaVerificada();
	
}
