package muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import muestra.NoVinchuca;
import muestra.UbicacionI;
import muestra.UsuarioI;
import muestra.Vinchuca;


public class MuestraTest {

	Muestra muestraVinchuca;
	Muestra muestraNoVinchuca;
	UbicacionI ubicacion1;
	UbicacionI ubicacion2;
	UsuarioI usuarioBasicoJuan;
	UsuarioI usuarioBasicoPepe;
	UsuarioI usuarioExpertoLeonel;
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
		usuarioExpertoLeonel= mock(UsuarioI.class);
		usuarioExpertoMaria= mock(UsuarioI.class);
		usuarioEspecialistaMarta= mock(UsuarioI.class);
		ubicacion1= mock(UbicacionI.class);
		ubicacion2= mock(UbicacionI.class);
		muestraNoVinchuca= new Muestra(Vinchuca.Gusayana,ubicacion1,usuarioBasicoJuan);
		muestraVinchuca= new Muestra(Vinchuca.Infestans,ubicacion2,usuarioExpertoMaria);
	}
	
	@Test
	public void testMuestraSeCreaCorrectamente() {
		assertEquals(Vinchuca.Infestans,muestraVinchuca.getEspecieFotografiada());
		assertEquals(ubicacion2,muestraVinchuca.getUbicacion());
		assertEquals(usuarioExpertoMaria,muestraVinchuca.getFuente());
		
	}
	
	@Test
	public void testResultadoActualDeUnaMuestraRecienEnviadaEsLaEspecieDeLaMuestraEnviada() {
		assertEquals("Infestans", muestraVinchuca.resultadoActual());
	}
	
	@Test
	public void testResultadoActualDeUnaMuestraDondeHayUnEmpateDeOpinionesResultaEnNoDefinido() {
		muestraVinchuca.registrarOpinionNormal(usuarioBasicoPepe, NoVinchuca.ChincheFoliada);
		assertEquals("No Definido", muestraVinchuca.resultadoActual());
	}
	
	@Test
	public void testElUsuarioQueEnviaLaMuestraNoPuedeOpinar(){
		
	}
	
	@Test
	public void testUnUsuarioQueOpinaNoPuedeVolverAOpinar() {
		
	}
	
}
