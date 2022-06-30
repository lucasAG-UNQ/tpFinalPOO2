package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.MuestraI;
import opinion.OpinionI;
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
	MuestraI muestra20;
	OpinionI opinion1;
	OpinionI opinionHoy;
	OpinionI opinionHace35Dias;

	LocalDate fechaHoy;
	LocalDate fecha35Dias;

	List <OpinionI> opiniones19en30Dias;
	List <OpinionI> opiniones20en30Dias;
	List <OpinionI> opiniones20enMasDe30Dias;
	
	@BeforeEach
	public void setUp() {
		//Se mockea el sistema y se utiliza el mensaje setSistema con el fin de testear sin
		//dependencia del sistema
		muestra1= mock(MuestraI.class);
		muestra20= mock(MuestraI.class);

		fechaHoy= LocalDate.now();
		fecha35Dias= LocalDate.now().minusDays(35);
		
		
		opinionHoy= mock(OpinionI.class);
		when(opinionHoy.getFechaOpinion()).thenReturn(fechaHoy);
		opinionHace35Dias= mock(OpinionI.class);
		when(opinionHace35Dias.getFechaOpinion()).thenReturn(fecha35Dias);
		
		
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
		
		opiniones19en30Dias= new ArrayList <OpinionI>();
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		opiniones19en30Dias.add(opinionHoy);
		
		opiniones20en30Dias= new ArrayList <OpinionI>();
		opiniones20en30Dias.addAll(opiniones19en30Dias);
		opiniones20en30Dias.add(opinionHoy);
		
		opiniones20enMasDe30Dias= new ArrayList <OpinionI>();
		opiniones20enMasDe30Dias.addAll(opiniones19en30Dias);
		opiniones20enMasDe30Dias.add(opinionHace35Dias);
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
