package dev.sgp.entite;

public class Departement {

	private Integer id;
	private String nom;
	
	public Departement() {
	}
	

	public Departement(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	
}
