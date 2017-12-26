package dev.sgp.listener;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dev.sgp.entite.Collaborateur;
import dev.sgp.entite.Departement;
import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
import dev.sgp.util.Constantes;

@WebListener
public class InitialisationSessionListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		CollaborateurService collabService = Constantes.COLLAB_SERVICE;
		DepartementService departemenService = Constantes.DEPARTEMENT_SERVICE;

		List<Departement> listeDepartements = departemenService.listerDepartements();

		listeDepartements.add(new Departement(1, "Comptabilite"));
		listeDepartements.add(new Departement(2, "Ressources Humaines"));
		listeDepartements.add(new Departement(3, "Informatique"));
		listeDepartements.add(new Departement(4, "Administratif"));

		List<Collaborateur> listeCollaborateurs = collabService.listerCollaborateurs();

		String adresse = "rue intel, 44000 nantes";
		String numeroSecuriteSociale = "1236548970123654";

		listeCollaborateurs.add(new Collaborateur("jean", "pierre", LocalDate.of(1985, 5, 16), adresse,
				numeroSecuriteSociale, "pierre.jean@societe.com", "comptable", listeDepartements.get(0), true));
		
		listeCollaborateurs.add(new Collaborateur("alfred", "pierre", LocalDate.of(1995, 5, 16), adresse,
				numeroSecuriteSociale, "pierre.alfred@societe.com", "developpeur", listeDepartements.get(2), false));
		
		listeCollaborateurs.add(new Collaborateur("jean", "kevin", LocalDate.of(1989, 5, 16), adresse,
				numeroSecuriteSociale, "kevin.jean@societe.com", "chef de projet", listeDepartements.get(2), true));
		
		listeCollaborateurs.add(new Collaborateur("ruth", "alicia", LocalDate.of(1995, 5, 16), adresse, numeroSecuriteSociale,
						"alicia.ruth@societe.com", "Charge de recherche", listeDepartements.get(1), false));
		
		listeCollaborateurs.add(new Collaborateur("john", "doe", LocalDate.of(1975, 5, 16), adresse,
				numeroSecuriteSociale, "doe.john@societe.com", "directeur", listeDepartements.get(3), true));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Obligation de rédéfinir cette methode à cause de l'interface ServletContextListener
	}

}
