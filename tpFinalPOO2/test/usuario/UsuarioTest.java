package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.MuestraI;
import opinion.Opinion;
import sistemaWeb.SistemaWebUsuarioI;

public class UsuarioTest {

	
	SistemaWebUsuarioI sistema;
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

	LocalDate fechaHoy;
	LocalDate fecha35Dias;

	HashMap <MuestraI,LocalDate> opiniones19en30Dias;
	HashMap <MuestraI,LocalDate> opiniones20en30Dias;
	HashMap <MuestraI,LocalDate> opiniones20enMasDe30Dias;
	
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
		muestra8= mock(MuestraI.class);
		muestra9= mock(MuestraI.class);
		muestra10= mock(MuestraI.class);
		muestra11= mock(MuestraI.class);
		muestra12= mock(MuestraI.class);
		muestra13= mock(MuestraI.class);
		muestra14= mock(MuestraI.class);
		muestra15= mock(MuestraI.class);
		muestra16= mock(MuestraI.class);
		muestra17= mock(MuestraI.class);
		muestra18= mock(MuestraI.class);
		muestra19= mock(MuestraI.class);
		muestra20= mock(MuestraI.class);
	
		fechaHoy= LocalDate.now();
		fecha35Dias= LocalDate.now().minusDays(35);
		
		certificado= mock (CertificadoExternoI.class);
		sistema= mock (SistemaWebUsuarioI.class);
		
		usuarioBasicoJuan= new Usuario();
		usuarioBasicoPepe= new Usuario();
		usuarioBasicoLeonel= new Usuario();
		
		usuarioExpertoMaria= new Usuario();
		usuarioExpertoMaria.setCategoriaUsuario(new Experto());
		
		usuarioEspecialistaMarta= new Usuario(certificado);
		
		usuarioBasicoJuan.setSistema(sistema);
		usuarioBasicoPepe.setSistema(sistema);
		usuarioBasicoLeonel.setSistema(sistema);
		usuarioExpertoMaria.setSistema(sistema);
		usuarioEspecialistaMarta.setSistema(sistema);
		
		opiniones19en30Dias= new 	HashMap <MuestraI,LocalDate>();
		opiniones19en30Dias.put(muestra1, fechaHoy);
		opiniones19en30Dias.put(muestra2, fechaHoy);
		opiniones19en30Dias.put(muestra3, fechaHoy);
		opiniones19en30Dias.put(muestra4, fechaHoy);
		opiniones19en30Dias.put(muestra5, fechaHoy);
		opiniones19en30Dias.put(muestra6, fechaHoy);
		opiniones19en30Dias.put(muestra7, fechaHoy);
		opiniones19en30Dias.put(muestra8, fechaHoy);
		opiniones19en30Dias.put(muestra9, fechaHoy);
		opiniones19en30Dias.put(muestra10, fechaHoy);
		opiniones19en30Dias.put(muestra11, fechaHoy);
		opiniones19en30Dias.put(muestra12, fechaHoy);
		opiniones19en30Dias.put(muestra13, fechaHoy);
		opiniones19en30Dias.put(muestra14, fechaHoy);
		opiniones19en30Dias.put(muestra15, fechaHoy);
		opiniones19en30Dias.put(muestra16, fechaHoy);
		opiniones19en30Dias.put(muestra17, fechaHoy);
		opiniones19en30Dias.put(muestra18, fechaHoy);
		opiniones19en30Dias.put(muestra19, fechaHoy);
		
		opiniones20en30Dias= new 	HashMap <MuestraI,LocalDate>();
		opiniones20en30Dias.putAll(opiniones19en30Dias);
		opiniones20en30Dias.put(muestra20, fechaHoy);
		
		opiniones20enMasDe30Dias= new 	HashMap <MuestraI,LocalDate>();
		opiniones20enMasDe30Dias.putAll(opiniones19en30Dias);
		opiniones20enMasDe30Dias.put(muestra20, fecha35Dias);
		
		usuarioExpertoMaria.setHistorialOpinion(opiniones20en30Dias);
	}
	
	
	@Test
	public void testUsuarioRecienCreadoEsUnUsuarioBasico() {
		Usuario user= new Usuario();
		CategoriaUsuario estado= new Basico();
		
		assertEquals(estado.getClass(), user.getCategoriaUsuario().getClass());
	}
	
	@Test
	public void testUsuarioQuePresentaUnCertificadoAlCrearseEsUnUsuarioEspecialista() {
		Usuario user= new Usuario(certificado);
		CategoriaUsuario estado= new Especialista();
		
		assertEquals(estado.getClass(), user.getCategoriaUsuario().getClass());
	}
	
	@Test
	public void testUnUsuarioPuedePresentarUnCertificadoLuegoDeCrearseParaPasarASerEspecialista() {
		Usuario user= new Usuario(certificado);
		CategoriaUsuario estado= new Especialista();
		user.presentarCertificado(certificado);
		
		assertEquals(estado.getClass(), user.getCategoriaUsuario().getClass());
	}
	
	@Test
	
	public void testUnUsuarioBasicoPuedeEnviarUnaMuestraAlSistemaWeb() {
		usuarioBasicoJuan.enviarMuestra(muestra1);
		
		verify(sistema).registrarMuestra(muestra1);
	}
	
	@Test
	public void testUnUsuarioExpertoPuedeEnviarUnaMuestraAlSistemaWeb() {
		usuarioExpertoMaria.enviarMuestra(muestra1);
		
		verify(sistema).registrarMuestra(muestra1);
	}
	
	@Test
	public void testUnUsuarioEspecialistaPuedeEnviarUnaMuestraAlSistemaWeb() {
		usuarioEspecialistaMarta.enviarMuestra(muestra1);
		
		verify(sistema).registrarMuestra(muestra1);
	}
	
	
	@Test
	public void testUnUsuarioBasicoPuedeEnviarUnaOpinionAUnaMuestra() {
		Opinion opinion= Opinion.ChincheFoliada;
		usuarioBasicoJuan.opinar(muestra1,opinion);
		
		verify(muestra1).registrarOpinionNormal(usuarioBasicoJuan, opinion);
	}
	
	@Test
	public void testUnUsuarioExpertoPuedeEnviarUnaOpinionAUnaMuestra() {
		Opinion opinion= Opinion.ChincheFoliada;
		usuarioExpertoMaria.opinar(muestra1,opinion);
		
		verify(muestra1).registrarOpinionExperta(usuarioExpertoMaria, opinion);
	}
	
	@Test
	public void testUnUsuarioEspecialistaPuedeEnviarUnaOpinionAUnaMuestra() {
		Opinion opinion= Opinion.ChincheFoliada;
		usuarioEspecialistaMarta.opinar(muestra1,opinion);
		
		verify(muestra1).registrarOpinionExperta(usuarioEspecialistaMarta, opinion);
	}
	
	@Test
	public void testUnUsuarioBasicoPuedeSaberSiCumpleElRequisitoDeOpinar20VecesEn30DiasParaSerExperto() {
		assertFalse(usuarioBasicoPepe.cumpleRequisito20Opiniones());
		
		usuarioBasicoPepe.setHistorialOpinion(opiniones20en30Dias);

		assertTrue(usuarioBasicoPepe.cumpleRequisito20Opiniones());
	}

	@Test
	public void testUnUsuarioBasicoQueOpina20VecesEnMasDe30DiasNoCumpleElRequisitoParaSerExperto() {
		usuarioBasicoPepe.setHistorialOpinion(opiniones20enMasDe30Dias);

		assertFalse(usuarioBasicoPepe.cumpleRequisito20Opiniones());
	}
	
	
	@Test
	public void testUnUsuarioBasicoQueEnvia10MuestrasYOpina20VecesEn30DiasPasaASerExperto() {
		when(sistema.verificar10MuestrasUsuario(usuarioBasicoPepe)).thenReturn(true);
		
		usuarioBasicoPepe.setHistorialOpinion(opiniones20en30Dias);
		usuarioBasicoPepe.verificarCategoria();
		
		assertEquals(Experto.class,usuarioBasicoPepe.getCategoriaUsuario().getClass());
	}
	
	@Test
	public void testUnUsuarioBasicoQueOpina20VecesEn30DiasPeroNoCumpleElRequisitoDeEnvioDeMuestrasNoEsExperto() {
		when(sistema.verificar10MuestrasUsuario(usuarioBasicoPepe)).thenReturn(false);
		
		usuarioBasicoPepe.setHistorialOpinion(opiniones20en30Dias);
		usuarioBasicoPepe.verificarCategoria();
		
		assertEquals(Basico.class,usuarioBasicoPepe.getCategoriaUsuario().getClass());
	}
	
	@Test
	public void testUnUsuarioExpertoQueNoCumpleElRequisitoDeEnvioDeMuestrasDejaDeSerExperto() {
		when(sistema.verificar10MuestrasUsuario(usuarioExpertoMaria)).thenReturn(false);
		
		usuarioExpertoMaria.setHistorialOpinion(opiniones20en30Dias);
		usuarioExpertoMaria.verificarCategoria();
		
		assertEquals(Basico.class,usuarioExpertoMaria.getCategoriaUsuario().getClass());
	}
	
	@Test
	public void testUnUsuarioExpertoQueOpina20VecesEnMasDe30DiasNoCumpleElRequisitoParaSerExperto() {
		when(sistema.verificar10MuestrasUsuario(usuarioExpertoMaria)).thenReturn(true);
		
		usuarioExpertoMaria.setHistorialOpinion(opiniones20enMasDe30Dias);
		usuarioExpertoMaria.verificarCategoria();
		
		assertEquals(Basico.class,usuarioExpertoMaria.getCategoriaUsuario().getClass());
		
	}
	
	@Test
	public void testUnUsuarioExpertoQueOpina20VecesEn30DiasYEnvio10MuestrasContinuaSiendoExperto() {
		when(sistema.verificar10MuestrasUsuario(usuarioExpertoMaria)).thenReturn(true);
		
		usuarioExpertoMaria.setHistorialOpinion(opiniones20en30Dias);
		usuarioExpertoMaria.verificarCategoria();
		
		assertEquals(Experto.class,usuarioExpertoMaria.getCategoriaUsuario().getClass());
	}
	
	@Test
	public void testUnUsuarioEspecialistaNoPuedeBajarDeCategoriaAunqueNoCumplaLosRequisitos() {
		when(sistema.verificar10MuestrasUsuario(usuarioEspecialistaMarta)).thenReturn(false);
		
		usuarioEspecialistaMarta.setHistorialOpinion(opiniones20enMasDe30Dias);
		usuarioEspecialistaMarta.verificarCategoria();
		
		assertEquals(Especialista.class,usuarioEspecialistaMarta.getCategoriaUsuario().getClass());
	}
	
	
	
	
}
