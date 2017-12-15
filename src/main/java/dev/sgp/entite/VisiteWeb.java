package dev.sgp.entite;

public class VisiteWeb {

	private String chemin;
	private Long tempsExecution;
	
	public VisiteWeb() {
		
	}
	
	public VisiteWeb(String chemin, Long tempsExecution) {
		super();
		this.chemin = chemin;
		this.tempsExecution = tempsExecution;
	}

	/**
	 * @return the chemin
	 */
	public String getChemin() {
		return chemin;
	}

	/**
	 * @return the tempsExecution
	 */
	public Long getTempsExecution() {
		return tempsExecution;
	}

}
