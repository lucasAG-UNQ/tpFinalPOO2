package organizacion;

import java.util.List;

import muestra.Muestra;
import ubicacion.UbicacionI;

public interface IZonaDeCobertura {

	List<Muestra> getMuestras();
	double getRadio();
	UbicacionI getEpicentro();
	List<ZonaDeCobertura> zonasSolapadas(List<ZonaDeCobertura> zonas);
	void addMuestra(Muestra muestra);
	void notificarMuestraValidada(Muestra muestra);
	
}
