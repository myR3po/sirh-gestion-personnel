package dev.sgp.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditerCollaborateurController extends HttpServlet {

	private static final long serialVersionUID = -2624817299853340666L;
	
	private static final String MATRICULE_PARAM = "matricule";
	private static final String TITRE_PARAM = "titre";
	private static final String NOM_PARAM = "nom";
	private static final String PRENOM_PARAM = "prenom";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String matricule = req.getParameter(MATRICULE_PARAM);
		if (matricule == null || matricule.isEmpty()) {
			resp.sendError(400, "Un matricule est attendu");
		} else {
			resp.setContentType("text/html");
			resp.getWriter().write("<h1>Editer un collaborateur</h1>\r\n" + "<p>Matricule : " + matricule + "</p>\r\n");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String msgError = "";
		
		Map<String, String> results = getParams(req, MATRICULE_PARAM, TITRE_PARAM, NOM_PARAM, PRENOM_PARAM);
		msgError = results.get("ERROR");
		
		if (msgError != null ) {
			resp.sendError(400, "Les paramètres suivants sont incorrects : "+msgError);
		} else {
			resp.setContentType("text/html");
			resp.setStatus(201);
			resp.getWriter().write("<h1>Creation d'un collaborateur avec les informations suivantes :</h1>\r\n" + 
									"<p>" + results.get("SUCCESS") +"</p>\r\n");
		}
	}

	
	private Map<String, String> getParams(HttpServletRequest req, String... params) {
		Map<String, String> results = new HashMap<String, String>();
		String msgError = "";
		String msgSuccess = "";
		String valueParam = null;
		
		for(String param : params) {
			valueParam = req.getParameter(param);
			if (valueParam == null || valueParam.isEmpty()) {
				msgError += " " + param;
			}
			else {
				msgSuccess += param + "=" +valueParam + ","; 
			}
		}
		
		if(!msgError.isEmpty()) {
			results.put("ERROR", msgError);
		}else {
			results.put("SUCCESS", msgSuccess.substring(0,	msgSuccess.length() - 1));
		}
		
		return results;
	}
	
}
