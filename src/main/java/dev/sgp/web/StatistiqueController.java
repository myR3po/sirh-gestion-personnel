package dev.sgp.web;

import java.io.IOException;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.entite.VisiteWeb;
import dev.sgp.service.VisiteWebService;
import dev.sgp.util.Constantes;

public class StatistiqueController extends HttpServlet {
		
	private static final long serialVersionUID = -7680478807505281242L;
	
	private final VisiteWebService visiteService = Constantes.VISITE_WEB_SERVICE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("listeStats", getStat());
		req.getRequestDispatcher("/WEB-INF/views/statistiques/statistiques.jsp").forward(req, resp);
	}
	
	private Map<String, LongSummaryStatistics> getStat(){
		
		List<VisiteWeb> listVisites = visiteService.listerVisites();
		Map<String, LongSummaryStatistics> results = listVisites.stream().collect(
				Collectors.groupingBy(VisiteWeb::getChemin, 
						Collectors.summarizingLong(VisiteWeb::getTempsExecution))
				);
		
		
		return results;
	}
	
}
