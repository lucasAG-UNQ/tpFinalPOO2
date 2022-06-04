package muestra;

public abstract class EstadoMuestra {

	protected abstract void registrarOpinionNormal(UsuarioI usuario,Muestra muestra);
	protected abstract void registrarOpinionExperta(UsuarioI usuario,Muestra muestra);
	protected abstract void resultadoActual();
	
}
