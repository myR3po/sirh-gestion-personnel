package dev.sgp.service;

import java.util.ArrayList;
import java.util.List;

import dev.sgp.entite.Collaborateur;

public class CollaborateurService {
	List<Collaborateur> listeCollaborateurs = new ArrayList<>();

	public List<Collaborateur> listerCollaborateurs() {
		return listeCollaborateurs;
	}

	public void sauvegarderCollaborateur(Collaborateur collab) {
		listeCollaborateurs.add(collab);
	}

	public void mettreAjourCollaborateur(Collaborateur collab) {
		Collaborateur toUpdate = trouverCollaborateurParMatricule(collab.getMatricule());
		if (toUpdate != null) {
			if (toUpdate.getActif() != collab.getActif()) {
				toUpdate.setActif(collab.getActif());
			}

			toUpdate.setAdresse(collab.getAdresse());

			toUpdate.setCivilite(collab.getCivilite());

			toUpdate.setIntitulePoste(collab.getIntitulePoste());
			toUpdate.setDepartement(collab.getDepartement());

			toUpdate.setIban(collab.getIban());
			toUpdate.setBic(collab.getBic());
			toUpdate.setBanque(collab.getBanque());
		}

	}

	public Collaborateur trouverCollaborateurParMatricule(String matricule) {
		return listeCollaborateurs.stream().filter(c -> c.getMatricule().equals(matricule)).findAny().orElse(null);
	}

}