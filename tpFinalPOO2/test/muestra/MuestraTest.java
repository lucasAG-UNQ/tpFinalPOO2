package muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
	UsuarioI usuarioEspecialistaMarta;
	Calendar fechaHoy;
	Calendar fecha35Dias;
	
	@BeforeEach
	public void setUp() {
		fechaHoy= Calendar.getInstance();
		fecha35Dias= Calendar.getInstance();
		fecha35Dias.add(Calendar.DAY_OF_MONTH, -35);
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
		
		muestraVinchuca.getOpiniones().entrySet().stream().forEach(e->System.out.println(e));
		
		assertEquals("Solo pueden opinar expertos, estado actual: No Definido",muestraVinchuca.resultadoActual());
	}
	

}
