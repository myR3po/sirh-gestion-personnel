package dev.sgp.web;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dev.sgp.entite.Collaborateur;
import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
import dev.sgp.util.Constantes;

public class ListerCollaborateursController extends HttpServlet {
	
	static final long serialVersionUID = 2327864110537760628L;
	
	private final CollaborateurService collabService = Constantes.COLLAB_SERVICE;
	private final DepartementService departemenService = Constantes.DEPARTEMENT_SERVICE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		String nom = req.getParameter("nom") != null ? req.getParameter("nom").trim() : "";
		String departement = req.getParameter("departement")!= null ? req.getParameter("departement").trim() : "";
		boolean notActif = req.getParameter("pasActif")!= null ? true : false;
		
		List<Collaborateur> listeCollaborateurs = collabService.listerCollaborateurs();
		
		if(!nom.isEmpty()) {
			listeCollaborateurs = listeCollaborateurs.stream().filter(c -> c.getNom().startsWith(nom.toUpperCase())).collect(Collectors.toList());
		}
		
		if(!departement.isEmpty()) {
			listeCollaborateurs = listeCollaborateurs.stream().filter(c -> c.getDepartement().getNom().equalsIgnoreCase(departement)).collect(Collectors.toList());
		}
		
		if(notActif) {
			listeCollaborateurs = listeCollaborateurs.stream().filter(c -> c.getActif() == false).collect(Collectors.toList());
		}
		
		
		
		req.setAttribute("listeDepartements", departemenService.listerDepartements());
		req.setAttribute("listeCollabs", listeCollaborateurs);
		
		req.getRequestDispatcher("/WEB-INF/views/collab/listerCollaborateurs.jsp").forward(req, resp);
	}
	
	
}
