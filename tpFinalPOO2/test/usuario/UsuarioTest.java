package usuario;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

	Usuario usuarioBasicoJuan;
	Usuario usuarioBasicoPepe;
	Usuario usuarioBasicoLeonel;
	Usuario usuarioExpertoMaria;
	Usuario usuarioEspecialistaMarta;
	CertificadoExternoI certificado;
	
	@BeforeEach
	public void setUp() {
		certificado= mock (CertificadoExternoI.class);
		usuarioBasicoJuan= new Usuario();
		usuarioBasicoPepe= new Usuario();
		usuarioBasicoLeonel= new Usuario();
		usuarioEspecialistaMarta= new Usuario(certificado);
		
	}
	
	
	@Test
	public void testUsuarioSeCreaCorrectamente() {
		
	}
	
	
}
