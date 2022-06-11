package organizacion;

import muestra.Muestra;

public interface IZonaListener {
	
	void muestraRegistrada(Muestra m, ZonaDeCobertura z);
	
	void muestraValidada(Muestra m, ZonaDeCobertura z);

}
