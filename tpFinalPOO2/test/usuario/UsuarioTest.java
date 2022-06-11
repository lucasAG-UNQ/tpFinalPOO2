package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.MuestraI;

public class UsuarioTest {

	Usuario usuarioBasicoJuan;
	Usuario usuarioBasicoPepe;
	Usuario usuarioBasicoLeonel;
	Usuario usuarioExpertoMaria;
	Usuario usuarioEspecialistaMarta;
	CertificadoExternoI certificado;
	MuestraI muestra1;
	
	@BeforeEach
	public void setUp() {
		certificado= mock (CertificadoExternoI.class);
		usuarioBasicoJuan= new Usuario();
		usuarioBasicoPepe= new Usuario();
		usuarioBasicoLeonel= new Usuario();
		usuarioEspecialistaMarta= new Usuario(certificado);
		
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
	public void testUnUsuarioBasicoQueEnvia10MuestrasYOpina20VecesEn30DiasEsExperto() {
		//TODO
		//Podria testearse desde los test de funcionalidad general o desde los test del sistema web
	}
	
}
