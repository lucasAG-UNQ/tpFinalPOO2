package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.MuestraI;
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
	
	@BeforeEach
	public void setUp() {
		//Se mockea el sistema y se utiliza el mensaje setSistema con el fin de testear sin
		//dependencia del sistema
		certificado= mock (CertificadoExternoI.class);
		sistema= mock (SistemaWebUsuarioI.class);
		
		usuarioBasicoJuan= new Usuario();
		usuarioBasicoPepe= new Usuario();
		usuarioBasicoLeonel= new Usuario();
		usuarioEspecialistaMarta= new Usuario(certificado);
		
		usuarioBasicoJuan.setSistema(sistema);
		usuarioBasicoPepe.setSistema(sistema);
		usuarioBasicoLeonel.setSistema(sistema);
		usuarioEspecialistaMarta.setSistema(sistema);
		
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
		
	}
	
	@Test
	public void testUnUsuarioExpertoPuedeEnviarUnaMuestraAlSistemaWeb() {
		
	}
	
	@Test
	public void testUnUsuarioEspecialistaPuedeEnviarUnaMuestraAlSistemaWeb() {
		
	}
	
	
	@Test
	public void testUnUsuarioBasicoPuedeEnviarUnaOpinionAUnaMuestra() {
		
	}
	
	@Test
	public void testUnUsuarioExpertoPuedeEnviarUnaOpinionAUnaMuestra() {
		
	}
	
	@Test
	public void testUnUsuarioEspecialistaPuedeEnviarUnaOpinionAUnaMuestra() {
		
	}
	
	@Test
	public void testUnUsuarioBasicoPuedeSaberSiCumpleElRequisitoDeOpinar20VecesEn30DiasParaSerExperto() {
		assertFalse(usuarioBasicoPepe.cumpleRequisito20Opiniones());
		
		usuarioBasicoPepe.setHistorialOpinion(opiniones20en30Dias);
		
	}

	@Test
	public void testUnUsuarioBasicoQueOpina20VecesEnMasDe30DiasNoCumpleElRequisitoParaSerExperto() {
		
	}
	
	
	@Test
	public void testUnUsuarioBasicoQueEnvia10MuestrasYOpina20VecesEn30DiasPasaASerExperto() {
		when(sistema.verificar10MuestrasUsuario(usuarioBasicoPepe)).thenReturn(true);
	}
	
	@Test
	public void testUnUsuarioBasicoQueOpina20VecesEn30DiasPeroNoCumpleElRequisitoDeEnvioDeMuestrasNoEsExperto() {
		when(sistema.verificar10MuestrasUsuario(usuarioBasicoPepe)).thenReturn(false);
	}
	
	public void testUnUsuarioExpertoQueNoCumpleElRequisitoDeEnvioDeMuestrasDejaDeSerExperto() {
		when(sistema.verificar10MuestrasUsuario(usuarioBasicoPepe)).thenReturn(false);
	}
	
	@Test
	public void testUnUsuarioExpertoQueOpina20VecesEnMasDe30DiasNoCumpleElRequisitoParaSerExperto() {
		when(sistema.verificar10MuestrasUsuario(usuarioBasicoPepe)).thenReturn(true);
	}
	
}
