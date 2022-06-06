package sistemaWeb;

public class SistemaWeb implements SistemaWebUsuarioI{

	private static SistemaWeb sistema;
	
	
	public static SistemaWebUsuarioI getInstance() {
		if (sistema==null) {
			sistema= new SistemaWeb();
		}
		
		return sistema;
	}

}
