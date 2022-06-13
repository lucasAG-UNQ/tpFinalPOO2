package organizacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import ubicacion.UbicacionI;


class OrganizacionTest {
	
	Organizacion organizacion1 ;
	UbicacionI quilmes;
	IFuncionalidadExterna pluginRegistro;
	IFuncionalidadExterna pluginValidacion;
	ZonaDeCobertura zona1;
	Muestra muestra1;
	
	@BeforeEach
	public void setUp() {
		quilmes = mock(UbicacionI.class);
		zona1 = mock(ZonaDeCobertura.class);
		pluginRegistro = mock(IFuncionalidadExterna.class);
		pluginValidacion = mock(IFuncionalidadExterna.class);
		muestra1 = mock(Muestra.class);
		organizacion1= new Organizacion(quilmes,TipoDeOrganizacion.Cultural,20,pluginRegistro,pluginValidacion);
	}
	
	@Test 
	public void testTipoDeOrganizacion() {
		assertEquals(TipoDeOrganizacion.Cultural, organizacion1.getTipoDeOrganizacion());
	}
	
	@Test
	public void testCantidadDeIntegrantes() {
		assertEquals(20,organizacion1.getCantDeIntegrantes());
	}
	
	@Test
	public void testDireccionDeOrganizacion() {
		when(quilmes.getLatitud()).thenReturn(22.14);
		when(quilmes.getLongitud()).thenReturn(44.25);
		
		assertEquals("Latitud: 22.14° - Longitud: 44.25°", organizacion1.direccionDeOrganizacion());
	}
	
	@Test
	public void testRegistrarseAZona() {
		
		organizacion1.agregarZonaDeInteres(zona1);
		verify(zona1).addListener(organizacion1);
		
	}
	
	@Test
	public void testDesuscribirseAZona() {
		
		organizacion1.agregarZonaDeInteres(zona1);
		organizacion1.quitarZonaDeInteres(zona1);
		verify(zona1).removeListener(organizacion1);
		
	}
	
	@Test
	
	public void testMuestraRegistradaEnZona() {
		
		organizacion1.muestraRegistrada(muestra1, zona1);
		verify(pluginRegistro).nuevoEvento(organizacion1, zona1, muestra1);
		
	}
	
	@Test
	
	public void testMuestraValidadaEnZona() {
		
		organizacion1.muestraValidada(muestra1, zona1);
		verify(pluginValidacion).nuevoEvento(organizacion1, zona1, muestra1);
		
	}
	
	
	
	
}

	