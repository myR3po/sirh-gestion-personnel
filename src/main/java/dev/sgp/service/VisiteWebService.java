package dev.sgp.service;

import java.util.ArrayList;
import java.util.List;
import dev.sgp.entite.VisiteWeb;

public class VisiteWebService {
	List<VisiteWeb> listeVisites = new ArrayList<>();

	public List<VisiteWeb> listerVisites() {
		return listeVisites;
	}

	public void sauvegarderCollaborateur(VisiteWeb visite) {	
		listeVisites.add(visite);
	}
}