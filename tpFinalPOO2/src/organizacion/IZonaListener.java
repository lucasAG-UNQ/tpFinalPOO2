package organizacion;

import muestra.Muestra;
import muestra.MuestraI;

public interface IZonaListener {
	
	void muestraRegistrada(MuestraI muestra, ZonaDeCobertura z);
	
	void muestraValidada(MuestraI m, ZonaDeCobertura z);

}
