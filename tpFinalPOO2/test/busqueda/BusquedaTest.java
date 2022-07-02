package busqueda;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import muestra.MuestraI;
import opinion.Opinion;
import opinion.OpinionI;
import ubicacion.Ubicacion;
import usuario.UsuarioI;

public class BusquedaTest {
	List<MuestraI> muestras;
	MuestraI muestra1;
	MuestraI muestra2;
	MuestraI muestra3;
	Ubicacion ubicacion1;
	Ubicacion ubicacion2;
	Ubicacion ubicacion3;
	UsuarioI usuarioBasicoAna;
	UsuarioI usuarioExpertoMaria;
	UsuarioI usuarioExpertoPedro;
	LocalDate fechaCreacion1;
	LocalDate fechaCreacion2;
	LocalDate fechaCreacion3;
	String estadoVeri;
	OpinionI tipoInfen;
	OpinionI ningu;
	BFechaCreacion bFechaCre;
	BFechaUltimaVotacion bFechaUlt;
	BTipoInsecto bTipo;
	BNivelVerifica bVerificada;
	private LocalDate fechaOpinion1;
	private LocalDate fechaOpinion2;
	private LocalDate fechaOpinion3;	
	
		
	@BeforeEach
	public void setUp() {

		fechaCreacion1= LocalDate.of(2022, 01, 30);
		fechaCreacion2= LocalDate.of(2022, 03, 30);
		fechaCreacion3= LocalDate.of(2022, 06, 02);
		
		fechaOpinion1= LocalDate.of(2022, 01, 30);
		fechaOpinion2= LocalDate.of(2022, 03, 30);
		fechaOpinion3= LocalDate.of(2022, 06, 02);
			
		muestra1= mock(MuestraI.class);
		when(muestra1.getFechaEnvio()).thenReturn(fechaCreacion1);
		when(muestra1.getEspecieFotografiada()).thenReturn(Opinion.Gusayana);
		when(muestra1.estaVerificada()).thenReturn(true);
		when(muestra1.getFechaUltimaOpinion()).thenReturn(fechaOpinion1);
			
		muestra2= mock(MuestraI.class);
		when(muestra2.getFechaEnvio()).thenReturn(fechaCreacion2);
		when(muestra2.getEspecieFotografiada()).thenReturn(Opinion.Gusayana);
		when(muestra2.estaVerificada()).thenReturn(true);
		when(muestra2.getFechaUltimaOpinion()).thenReturn(fechaOpinion2);
		
		muestra3= mock(MuestraI.class);
		when(muestra3.getFechaEnvio()).thenReturn(fechaCreacion3);
		when(muestra3.getEspecieFotografiada()).thenReturn(Opinion.Infestans);
		when(muestra3.estaVerificada()).thenReturn(false);
		when(muestra3.getFechaUltimaOpinion()).thenReturn(fechaOpinion3);
		
		muestras= new ArrayList<MuestraI>();
		muestras.add(muestra1);
		muestras.add(muestra2);
		muestras.add(muestra3);
		
			
		bFechaCre= new BFechaCreacion(fechaCreacion3);
		bTipo= new BTipoInsecto (Opinion.Gusayana);
		bVerificada= new BNivelVerifica ("verificada");
		bFechaUlt = new BFechaUltimaVotacion(fechaOpinion1);
	}
		
		
	@Test
	public void testBusquedaPorFechaCreacion() {
		List<MuestraI> esperado= new ArrayList<MuestraI>(Arrays.asList	(muestra3));
		List<MuestraI> noEsperado= new ArrayList<MuestraI>(Arrays.asList(muestra1,muestra2));
		
		assertTrue(bFechaCre.buscarMuestra(muestras).containsAll(esperado));
		assertFalse(bFechaCre.buscarMuestra(muestras).containsAll(noEsperado));
	} 
		
	@Test
	public void testBusquedaPorTipoDeInsecto() {
		List<MuestraI> esperado= new ArrayList<MuestraI>(Arrays.asList	(muestra2, muestra1));
		List<MuestraI> noEsperado= new ArrayList<MuestraI>(Arrays.asList(muestra3));
		
		assertTrue(bTipo.buscarMuestra(muestras).containsAll(esperado));
		assertFalse(bTipo.buscarMuestra(muestras).containsAll(noEsperado));
	}
	
	@Test
	public void testBusquedaPorNivelDeVerificacion() {
		//Para esta busqueda se utilizan strings como parametros de busqueda,
		//pero se podria crear una clase enum "filtro estado" para realizar esta busqueda 
		
		List<MuestraI> esperado= new ArrayList<MuestraI>(Arrays.asList	(muestra2, muestra1));
		List<MuestraI> noEsperado= new ArrayList<MuestraI>(Arrays.asList(muestra3));
		
		assertTrue(bVerificada.buscarMuestra(muestras).containsAll(esperado));
		assertFalse(bVerificada.buscarMuestra(muestras).containsAll(noEsperado));
	}
	
	@Test
	public void testBusquedaPorUltimaFechaDeVotacion() {
		List<MuestraI> esperado= new ArrayList<MuestraI>(Arrays.asList	(muestra3, muestra2));
		List<MuestraI> noEsperado= new ArrayList<MuestraI>(Arrays.asList(muestra1));
		
		assertTrue(bFechaUlt.buscarMuestra(muestras).containsAll(esperado));
		assertFalse(bFechaUlt.buscarMuestra(muestras).containsAll(noEsperado));
	}
	
	@Test
	public void testInterseccionDeBusquedas() {
		List<MuestraI> esperado= new ArrayList<MuestraI>(Arrays.asList	(muestra2));
		List<MuestraI> noEsperado= new ArrayList<MuestraI>(Arrays.asList(muestra3, muestra1));
		BAnd interseccion= new BAnd(bFechaUlt,bVerificada);
		
		assertTrue(interseccion.buscarMuestra(muestras).containsAll(esperado));
		assertFalse(interseccion.buscarMuestra(muestras).containsAll(noEsperado));
	}
	
	@Test
	public void testUnionDeBusquedas() {
		List<MuestraI> esperado= new ArrayList<MuestraI>(Arrays.asList	(muestra3,muestra2));
		List<MuestraI> noEsperado= new ArrayList<MuestraI>(Arrays.asList(muestra1));
		BAnd interseccion= new BAnd(bFechaUlt,bVerificada);
		BOr union= new BOr(bFechaUlt,interseccion);
		
		assertTrue(union.buscarMuestra(muestras).containsAll(esperado));
		assertFalse(union.buscarMuestra(muestras).containsAll(noEsperado));
	}
	
}
			
		
		
		
	

