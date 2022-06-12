package opinion;

public enum Opinion {

	Infestans,
	Sordida,
	Gusayana,
	ChincheFoliada,
	PhtiaChinche,
	Ninguna,
	ImagenPocoClara;
	
	
	private boolean esOpinionExperta;

	Opinion() {
		this.esOpinionExperta=false;
	}
	
	public Boolean esOpinionExperta() {
		return this.esOpinionExperta;
	}
	
	public void setEsExperta() {
		this.esOpinionExperta=true;
	}

}
