package organizacion;

import muestra.MuestraI;

import java.util.List;

import ubicacion.UbicacionI;

public interface IZonaDeCobertura {

	List<MuestraI> getMuestras();
	double getRadio();
	UbicacionI getEpicentro();
	List<ZonaDeCobertura> zonasSolapadas(List<ZonaDeCobertura> zonas);
	public void addMuestra(MuestraI muestra);
	void notificarMuestraValidada(MuestraI muestra);
	
}
