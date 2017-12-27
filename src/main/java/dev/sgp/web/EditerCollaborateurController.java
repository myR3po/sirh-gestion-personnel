package dev.sgp.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.entite.Collaborateur;
import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
import dev.sgp.util.Constantes;
import dev.sgp.web.form.CollaborateurForm;

@WebServlet("/collaborateurs/editer")
public class EditerCollaborateurController extends HttpServlet {

	private static final long serialVersionUID = -2624817299853340666L;
	
	private final CollaborateurService collabService = Constantes.COLLAB_SERVICE;
	private final DepartementService departemenService = Constantes.DEPARTEMENT_SERVICE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String matricule = req.getParameter(Constantes.PARAM_MATRICULE);
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Collaborateur collaborateur = null;
		String matricule = req.getParameter(Constantes.PARAM_MATRICULE);
		
		if(matricule == null || matricule.isEmpty()) {
			resp.sendError(400, "Merci de fournir un matricule");
		}else {
			collaborateur = collabService.trouverCollaborateurParMatricule(matricule);
			
			if(collaborateur != null){
				CollaborateurForm collabForm = new CollaborateurForm();
				Collaborateur collabToUpdate = collabForm.updateCollaborateur(req);
				
				if(collabToUpdate.getAdresse() == null || collabToUpdate.getAdresse().isEmpty()) {
					collabToUpdate.setAdresse(collaborateur.getAdresse());
				}

				if (collabForm.getErrors().isEmpty()) {
					collabToUpdate.setMatricule(collaborateur.getMatricule());
					collabService.mettreAjourCollaborateur(collabToUpdate);
					resp.sendRedirect(req.getContextPath()+"/collaborateurs/lister");
				} else {
					req.setAttribute("collab", collaborateur);
					req.setAttribute("collabUpdate", collabToUpdate);
					req.setAttribute("form", collabForm);
					req.setAttribute("listeDepartements", departemenService.listerDepartements());
					resp.setStatus(400);
					req.getRequestDispatcher("/WEB-INF/views/collab/editerCollaborateur.jsp").forward(req, resp);
				}
			}else{
				resp.sendError(400, "Ce matricule ne correspond à aucun de nos collaborateurs connus");
			}
		}
	}

}
