package opinion;

import java.time.LocalDate;

public enum Opinion implements OpinionI{

	Infestans,
	Sordida,
	Gusayana,
	ChincheFoliada,
	PhtiaChinche,
	Ninguna,
	ImagenPocoClara;
	
	
	private boolean esOpinionExperta;
	private LocalDate fechaOpinion;

	Opinion() {
		this.esOpinionExperta=false;
		this.fechaOpinion= LocalDate.now();
	}
	
	public Boolean esOpinionExperta() {
		return this.esOpinionExperta;
	}
	
	public void setEsExperta() {
		this.esOpinionExperta=true;
	}

	/**
	 * Mensaje creado con el fin de testear
	 * @param fecha
	 */
	public void setFechaOpinion(LocalDate fecha) {
		this.fechaOpinion=fecha;
	}

	/**
	 * Mensaje creado con el fin de testear
	 * @return
	 */
	public LocalDate getFechaOpinion() {
		return this.fechaOpinion;
	}
	
}
