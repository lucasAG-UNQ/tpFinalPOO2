package ubicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UbicacionTest {

	Ubicacion ubicacion1;
	Ubicacion ubicacion2;
	Ubicacion ubicacion3;
	Ubicacion ubicacion4;
	
	@BeforeEach
	public void setUp() {
		ubicacion1=new Ubicacion(15.1,12.2);
		ubicacion2=new Ubicacion(13.2,11.1);
		ubicacion3=new Ubicacion(15.1021,12.20081);
		ubicacion4=new Ubicacion(19.1,22.2);
	}
	
	@Test
	public void testSePuedeSaberLaAltitudYLatitudDeUnaUbicacion() {
		assertEquals(15.1,ubicacion1.getLatitud());
		assertEquals(12.2,ubicacion1.getLongitud());
	}
	
	@Test
	public void testSePuedePedirLaDistanciaAUnaUbicacionHastaOtraUbicaionEnKilometros() {
		assertEquals(242.28153763392086,ubicacion1.distanciaHasta(ubicacion2));
	}
	
	@Test
	public void testElCalculoDeLaDistanciaEsConmutativo() {
		assertEquals(ubicacion1.distanciaHasta(ubicacion2),ubicacion2.distanciaHasta(ubicacion1));
	}
	
	@Test
	public void testSePuedeSaberQueUbicacionesEstanAMenosDeXKilometrosDeUnaMuestra() {
		List <Ubicacion> ubicaciones= new ArrayList<Ubicacion>(Arrays.asList(ubicacion2, ubicacion1, ubicacion3, ubicacion4));
		List <Ubicacion> res= ubicacion1.ubicacionesAMenosDe(ubicaciones, 10d);
		List <Ubicacion> esperado= new ArrayList<Ubicacion>(Arrays.asList(ubicacion1, ubicacion3));
		
		assertTrue(ubicacion1.distanciaHasta(ubicacion1)<10);
		assertTrue(ubicacion1.distanciaHasta(ubicacion3)<10);
		assertTrue(res.containsAll(esperado));
	}
	
}
