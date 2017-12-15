package dev.sgp.web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.entite.Collaborateur;
import dev.sgp.entite.Departement;
import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
import dev.sgp.util.Constantes;

public class AjouterCollaborateurController extends HttpServlet {

	private static final long serialVersionUID = 2934408506651908717L;
		
	private static final String PARAM_NOM = "nom";
	private static final String PARAM_PRENOM = "prenom";
	private static final String PARAM_DATE_NAISSANCE = "dateNaissance";
	private static final String PARAM_ADRESSE = "adresse";
	private static final String PARAM_NUM_SECU = "numeroSecuriteSociale";
	private static final String PARAM_INTITULE_POSTE = "intitule";
	private static final String PARAM_DEPARTEMENT = "departement";
	
	private static final String PARAM_MSG_ERROR_NOM = "nomError";
	private static final String PARAM_MSG_ERROR_PRENOM = "prenomError";
	private static final String PARAM_MSG_ERROR_DATE_NAISSANCE = "dateNaissanceError";
	private static final String PARAM_MSG_ERROR_ADRESSE = "adresseError";
	private static final String PARAM_MSG_ERROR_NUM_SECU = "numeroSecuriteSocialeError";
	private static final String PARAM_MSG_ERROR_INTITULE_POSTE = "intituleError";
	private static final String PARAM_MSG_ERROR_DEPARTEMENT = "departementError";

	private final CollaborateurService collabService = Constantes.COLLAB_SERVICE;
	
	private final DepartementService departemenService = Constantes.DEPARTEMENT_SERVICE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("listeDepartements", departemenService.listerDepartements());
		
		req.getRequestDispatcher("/WEB-INF/views/collab/ajouterCollaborateur.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
		String nomDomaineSociete = resourceBundle.getString("societe.domain.name");
		
		boolean hasError = false;

		String nom = req.getParameter(PARAM_NOM) != null ? req.getParameter(PARAM_NOM).trim() : "";
		String prenom = req.getParameter(PARAM_PRENOM) != null ? req.getParameter(PARAM_PRENOM).trim() : "";
		String dob = req.getParameter(PARAM_DATE_NAISSANCE) != null ? req.getParameter(PARAM_DATE_NAISSANCE).trim() : "";
		String adresse = req.getParameter(PARAM_ADRESSE) != null ? req.getParameter(PARAM_ADRESSE).trim() : "";
		String numeroSecuriteSociale = req.getParameter(PARAM_NUM_SECU) != null
				? req.getParameter(PARAM_NUM_SECU).trim()
				: "";

		String intitule = req.getParameter(PARAM_INTITULE_POSTE) != null ? req.getParameter(PARAM_INTITULE_POSTE).trim() : "";
		String departementParam = req.getParameter(PARAM_DEPARTEMENT) != null ? req.getParameter(PARAM_DEPARTEMENT).trim() : "";
		Departement departement = null;
		
		
		String champRequis = "Ce champ est requis";

		if (nom.isEmpty()) {
			req.setAttribute( PARAM_MSG_ERROR_NOM , champRequis );
			hasError = true;
		} else if (!nom.matches("[a-zA-Z]{3,}")) {
			req.setAttribute( PARAM_MSG_ERROR_NOM , "Entrer un nom correct" );
			hasError = true;
		}

		if (prenom.isEmpty()) {
			req.setAttribute( PARAM_MSG_ERROR_PRENOM , champRequis );
			hasError = true;
		} else if (!prenom.matches("[a-zA-Z]{3,}")) {
			req.setAttribute( PARAM_MSG_ERROR_PRENOM , "Entrer un prenom correct" );
			hasError = true;
		}

		if (dob.isEmpty()) {
			req.setAttribute( PARAM_MSG_ERROR_DATE_NAISSANCE , champRequis );
			hasError = true;
		}

		if (adresse.isEmpty()) {
			req.setAttribute( PARAM_MSG_ERROR_ADRESSE , champRequis );
			hasError = true;
		}

		if (numeroSecuriteSociale.isEmpty()) {
			req.setAttribute( PARAM_MSG_ERROR_NUM_SECU , champRequis);
			hasError = true;
		}else if(!numeroSecuriteSociale.matches("[0-9]{15}")) {
			req.setAttribute( PARAM_MSG_ERROR_NUM_SECU , "Veuillez entrer un numero de sécurité valide");
			hasError = true;
		}
		
		if (intitule.isEmpty()) {
			req.setAttribute( PARAM_MSG_ERROR_INTITULE_POSTE , champRequis);
			hasError = true;
		}else if(!intitule.matches("[a-zA-Z ]{4,}")) {
			req.setAttribute( PARAM_MSG_ERROR_INTITULE_POSTE , "Entrer un intitulé correct");
			hasError = true;
		}
		
		
		if(!hasError) {
			for(Departement depa : departemenService.listerDepartements()) {
				if(depa.getNom().equals(departementParam)) {
					departement = depa;
				}
			}
			
			if(departement == null) {
				req.setAttribute( PARAM_MSG_ERROR_DEPARTEMENT , "Selectionner un departement");
				hasError = true;
			}
		}

		if (hasError) {
			req.setAttribute( PARAM_NOM , nom );
			req.setAttribute( PARAM_PRENOM , prenom );
			req.setAttribute( PARAM_DATE_NAISSANCE , dob );
			req.setAttribute( PARAM_ADRESSE , adresse );
			req.setAttribute( PARAM_INTITULE_POSTE , intitule );
			req.setAttribute("listeDepartements", departemenService.listerDepartements());
			
			resp.setStatus(400);
			req.getRequestDispatcher("/WEB-INF/views/collab/ajouterCollaborateur.jsp").forward(req, resp);
		} else {

			LocalDate dateNaissance = LocalDate.parse(dob);

			Collaborateur collab = new Collaborateur();
			collab.setNom(nom.toUpperCase());
			collab.setPrenom(prenom);
			collab.setNumeroSecuriteSociale(numeroSecuriteSociale);
			collab.setEmailPro(prenom +"."+ nom + "@"+nomDomaineSociete);
			collab.setDateNaissance(dateNaissance);
			collab.setAdresse(adresse);
			collab.setIntitulePoste(intitule);
			collab.setDepartement(departement);
			

			collabService.sauvegarderCollaborateur(collab);
			resp.sendRedirect(req.getContextPath()+"/collaborateurs/lister");
		}
		

	}

}
