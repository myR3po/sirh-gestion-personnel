package dev.sgp.listener;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import dev.sgp.entite.Collaborateur;
import dev.sgp.entite.Departement;
import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
import dev.sgp.util.Constantes;

public class InitialisationSessionListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		CollaborateurService collabService = Constantes.COLLAB_SERVICE;
		DepartementService departemenService = Constantes.DEPARTEMENT_SERVICE;

		List<Departement> listeDepartements = departemenService.listerDepartements();

		listeDepartements.add(new  Departement(1, "Comptabilite"));
		listeDepartements.add(new  Departement(2, "Ressources Humaines"));
		listeDepartements.add(new  Departement(3, "Informatique"));
		listeDepartements.add(new  Departement(4, "Administratif"));

				
		List<Collaborateur> listeCollaborateurs = collabService.listerCollaborateurs();

		listeCollaborateurs.add(new Collaborateur("Jean", "pierre", LocalDate.of(1985, 5, 16),
				"rue intel, 44000 nantes", "1236548970123654", "pierre.jean@societe.com", "comptable", listeDepartements.get(0), true));
		listeCollaborateurs.add(new Collaborateur("Alfred", "pierre", LocalDate.of(1995, 5, 16),
				"rue intel, 44000 nantes", "1236548970123654", "@societe.com", "developpeur", listeDepartements.get(2), false));
		listeCollaborateurs.add(new Collaborateur("Jean", "kevin", LocalDate.of(1989, 5, 16), "rue intel, 44000 nantes",
				"1236548970123654", "@societe.com", "chef de projet", listeDepartements.get(2), true));
		listeCollaborateurs.add(new Collaborateur("ruth", "alicia", LocalDate.of(1995, 5, 16),
				"rue intel, 44000 nantes", "1236548970123654", "@societe.com", "Charge de recherche", listeDepartements.get(1), false));
		listeCollaborateurs.add(new Collaborateur("john", "doe", LocalDate.of(1975, 5, 16), "rue intel, 44000 nantes",
				"1236548970123654", "@societe.com", "directeur", listeDepartements.get(3), true));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
