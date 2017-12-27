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

		char male = 'm';
		char femelle = 'f' ;
		
		Collaborateur c = new Collaborateur("jean", "pierre", LocalDate.of(1985, 5, 16), adresse, numeroSecuriteSociale);
		c.setCivilite(male);
		c.setPhoto(Constantes.PHOTO_MALE);
		c.setEmailPro(c.getPrenom() +"."+ c.getNom() + "@"+ Constantes.NOM_DOMAINE_SOCIETE);
		c.setIntitulePoste("comptable");
		c.setDepartement(listeDepartements.get(0));
		listeCollaborateurs.add(c);
		
		c = new Collaborateur("alfred", "pierre", LocalDate.of(1995, 5, 16), adresse, numeroSecuriteSociale);
		c.setCivilite(male);
		c.setPhoto(Constantes.PHOTO_MALE);
		c.setEmailPro(c.getPrenom() +"."+ c.getNom() + "@"+ Constantes.NOM_DOMAINE_SOCIETE);
		c.setIntitulePoste("developpeur");
		c.setDepartement(listeDepartements.get(2));
		c.setActif(false);
		listeCollaborateurs.add(c);
		
		c = new Collaborateur("jean", "kevin", LocalDate.of(1989, 5, 16), adresse, numeroSecuriteSociale);
		c.setCivilite(male);
		c.setPhoto(Constantes.PHOTO_MALE);
		c.setEmailPro(c.getPrenom() +"."+ c.getNom() + "@"+ Constantes.NOM_DOMAINE_SOCIETE);
		c.setIntitulePoste("chef de projet");
		c.setDepartement(listeDepartements.get(2));
		listeCollaborateurs.add(c);
		
		c = new Collaborateur("ruth", "alicia", LocalDate.of(1995, 5, 16), adresse, numeroSecuriteSociale);
		c.setCivilite(femelle);
		c.setPhoto(Constantes.PHOTO_FEMELLE);
		c.setEmailPro(c.getPrenom() +"."+ c.getNom() + "@"+ Constantes.NOM_DOMAINE_SOCIETE);
		c.setIntitulePoste("Charge de recherche");
		c.setDepartement(listeDepartements.get(1));
		c.setActif(false);
		listeCollaborateurs.add(c);
		
		c = new Collaborateur("john", "doe", LocalDate.of(1975, 5, 16), adresse, numeroSecuriteSociale);
		c.setCivilite(male);
		c.setPhoto(Constantes.PHOTO_MALE);
		c.setEmailPro(c.getPrenom() +"."+ c.getNom() + "@"+ Constantes.NOM_DOMAINE_SOCIETE);
		c.setIntitulePoste("directeur");
		c.setDepartement(listeDepartements.get(3));
		listeCollaborateurs.add(c);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Obligation de rédéfinir cette methode à cause de l'interface ServletContextListener
	}

}
