package dev.sgp.util;

import java.util.ResourceBundle;

import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
import dev.sgp.service.VisiteWebService;

public interface Constantes {
	CollaborateurService COLLAB_SERVICE = new CollaborateurService();
	DepartementService DEPARTEMENT_SERVICE = new DepartementService();
	VisiteWebService VISITE_WEB_SERVICE = new VisiteWebService();
	
	String PHOTO_MALE = "unknown-man.png";
	String PHOTO_FEMELLE = "unknown-woman.png";
	
	String NOM_DOMAINE_SOCIETE = ResourceBundle.getBundle("application").getString("societe.domain.name");
	
	// le nom des champs dans les formulaires
	String PARAM_MATRICULE = "matricule";
	String PARAM_NOM = "nom";
	String PARAM_PRENOM = "prenom";
	String PARAM_DATE_NAISSANCE = "dateNaissance";
	String PARAM_ADRESSE = "adresse";
	String PARAM_NUM_SECU = "numeroSecuriteSociale";
	String PARAM_INTITULE_POSTE = "intitulePoste";
	String PARAM_DEPARTEMENT = "departement";
	String PARAM_BANQUE = "banque";
	String PARAM_BIC = "bic";
	String PARAM_IBAN = "iban";
	String PARAM_DESACTIVE = "desactive";
	String PARAM_CIVILITE = "civilite";
}
