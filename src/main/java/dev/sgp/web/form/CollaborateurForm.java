package dev.sgp.web.form;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import dev.sgp.entite.Collaborateur;
import dev.sgp.entite.Departement;
import dev.sgp.service.DepartementService;
import static dev.sgp.util.Constantes.*;
public class CollaborateurForm {

	private final DepartementService departemenService = DEPARTEMENT_SERVICE;
	
	private Map<String, String> errors;
	private final String champRequis = "Ce champ est requis";
	
	public CollaborateurForm() {
		errors = new HashMap<>();
	}
	
	public Collaborateur createCollaborateur(final HttpServletRequest req){
		
		String nom = getValeurChamp(req, PARAM_NOM);
		String prenom = getValeurChamp(req, PARAM_PRENOM);
		String dob = getValeurChamp(req, PARAM_DATE_NAISSANCE);
		String adresse = getValeurChamp(req, PARAM_ADRESSE);
		String civilite = getValeurChamp(req, PARAM_CIVILITE);
		String numeroSecuriteSociale = getValeurChamp(req, PARAM_NUM_SECU);
		String intitule = getValeurChamp(req, PARAM_INTITULE_POSTE);
		String departementParam = getValeurChamp(req, PARAM_DEPARTEMENT);
		
		Departement departement = null;
		
		Collaborateur collab = new Collaborateur();
		
		try {
			validationCivilite(civilite);
			collab.setCivilite(civilite.toLowerCase().charAt(0));
		} catch (Exception e) {
			errors.put(PARAM_CIVILITE, e.getMessage() );
		}
		
				
		try {
			validationNomEtPrenom(nom);
			collab.setNom(nom.toLowerCase());
		} catch (Exception e) {
			errors.put(PARAM_NOM, e.getMessage() );
		}
		
		try {
			validationNomEtPrenom(prenom);
			collab.setPrenom(prenom.toLowerCase());
		} catch (Exception e) {
			errors.put(PARAM_PRENOM, e.getMessage() );
		}
		
		try {
			validationDateNaissance(dob);
			collab.setDateNaissance(LocalDate.parse(dob));
		} catch (Exception e) {
			errors.put(PARAM_DATE_NAISSANCE , e.getMessage() );
		}

		if (adresse == null) {
			errors.put(PARAM_ADRESSE , champRequis );
		}else {
			collab.setAdresse(adresse);
		}
		
		try {
			validationNumeroSecuriteSociale(numeroSecuriteSociale);
			collab.setNumeroSecuriteSociale(numeroSecuriteSociale);
		} catch (Exception e) {
			errors.put(PARAM_NUM_SECU , e.getMessage());
		}
		
		if (intitule == null) {
			errors.put(PARAM_INTITULE_POSTE , champRequis);
		}else if(!intitule.matches("[a-zA-Z ]{4,}")) {
			errors.put(PARAM_INTITULE_POSTE , "Entrer un intitulé correct");
		}else {
			collab.setIntitulePoste(intitule);
		}
		
		if (departementParam == null) {
			errors.put(PARAM_DEPARTEMENT , champRequis );
		}
		
		if(errors.isEmpty()) {
			departement = departemenService.trouverDepartementParNom(departementParam);
			
			if(departement == null) {
				errors.put(PARAM_DEPARTEMENT, "Selectionner un departement");
			}else {
				collab.setDepartement(departement);
				collab.setEmailPro(collab.getPrenom() +"."+ collab.getNom() + "@"+ NOM_DOMAINE_SOCIETE);
				setPhotoCollaborateur(collab);
			}
		}
		
		return collab;
	}

	private void validationNomEtPrenom(String champ) throws Exception {
		if (champ == null) {
			throw new Exception(champRequis);
		} else if (!champ.matches("[a-zA-Z]{3,}")) {
			throw new Exception("Champ incorrect");
		}
	}
	
	private void validationCivilite(String civilite) throws Exception {
		if (civilite == null) {
			throw new Exception( champRequis );
		} else if (!civilite.equalsIgnoreCase("M") && !civilite.equalsIgnoreCase("F")) {
			throw new Exception("Incorrect" );
		}
	}
	
	private void validationNumeroSecuriteSociale(String numSecu) throws Exception {
		if (numSecu == null) {
			throw new Exception(champRequis);
		}else if(!numSecu.matches("[0-9]{15}")) {
			throw new Exception("Veuillez entrer un numero de sécurité valide");
		}
	}
	
	private void validationDateNaissance(String dateNaissance) throws Exception{
		if (dateNaissance == null) {
			throw new Exception(champRequis);
		}else {
			try {
			LocalDate.parse(dateNaissance);
			}catch(DateTimeParseException dtpe) {
				throw new Exception("Date incorrecte");
			}
		}
	}
	
	private String getValeurChamp(final HttpServletRequest req, final String champ ) {
		String valeur = req.getParameter(champ);
		return (valeur == null || valeur.trim().length() == 0)? null: valeur;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public void setPhotoCollaborateur(Collaborateur collab) {
		if(collab.getCivilite() == 'm') {
			collab.setPhoto(PHOTO_MALE);
		}else {
			collab.setPhoto(PHOTO_FEMELLE);
		}
	}
	
	public Collaborateur updateCollaborateur(final HttpServletRequest req){
				
		String civilite = getValeurChamp(req, PARAM_CIVILITE);
		String adresse = getValeurChamp(req, PARAM_ADRESSE);
		String intitule = getValeurChamp(req, PARAM_INTITULE_POSTE);
		String departementParam = getValeurChamp(req, PARAM_DEPARTEMENT);
		String banque = getValeurChamp(req, PARAM_BANQUE);
		String bic = getValeurChamp(req, PARAM_BIC);
		String iban = getValeurChamp(req, PARAM_IBAN);
		
		Boolean desactive = req.getParameter(PARAM_DESACTIVE)!= null ? true : false;
		
		Collaborateur collab = new Collaborateur();
		
		collab.setActif(!desactive);
		
		if (civilite != null && (civilite.equalsIgnoreCase("M") || civilite.equalsIgnoreCase("F"))) {
			collab.setCivilite(civilite.toLowerCase().charAt(0));
		}
		
		if (adresse == null) {
			errors.put(PARAM_ADRESSE, champRequis);
		}else {
			collab.setAdresse(adresse);
		}
		
		if(intitule != null) {
			if(!intitule.matches("[a-zA-Z ]{4,}")) {
				errors.put(PARAM_INTITULE_POSTE , "Entrer un intitulé correct");
			}else {
				collab.setIntitulePoste(intitule);
			}
		}
		
		if(departementParam != null) {
			Departement departement = departemenService.trouverDepartementParNom(departementParam);
			
			if(departement == null) {
				errors.put(PARAM_DEPARTEMENT, "Selectionner un departement");
			}else {
				collab.setDepartement(departement);
			}
		}
		
		if(banque != null) {
			collab.setBanque(banque);
			
			if(bic == null) {
				errors.put(PARAM_BIC, "Renseigner le BIC");
			}else {
				collab.setBic(bic);
			}
			
			if(iban == null) {
				errors.put(PARAM_IBAN, "Renseigner l'IBAN");
			}else {
				collab.setIban(iban);
			}
		}else if((bic != null || iban != null) && banque == null) {
			errors.put(PARAM_BANQUE, "Renseigner la banque");
			
			if(iban != null) {
				collab.setIban(iban);				
			}
			
			if(bic != null) {
				collab.setBic(bic);
			}
		}

		if(errors.isEmpty()) {
			setPhotoCollaborateur(collab);
		}
		
		return collab;
	}	
	
}
