package dev.sgp.web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
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
import dev.sgp.util.Param;

public class AjouterCollaborateurController extends HttpServlet {

	private static final long serialVersionUID = 2934408506651908717L;

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

		String nom = req.getParameter(Param.NOM) != null ? req.getParameter(Param.NOM).trim() : "";
		String prenom = req.getParameter(Param.PRENOM) != null ? req.getParameter(Param.PRENOM).trim() : "";
		String dob = req.getParameter(Param.DATE_NAISSANCE) != null ? req.getParameter(Param.DATE_NAISSANCE).trim() : "";
		String adresse = req.getParameter(Param.ADRESSE) != null ? req.getParameter(Param.ADRESSE).trim() : "";
		String numeroSecuriteSociale = req.getParameter(Param.NUM_SECU) != null
				? req.getParameter(Param.NUM_SECU).trim()
				: "";

		String intitule = req.getParameter(Param.INTITULE_POSTE) != null ? req.getParameter(Param.INTITULE_POSTE).trim() : "";
		String departementParam = req.getParameter(Param.DEPARTEMENT) != null ? req.getParameter(Param.DEPARTEMENT).trim() : "";
		Departement departement = null;
		
		
		String champRequis = "Ce champ est requis";

		Map<String, String> errors = new HashMap<>();
		
		if (nom.isEmpty()) {
			errors.put(Param.NOM, champRequis );
		} else if (!nom.matches("[a-zA-Z]{3,}")) {
			errors.put(Param.NOM, "Entrer un nom correct" );
		}

		if (prenom.isEmpty()) {
			errors.put(Param.PRENOM, champRequis );
		} else if (!prenom.matches("[a-zA-Z]{3,}")) {
			errors.put(Param.PRENOM, "Entrer un prenom correct" );
		}

		if (dob.isEmpty()) {
			errors.put(Param.DATE_NAISSANCE , champRequis );
		}

		if (adresse.isEmpty()) {
			errors.put(Param.ADRESSE , champRequis );
		}

		if (numeroSecuriteSociale.isEmpty()) {
			errors.put(Param.NUM_SECU , champRequis);
		}else if(!numeroSecuriteSociale.matches("[0-9]{15}")) {
			errors.put(Param.NUM_SECU , "Veuillez entrer un numero de sécurité valide");
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

		if (!errors.isEmpty()) {
			req.setAttribute( Param.NOM , nom );
			req.setAttribute( Param.PRENOM , prenom );
			req.setAttribute( Param.DATE_NAISSANCE , dob );
			req.setAttribute( Param.ADRESSE , adresse );
			req.setAttribute( Param.INTITULE_POSTE , intitule );
			req.setAttribute( Param.NUM_SECU , numeroSecuriteSociale );
			req.setAttribute("listeDepartements", departemenService.listerDepartements());
			req.setAttribute("errors", errors);
			
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
