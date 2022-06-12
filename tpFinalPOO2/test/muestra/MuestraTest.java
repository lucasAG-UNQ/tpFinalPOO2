package muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import opinion.Opinion;
import ubicacion.UbicacionI;
import usuario.UsuarioI;


public class MuestraTest {

	Muestra muestraVinchuca;
	Muestra muestraNoVinchuca;
	UbicacionI ubicacion1;
	UbicacionI ubicacion2;
	UsuarioI usuarioBasicoJuan;
	UsuarioI usuarioBasicoPepe;
	UsuarioI usuarioBasicoLeonel;
	UsuarioI usuarioExpertoMaria;
	UsuarioI usuarioExpertoToto;
	UsuarioI usuarioEspecialistaMarta;
	LocalDate fechaHoy;
	LocalDate fecha35Dias;
	
	@BeforeEach
	public void setUp() {
		fechaHoy= LocalDate.now();
		fecha35Dias= LocalDate.now().minusDays(35);
		usuarioBasicoJuan= mock(UsuarioI.class);
		usuarioBasicoPepe= mock(UsuarioI.class);
		usuarioBasicoLeonel= mock(UsuarioI.class);
		usuarioExpertoMaria= mock(UsuarioI.class);
		usuarioEspecialistaMarta= mock(UsuarioI.class);
		ubicacion1= mock(UbicacionI.class);
		ubicacion2= mock(UbicacionI.class);
		//Como precondicion el usuario opina al momento de enviar la muestra, se simula esto aqui
		muestraNoVinchuca= new Muestra(Opinion.Gusayana,ubicacion1,usuarioBasicoJuan);
		muestraNoVinchuca.registrarOpinionNormal(usuarioBasicoJuan, Opinion.Gusayana);
		
		muestraVinchuca= new Muestra(Opinion.Infestans,ubicacion2,usuarioExpertoMaria);
		Opinion opinionExperta= Opinion.Infestans;
		opinionExperta.setEsExperta();
		muestraVinchuca.registrarOpinionExperta(usuarioExpertoMaria, opinionExperta);
	}
	
	@Test
	public void testMuestraSeCreaCorrectamente() {
		assertEquals(Opinion.Infestans,muestraVinchuca.getEspecieFotografiada());
		assertEquals(ubicacion2,muestraVinchuca.getUbicacion());
		assertEquals(usuarioExpertoMaria,muestraVinchuca.getFuente());
		assertEquals(LocalDate.now(),muestraVinchuca.getFechaEnvio());
	}
	
	@Test
	public void testResultadoActualDeUnaMuestraRecienEnviadaEsLaEspecieDeLaMuestraEnviada() {
		assertEquals("Solo pueden opinar expertos, estado actual: Infestans", muestraVinchuca.resultadoActual());
	}
	
	@Test
	public void testResultadoActualDeUnaMuestraDondeHayUnEmpateDeOpinionesResultaEnNoDefinido() {
		muestraNoVinchuca.registrarOpinionNormal(usuarioBasicoPepe, Opinion.ChincheFoliada);
		assertEquals("Estado actual: No Definido", muestraNoVinchuca.resultadoActual());
	}
	
	@Test
	public void testElUsuarioQueEnviaLaMuestraNoPuedeOpinar(){
		String esperado= "Estado actual: "+Opinion.Gusayana.toString();
		assertEquals(esperado,muestraNoVinchuca.resultadoActual());
		assertEquals("Usted ya opino en esta muestra",muestraNoVinchuca.registrarOpinionNormal(usuarioBasicoJuan, Opinion.ImagenPocoClara));
		assertEquals(esperado,muestraNoVinchuca.resultadoActual());
	}
	
	@Test
	public void testUnUsuarioQueOpinaNoPuedeVolverAOpinar() {
		muestraNoVinchuca.registrarOpinionNormal(usuarioBasicoPepe, Opinion.ChincheFoliada);
		muestraNoVinchuca.registrarOpinionNormal(usuarioBasicoPepe, Opinion.ChincheFoliada);
		
		assertEquals("Estado actual: No Definido", muestraNoVinchuca.resultadoActual());
	}
	
	@Test
	public void testCuandoOpinaUnUsuarioExpertoUnUsuarioBasicoYaNoPuedeOpinar() {
		muestraNoVinchuca.registrarOpinionExperta(usuarioExpertoMaria, Opinion.Gusayana);
		String resultado= muestraNoVinchuca.registrarOpinionNormal(usuarioBasicoPepe, Opinion.ChincheFoliada);
		assertEquals("No puede opinar ya que un experto opino",resultado);
	}
	
	@Test
	public void testCuandoOpinaUnUsuarioExpertoSoloCuentanVotosDeUsuariosExpertosParaElResultado() {
		muestraVinchuca.registrarOpinionNormal(usuarioBasicoPepe, Opinion.Infestans);
		muestraVinchuca.registrarOpinionNormal(usuarioBasicoLeonel, Opinion.Infestans);
		
		Opinion opinionExperta=Opinion.Gusayana;
		opinionExperta.setEsExperta();
		muestraVinchuca.registrarOpinionExperta(usuarioEspecialistaMarta, opinionExperta);
		
		assertEquals("Solo pueden opinar expertos, estado actual: No Definido",muestraVinchuca.resultadoActual());
	}
	
	@Test
	public void testCuandoDosUsuariosExpertosCoincidenConSusOpinionesYaNadiePuedeOpinar() {
		Opinion opinionExperta=Opinion.Infestans;
		opinionExperta.setEsExperta();
		muestraVinchuca.registrarOpinionExperta(usuarioEspecialistaMarta, opinionExperta);
		
		String resExperto=muestraVinchuca.registrarOpinionExperta(usuarioExpertoToto, opinionExperta);
		String resBasico= muestraVinchuca.registrarOpinionNormal(usuarioBasicoPepe, Opinion.ImagenPocoClara);
		
		assertEquals("No se puede opinar ya que esta muestra esta verificada",resExperto);
		assertEquals("No se puede opinar ya que esta muestra esta verificada",resBasico);
	}

	@Test
	public void testElResultadoDeUnaMuestraVerificadaEsVerificadaYSuResultado() {
		Opinion opinionExperta=Opinion.Infestans;
		opinionExperta.setEsExperta();
		muestraVinchuca.registrarOpinionExperta(usuarioEspecialistaMarta, opinionExperta);
		
		assertEquals("Verificada: Infestans", muestraVinchuca.resultadoActual());
		
	}
	
}
