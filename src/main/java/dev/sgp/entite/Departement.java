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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || getClass() != obj.getClass()){
			return false;
		}
		
		Departement other = (Departement) obj;
		
		if (id == null || other.id == null) {
				return false;
		}
		
		if (nom == null || other.nom == null) {
				return false;
		}
		
		return id.equals(other.id) && nom.equals(other.nom);
	}

	
}
