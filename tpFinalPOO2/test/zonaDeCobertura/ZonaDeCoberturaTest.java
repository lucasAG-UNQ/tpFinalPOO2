package zonaDeCobertura;

import ubicacion.UbicacionI;

public class ZonaDeCoberturaTest {

	ZonaDeCobertura zona1;
	ZonaDeCobertura zona2;
	ZonaDeCobertura zona3;
	ZonaDeCobertura zona4;
	ZonaDeCobertura zona5;
	UbicacionI ubicacion1;
	
	@BeforeEach
	public void setUp() {
		zona1=new ZonaDeCobertura("Avellaneda 1", ubicacion1,10d);
	}
}
