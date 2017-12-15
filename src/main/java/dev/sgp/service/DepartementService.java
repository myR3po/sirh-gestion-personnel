package dev.sgp.service;

import java.util.ArrayList;
import java.util.List;

import dev.sgp.entite.Departement;

public class DepartementService {
	List<Departement> listeDepartements = new ArrayList<>();

	public List<Departement> listerDepartements() {
		
		if(listeDepartements.size() == 0) {
			listeDepartements.add(new  Departement(1, "Comptabilite"));
			listeDepartements.add(new  Departement(2, "Ressources Humaines"));
			listeDepartements.add(new  Departement(3, "Informatique"));
			listeDepartements.add(new  Departement(4, "Administratif"));
		}
		
		return listeDepartements;
	}

	public void sauvegarderDepartement(Departement departement) {
		listeDepartements.add(departement);
	}
	
}