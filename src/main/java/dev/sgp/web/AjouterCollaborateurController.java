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
		
		CollaborateurForm collabForm = new CollaborateurForm();
		Collaborateur collab = collabForm.createCollaborateur(req);
		
		if (collabForm.getErrors().isEmpty()) {
			collabService.sauvegarderCollaborateur(collab);
			resp.sendRedirect(req.getContextPath()+"/collaborateurs/lister");
			
			
		} else {
			req.setAttribute("listeDepartements", departemenService.listerDepartements());
			req.setAttribute( "form" , collabForm );
			req.setAttribute("collab", collab);
			
			resp.setStatus(400);
			req.getRequestDispatcher("/WEB-INF/views/collab/ajouterCollaborateur.jsp").forward(req, resp);
		}
	}

}
