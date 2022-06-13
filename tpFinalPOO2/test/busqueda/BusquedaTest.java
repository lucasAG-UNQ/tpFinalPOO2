package busqueda;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import muestra.EstadoMuestra;
import muestra.Muestra;
import opinion.Opinion;
import ubicacion.Ubicacion;
import usuario.UsuarioI;

	public class BusquedaTest {
		List<Muestra> muestras;
		Muestra muestra1;
		Muestra muestra2;
		Muestra muestra3;
		Ubicacion ubicacion1;
		Ubicacion ubicacion2;
		Ubicacion ubicacion3;
		UsuarioI usuarioBasicoAna;
		UsuarioI usuarioExpertoMaria;
		UsuarioI usuarioExpertoPedro;
		LocalDate fechaCreacion1;
		LocalDate fechaCreacion2;
		LocalDate fechaCreacion3;
		EstadoMuestra estadoVeri;
		Opinion tipoInfen;
		Opinion ningu;
		BFechaCreacion bFechaCre;
		BFechaUltimaVotacion bFechaUlt;
		BTipoInsecto bTipo;
		BNivelVerifica bVerificada;	
		
		
		@BeforeEach
		public void setUp() {
			muestras.add(muestra1);
			muestras.add(muestra2);
			muestras.add(muestra3);
			muestra1= new Muestra (Opinion.Gusayana,ubicacion1,usuarioBasicoAna);
			muestra2= new Muestra (Opinion.Infestans,ubicacion2,usuarioExpertoMaria);
			muestra3= new Muestra (Opinion.Infestans,ubicacion3,usuarioExpertoPedro);
			fechaCreacion1= LocalDate.of(2022, 01, 30);
			fechaCreacion2= LocalDate.of(2022, 03, 30);
			fechaCreacion3= LocalDate.of(2022, 06, 02);
			bFechaCre= new BFechaCreacion(muestras , LocalDate.of(2022, 01, 30));
			bTipo= new BTipoInsecto (muestras,Opinion.Gusayana);
			bVerificada= new BNivelVerifica (muestras,estadoVeri);
			bFechaUlt = new BFechaUltimaVotacion(muestras);
					
			
		}
		@Test
		public void testPorUltimaFechaCreacion() {
			
			
		} 
}
			
		
		
		
	

