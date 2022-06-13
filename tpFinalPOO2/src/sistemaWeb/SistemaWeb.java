package sistemaWeb;

import java.util.ArrayList;
import java.util.List;

import busqueda.Buscador;
import muestra.Muestra;
import muestra.MuestraI;
import organizacion.IZonaDeCobertura;
import organizacion.OrganizacionI;
import usuario.UsuarioI;

public class SistemaWeb implements SistemaWebUsuarioI{

	private static SistemaWeb sistema;
	private ArrayList<UsuarioI> usuarios;
	private ArrayList<MuestraI> muestras;
	private Buscador buscador;
	private ArrayList<OrganizacionI> organizaciones;
	private ArrayList<IZonaDeCobertura> zonasDeCobertura;
	
	public ArrayList<UsuarioI> getUsuarios() {
		return usuarios;
	}

	public ArrayList<MuestraI> getMuestras() {
		return muestras;
	}

	public ArrayList<OrganizacionI> getOrganizaciones() {
		return organizaciones;
	}

	public ArrayList<IZonaDeCobertura> getZonasDeCobertura() {
		return zonasDeCobertura;
	}

	public SistemaWeb() {
		this.usuarios= new ArrayList<UsuarioI>();
		this.muestras= new ArrayList<MuestraI>();
		this.organizaciones= new ArrayList<OrganizacionI>();
		this.zonasDeCobertura= new ArrayList<IZonaDeCobertura>();
		this.buscador= Buscador.getInstance();
	}
	
	public static SistemaWeb getInstance() {
		if (sistema==null) {
			sistema= new SistemaWeb();
		}
		
		return sistema;
	}

	@Override
	public Boolean verificar10MuestrasUsuario(UsuarioI usuario) {
		Boolean cumple= this.muestras.stream()
						.filter(m->m.getFuente()==usuario)
						.toList()
						.size()>=10;
						
		return cumple;
	}

	@Override
	public String registrarUsuario(UsuarioI usuario) {
		this.usuarios.add(usuario);
		return "Usuario registrado";
	}


	@Override
	public String registrarMuestra(MuestraI muestra) {
		this.muestras.add(muestra);
		this.notificarNuevaMuestra(muestra);
		return "Muestra registrada";
	}
	
	private void notificarNuevaMuestra(MuestraI muestra) {
		this.zonasDeCobertura.stream()
		.forEach(e->e.addMuestra(muestra));
	}

	public List<MuestraI> muestrasAMenosDe(MuestraI muestra, Double kilometros){
		return muestras.stream()
				.filter(m-> muestra.distanciaHasta(m) < kilometros)
				.toList();
	}

	public String registrarOrganizacion(OrganizacionI organizacion) {
		this.organizaciones.add(organizacion);
		return "Organizacion registrada";
	}

	public String registrarZona(IZonaDeCobertura zona) {
		this.zonasDeCobertura.add(zona);
		return "Zona registrada";
	}

	public void muestraVerificada(MuestraI muestra) {
		this.zonasDeCobertura.stream()
			.forEach(e->e.notificarMuestraValidada(muestra));
	}

}
