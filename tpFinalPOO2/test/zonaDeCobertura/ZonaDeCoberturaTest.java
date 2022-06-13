package zonaDeCobertura;

import ubicacion.Ubicacion;
import ubicacion.UbicacionI;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import organizacion.Organizacion;
import organizacion.ZonaDeCobertura;

public class ZonaDeCoberturaTest {

	ZonaDeCobertura zonaSur;
	ZonaDeCobertura zonaSur2;
	ZonaDeCobertura zonaNorte;
	
	UbicacionI quilmes;
	UbicacionI lomas;
	UbicacionI lujan;
	
	Muestra muestraValida;
	Muestra muestraNueva;
	Organizacion organizacion1;
	
	
	
	@BeforeEach
	public void setUp() {
		muestraValida = mock(Muestra.class);
		muestraNueva = mock(Muestra.class);
		organizacion1 = mock(Organizacion.class);
		quilmes = mock(UbicacionI.class);
		lujan = mock(UbicacionI.class);
		lomas = mock(UbicacionI.class);
		zonaSur=new ZonaDeCobertura("Zona Sur", quilmes,20);
		zonaSur2=new ZonaDeCobertura("Zona Sur2", lomas,20);
		zonaNorte=new ZonaDeCobertura("Zona Norte", lujan,20);
		
		
	}
	
	@Test
	public void testConocerNombre() {
		assertEquals("Zona Norte",zonaNorte.getNombre());
	}
	
	@Test
	public void testConocerEpicentro() {
		assertEquals(quilmes,zonaSur.getEpicentro());
	}
	
	@Test
	public void testConocerDistancia() {
		assertEquals(20,zonaSur.getRadio());
	}
	
	@Test
	public void testConocerMuestas() {
		when(quilmes.getLatitud()).thenReturn(22d);
		when(quilmes.getLongitud()).thenReturn(44d);			
		when(muestraNueva.getUbicacion()).thenReturn(quilmes);
		
		zonaSur.addMuestra(muestraNueva);		
		assertEquals(Arrays.asList(muestraNueva),zonaSur.getMuestras());
	}
	
	@Test
	public void testVerificarSiMuestaPerteneceAZonca() {
		when(quilmes.getLatitud()).thenReturn(22d);
		when(quilmes.getLongitud()).thenReturn(44d);			
		when(muestraNueva.getUbicacion()).thenReturn(quilmes);
		
		assertEquals(true,zonaSur.verificarSiMuestraPerteneceAZona(muestraNueva));
		
	}
	
	@Test
	public void testVerificarSiMuestaNoPerteneceAZonca() {
		when(quilmes.getLatitud()).thenReturn(22d);
		when(quilmes.getLongitud()).thenReturn(44d);
		when(lujan.getLatitud()).thenReturn(229d);
		when(lujan.getLongitud()).thenReturn(444d);			
		when(muestraNueva.getUbicacion()).thenReturn(lujan);
		
		assertEquals(false,zonaSur.verificarSiMuestraPerteneceAZona(muestraNueva));
		
	}
	
	
	@Test
	public void testZonasQueSeSolapan() {
		
		when(lomas.distanciaHasta(quilmes)).thenReturn(30d);
		when(lujan.distanciaHasta(quilmes)).thenReturn(100d);
				
		assertEquals(Arrays.asList(zonaSur2),zonaSur.zonasSolapadas(Arrays.asList(zonaNorte,zonaSur2)));
	}
	
	
	@Test
	public void agregarObserver() {
		zonaSur.addListener(organizacion1);
		assertEquals(Arrays.asList(organizacion1),zonaSur.getListeners());
	}
	
	@Test
	public void removerObserver() {
		zonaSur.addListener(organizacion1);
		zonaSur.removeListener(organizacion1);
		assertEquals(true,zonaSur.getListeners().isEmpty());
	}
	
	@Test
	public void notificarNuevaMuestra() {
		when(quilmes.getLatitud()).thenReturn(22d);
		when(quilmes.getLongitud()).thenReturn(44d);			
		when(muestraNueva.getUbicacion()).thenReturn(quilmes);
		
		zonaSur.addListener(organizacion1);		
		zonaSur.addMuestra(muestraNueva);			
		verify(organizacion1).muestraRegistrada(muestraNueva, zonaSur);
		
	}
	
	@Test
	public void notificarMuestraValidada() {
		when(quilmes.getLatitud()).thenReturn(22d);
		when(quilmes.getLongitud()).thenReturn(44d);			
		when(muestraValida.getUbicacion()).thenReturn(quilmes);
		
		zonaSur.addListener(organizacion1);		
		zonaSur.notificarMuestraValidada(muestraValida);			
		verify(organizacion1).muestraValidada(muestraValida, zonaSur);		
	}
	

	
}
