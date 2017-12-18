package dev.sgp.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

@WebServlet("/collaborateurs/editer")
public class EditerCollaborateurController extends HttpServlet {

	private static final long serialVersionUID = -2624817299853340666L;
	
	private final CollaborateurService collabService = Constantes.COLLAB_SERVICE;
	private final DepartementService departemenService = Constantes.DEPARTEMENT_SERVICE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String matricule = req.getParameter(Param.MATRICULE);
		if (matricule == null || matricule.isEmpty()) {
			resp.sendError(400, "Merci de fournir un matricule");
		} else {

			Collaborateur collaborateur = collabService.trouverCollaborateurParMatricule(matricule);

			if (collaborateur == null) {
				resp.sendError(400, "Ce matricule ne correspond à aucun de nos collaborateurs connus");
			} else {
				req.setAttribute("listeDepartements", departemenService.listerDepartements());
				req.setAttribute("collab", collaborateur);
				req.getRequestDispatcher("/WEB-INF/views/collab/editerCollaborateur.jsp").forward(req, resp);
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, Map<String, String>> results;
		Map<String, String> errors ;
		Map<String, String> values ;
		Collaborateur collaborateur = null;
		String matricule = req.getParameter(Param.MATRICULE);
		
		if(matricule == null || matricule.isEmpty()) {
			resp.sendError(400, "Merci de fournir un matricule");
		}else {
			collaborateur = collabService.trouverCollaborateurParMatricule(matricule);
			
			if(collaborateur != null){
				results = validParams(req);
				errors = results.get(Param.ERRORS);
				values = results.get(Param.VALUES);
						
				if (errors.isEmpty()) {
					updateCollaborateur(collaborateur, values);
					resp.sendRedirect(req.getContextPath()+"/collaborateurs/lister");
				} else {
					req.setAttribute("collab", collaborateur);
					req.setAttribute("listeDepartements", departemenService.listerDepartements());
					req.setAttribute(Param.VALUES, values);
					req.setAttribute(Param.ERRORS, errors);
					resp.setStatus(400);
					req.getRequestDispatcher("/WEB-INF/views/collab/editerCollaborateur.jsp").forward(req, resp);
				}
			}else{
				resp.sendError(400, "Ce matricule ne correspond à aucun de nos collaborateurs connus");
			}
		}
	}
	
	private Map<String, Map<String, String>> validParams(final HttpServletRequest req){
		
		Map<String, String> errors = new HashMap<>();
		Map<String, String> values = new HashMap<>();
		final String champRequis = "Ce champ est requis";
		
		String adresse = req.getParameter(Param.ADRESSE) != null ? req.getParameter(Param.ADRESSE).trim() : "";
		String intitule = req.getParameter(Param.INTITULE_POSTE) != null ? req.getParameter(Param.INTITULE_POSTE).trim() : "";
		String departementParam = req.getParameter(Param.DEPARTEMENT) != null ? req.getParameter(Param.DEPARTEMENT).trim() : "";
		String banque = req.getParameter(Param.BANQUE) != null ? req.getParameter(Param.BANQUE).trim() : "";
		String bic = req.getParameter(Param.BIC) != null ? req.getParameter(Param.BIC).trim() : "";
		String iban = req.getParameter(Param.IBAN) != null ? req.getParameter(Param.IBAN).trim() : "";
		boolean desactive = req.getParameter(Param.DESACTIVE)!= null ? true : false;
		
		if(desactive) {
			values.put(Param.DESACTIVE, "");
		}
		
		if (adresse.isEmpty()) {
			errors.put(Param.ADRESSE, champRequis);
		}else {
			values.put(Param.ADRESSE, adresse);
		}
		
		if(!intitule.isEmpty()) {
			values.put(Param.INTITULE_POSTE, intitule);
		}

		if(!departementParam.isEmpty()) {
			Departement departement = departemenService.trouverDepartementParNom(departementParam);
			
			if(departement == null) {
				errors.put(Param.INTITULE_POSTE, "Selectionner un departement");
			}else {
				values.put(Param.INTITULE_POSTE, departementParam);
			}
		}
		
		if(!banque.isEmpty()) {
			values.put(Param.BANQUE, banque);
			
			if(bic.isEmpty()) {
				errors.put(Param.BIC, "Renseigner le BIC");
			}else {
				values.put(Param.BIC, bic);
			}
			
			if(iban.isEmpty()) {
				errors.put(Param.IBAN, "Renseigner l'IBAN");
			}else {
				values.put(Param.IBAN, iban);
			}
		}else if(banque.isEmpty() && (bic.isEmpty() || iban.isEmpty())) {
			errors.put(Param.BANQUE, "Renseigner la banque");
			
			if(!iban.isEmpty()) {
				values.put(Param.IBAN, iban);				
			}
			
			if(!bic.isEmpty()) {
				values.put(Param.BIC, bic);
			}
		}

		Map<String, Map<String, String>> results = new HashMap<>();
		results.put(Param.ERRORS, errors);
		results.put(Param.VALUES, values);
		
		return results;
	}
		
	private void updateCollaborateur(Collaborateur collaborateur, Map<String, String> values) {

		collaborateur.setAdresse(values.get(Param.ADRESSE));
		
		if(values.containsKey(Param.BIC)) {
		collaborateur.setBic(values.get(Param.BIC));
		}
		
		if(values.containsKey(Param.IBAN)) {
			collaborateur.setIban(values.get(Param.IBAN));
		}
		
		if(values.containsKey(Param.BANQUE)) {
			collaborateur.setBanque(values.get(Param.BANQUE));
		}
		
		if(values.containsKey(Param.INTITULE_POSTE)) {
			collaborateur.setIntitulePoste(values.get(Param.INTITULE_POSTE));
		}
		
		Departement departement = departemenService.trouverDepartementParNom(values.get(Param.DEPARTEMENT));
		collaborateur.setDepartement(departement);

		if(values.containsKey(Param.DESACTIVE)) {
			collaborateur.setActif(false);
		}
	}
	
}
