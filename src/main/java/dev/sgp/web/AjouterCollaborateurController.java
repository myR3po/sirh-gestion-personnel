package dev.sgp.web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.entite.Collaborateur;
import dev.sgp.entite.Departement;
import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
import dev.sgp.util.Constantes;
import dev.sgp.util.Param;

@WebServlet("/collaborateurs/ajouter")
public class AjouterCollaborateurController extends HttpServlet {

	private static final long serialVersionUID = 2934408506651908717L;

	private final CollaborateurService collabService = Constantes.COLLAB_SERVICE;
	private final DepartementService departemenService = Constantes.DEPARTEMENT_SERVICE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listeDepartements", departemenService.listerDepartements());
		req.getRequestDispatcher("/WEB-INF/views/collab/ajouterCollaborateur.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Map<String, String>> results;
		Map<String, String> errors ;
		Map<String, String> values ;

		results = validParams(req);
		errors = results.get(Param.ERRORS);
		values = results.get(Param.VALUES);
		
		if (!errors.isEmpty()) {

			req.setAttribute("listeDepartements", departemenService.listerDepartements());
			req.setAttribute( Param.VALUES , values );
			req.setAttribute("errors", errors);
			
			resp.setStatus(400);
			req.getRequestDispatcher("/WEB-INF/views/collab/ajouterCollaborateur.jsp").forward(req, resp);
		} else {
			saveCollaborateur(values);
			resp.sendRedirect(req.getContextPath()+"/collaborateurs/lister");
		}
	}
	
	private Map<String, Map<String, String>> validParams(final HttpServletRequest req){
		
		Map<String, String> errors = new HashMap<>();
		Map<String, String> values = new HashMap<>();
		final String champRequis = "Ce champ est requis";
		
		String nom = req.getParameter(Param.NOM) != null ? req.getParameter(Param.NOM).trim() : "";
		String prenom = req.getParameter(Param.PRENOM) != null ? req.getParameter(Param.PRENOM).trim() : "";
		String dob = req.getParameter(Param.DATE_NAISSANCE) != null ? req.getParameter(Param.DATE_NAISSANCE).trim() : "";
		String adresse = req.getParameter(Param.ADRESSE) != null ? req.getParameter(Param.ADRESSE).trim() : "";
		String civilite = req.getParameter(Param.CIVILITE) != null ? req.getParameter(Param.CIVILITE).trim() : "";
		String numeroSecuriteSociale = req.getParameter(Param.NUM_SECU) != null
				? req.getParameter(Param.NUM_SECU).trim()
				: "";

		String intitule = req.getParameter(Param.INTITULE_POSTE) != null ? req.getParameter(Param.INTITULE_POSTE).trim() : "";
		String departementParam = req.getParameter(Param.DEPARTEMENT) != null ? req.getParameter(Param.DEPARTEMENT).trim() : "";
		Departement departement = null;
		
		
		if (civilite.isEmpty()) {
			errors.put(Param.CIVILITE, champRequis );
		} else if (!civilite.equalsIgnoreCase("M") && !civilite.equalsIgnoreCase("F")) {
			errors.put(Param.CIVILITE, "Incorrect" );
		}else {
			values.put(Param.CIVILITE, civilite);
		}
		
		if (nom.isEmpty()) {
			errors.put(Param.NOM, champRequis );
		} else if (!nom.matches("[a-zA-Z]{3,}")) {
			errors.put(Param.NOM, "Entrer un nom correct" );
		} else{
			values.put(Param.NOM, civilite);
		}

		if (prenom.isEmpty()) {
			errors.put(Param.PRENOM, champRequis );
		} else if (!prenom.matches("[a-zA-Z]{3,}")) {
			errors.put(Param.PRENOM, "Entrer un prenom correct" );
		}else {
			values.put(Param.PRENOM, civilite);
		}

		if (dob.isEmpty()) {
			errors.put(Param.DATE_NAISSANCE , champRequis );
		}else {
			values.put(Param.DATE_NAISSANCE, dob);
		}

		if (adresse.isEmpty()) {
			errors.put(Param.ADRESSE , champRequis );
		}else {
			values.put(Param.ADRESSE, adresse);
		}

		if (numeroSecuriteSociale.isEmpty()) {
			errors.put(Param.NUM_SECU , champRequis);
		}else if(!numeroSecuriteSociale.matches("[0-9]{15}")) {
			errors.put(Param.NUM_SECU , "Veuillez entrer un numero de sécurité valide");
		}else {
			values.put(Param.NUM_SECU, numeroSecuriteSociale);
		}
		
		if (intitule.isEmpty()) {
			errors.put(Param.INTITULE_POSTE , champRequis);
		}else if(!intitule.matches("[a-zA-Z ]{4,}")) {
			errors.put(Param.INTITULE_POSTE , "Entrer un intitulé correct");
		}
		
		if (departementParam.isEmpty()) {
			errors.put(Param.DEPARTEMENT , champRequis );
		}
		
		if(errors.isEmpty()) {
			departement = departemenService.trouverDepartementParNom(departementParam);
			
			if(departement == null) {
				errors.put(Param.DEPARTEMENT, "Selectionner un departement");
			}
		}

		
		Map<String, Map<String, String>> results = new HashMap<>();
		results.put(Param.ERRORS, errors);
		results.put(Param.VALUES, values);
		
		return results;
	}
	
	private void saveCollaborateur(Map<String, String> values) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
		String nomDomaineSociete = resourceBundle.getString("societe.domain.name");
		
		LocalDate dateNaissance = LocalDate.parse(values.get(Param.DATE_NAISSANCE));

		Collaborateur collab = new Collaborateur();
		collab.setNom(values.get(Param.NOM));
		collab.setPrenom(values.get(Param.PRENOM));
		collab.setNumeroSecuriteSociale(values.get(Param.NUM_SECU));
		collab.setEmailPro(collab.getPrenom() +"."+ collab.getNom() + "@"+nomDomaineSociete);
		collab.setDateNaissance(dateNaissance);
		collab.setAdresse(values.get(Param.ADRESSE));
		collab.setIntitulePoste(values.get(Param.INTITULE_POSTE));
		collab.setDepartement(departemenService.trouverDepartementParNom(values.get(Param.DEPARTEMENT)));
		
		collabService.sauvegarderCollaborateur(collab);
	}

}
