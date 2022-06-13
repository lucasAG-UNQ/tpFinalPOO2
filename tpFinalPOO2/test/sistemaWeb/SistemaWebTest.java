package sistemaWeb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.MuestraI;
import organizacion.IZonaDeCobertura;
import organizacion.OrganizacionI;
import usuario.CertificadoExternoI;
import usuario.Experto;
import usuario.Usuario;

public class SistemaWebTest {

	SistemaWeb sistema;
	Usuario usuarioBasicoJuan;
	Usuario usuarioBasicoPepe;
	Usuario usuarioBasicoLeonel;
	Usuario usuarioExpertoMaria;
	Usuario usuarioEspecialistaMarta;
	CertificadoExternoI certificado;
	MuestraI muestra1;
	MuestraI muestra2;
	MuestraI muestra3;
	MuestraI muestra4;
	MuestraI muestra5;
	MuestraI muestra6;
	MuestraI muestra7;
	MuestraI muestra8;
	MuestraI muestra9;
	MuestraI muestra10;
	MuestraI muestra11;
	MuestraI muestra12;
	MuestraI muestra13;
	MuestraI muestra14;
	MuestraI muestra15;
	MuestraI muestra16;
	MuestraI muestra17;
	MuestraI muestra18;
	MuestraI muestra19;
	MuestraI muestra20;
	
	OrganizacionI organizacion1;
	
	IZonaDeCobertura zona1;
	IZonaDeCobertura zona2;
	IZonaDeCobertura zona3;
	IZonaDeCobertura zona4;
	IZonaDeCobertura zona5;
	
	
	@BeforeEach
	public void setUp() {
		//Se mockea el sistema y se utiliza el mensaje setSistema con el fin de testear sin
		//dependencia del sistema
		muestra1= mock(MuestraI.class);
		muestra2= mock(MuestraI.class);
		muestra3= mock(MuestraI.class);
		muestra4= mock(MuestraI.class);
		muestra5= mock(MuestraI.class);
		muestra6= mock(MuestraI.class);
		muestra7= mock(MuestraI.class);
		muestra20= mock(MuestraI.class);
		
		certificado= mock (CertificadoExternoI.class);
		sistema= SistemaWeb.getInstance();
		
		usuarioBasicoJuan= new Usuario();
		usuarioBasicoPepe= new Usuario();
		usuarioBasicoLeonel= new Usuario();
		
		usuarioExpertoMaria= new Usuario();
		
		usuarioEspecialistaMarta= new Usuario(certificado);
		
		usuarioBasicoJuan.setSistema(sistema);
		usuarioBasicoPepe.setSistema(sistema);
		usuarioBasicoLeonel.setSistema(sistema);
		usuarioExpertoMaria.setSistema(sistema);
		usuarioEspecialistaMarta.setSistema(sistema);
		
		organizacion1= mock(OrganizacionI.class);
		
		sistema.registrarMuestra(muestra1);
		sistema.registrarMuestra(muestra2);
		sistema.registrarMuestra(muestra3);
		sistema.registrarMuestra(muestra4);
		sistema.registrarMuestra(muestra5);
		sistema.registrarMuestra(muestra6);
		sistema.registrarMuestra(muestra7);
		
		zona1=mock(IZonaDeCobertura.class);
		zona2=mock(IZonaDeCobertura.class);
		zona3=mock(IZonaDeCobertura.class);
		zona4=mock(IZonaDeCobertura.class);
		zona5=mock(IZonaDeCobertura.class);
		
		sistema.registrarZona(zona1);
		sistema.registrarZona(zona2);
		sistema.registrarZona(zona3);
		sistema.registrarZona(zona4);
	}
	
	@Test
	public void testCuandoSePideUnaInstanciaAlSistemaSiempreEsLaMisma() {
		assertEquals(sistema,SistemaWeb.getInstance());
	}
	
	@Test
	public void testSePuedenRegistrarMuestrasEnElSistemaWeb() {
		assertEquals("Muestra registrada",sistema.registrarMuestra(muestra20));
		assertTrue(sistema.getMuestras().contains(muestra20));
	}
	
	@Test
	public void testSePuedenRegistrarUsuariosEnElSistemaWeb() {
		assertEquals("Usuario registrado", sistema.registrarUsuario(usuarioBasicoPepe));
		assertTrue(sistema.getUsuarios().contains(usuarioBasicoPepe));
	}
	
	@Test
	public void testSePuedenRegistrarOrganizacionesAlSistema() {
		assertEquals("Organizacion registrada", sistema.registrarOrganizacion(organizacion1));
		assertTrue(sistema.getOrganizaciones().contains(organizacion1));
	}
	
	@Test
	public void testSePuedenRegistrarZonasDeCoberturaAlSistema() {
		assertEquals("Zona registrada", sistema.registrarZona(zona5));
		assertTrue(sistema.getZonasDeCobertura().contains(zona5));
	}
	
	@Test
	public void testSePuedenSaberQueMuestrasEstanAXKilometrosDeOtraMuestra() {
		when(muestra20.distanciaHasta(muestra1)).thenReturn(9d);
		when(muestra20.distanciaHasta(muestra2)).thenReturn(15d);
		when(muestra20.distanciaHasta(muestra3)).thenReturn(10d);
		when(muestra20.distanciaHasta(muestra4)).thenReturn(9.9d);
		when(muestra20.distanciaHasta(muestra5)).thenReturn(8d);
		when(muestra20.distanciaHasta(muestra6)).thenReturn(20d);
		when(muestra20.distanciaHasta(muestra7)).thenReturn(10.1d);
		
		ArrayList<MuestraI> esperado= new ArrayList<MuestraI>(Arrays.asList(muestra1,
																			muestra4,
																			muestra5));
		
		sistema.getMuestras().stream().forEach(e->System.out.println(e));
		
		assertTrue(sistema.muestrasAMenosDe(muestra20, 10d).containsAll(esperado));
	}
	
	@Test
	public void testCuandoSeRegistraUnaMuestraSeLeNotificaALasZonasDeCobertura() {
		sistema.registrarMuestra(muestra20);
		
		verify(zona1).addMuestra(muestra20);
		verify(zona2).addMuestra(muestra20);
		verify(zona3).addMuestra(muestra20);
		verify(zona4).addMuestra(muestra20);
		verify(zona5, never()).addMuestra(muestra20);
	}
	
	@Test
	public void testCuandoUnaMuestraEsVerificadaSeLeNotificaALasZonasDeCobertura() {
		sistema.muestraVerificada(muestra1);
		
		verify(zona1).muestraVerificada(muestra1);
		verify(zona2).muestraVerificada(muestra1);
		verify(zona3).muestraVerificada(muestra1);
		verify(zona4).muestraVerificada(muestra1);
		verify(zona5, never()).muestraVerificada(muestra1);
	}
	
	
}
