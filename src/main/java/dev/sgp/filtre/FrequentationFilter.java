package dev.sgp.filtre;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import dev.sgp.entite.VisiteWeb;
import dev.sgp.service.VisiteWebService;
import dev.sgp.util.Constantes;

public class FrequentationFilter implements Filter {

	private FilterConfig config;
	private final VisiteWebService visiteService = Constantes.VISITE_WEB_SERVICE;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		long before = System.currentTimeMillis();
		chain.doFilter(request, response);
		long after = System.currentTimeMillis();
		String path = ((HttpServletRequest) request).getRequestURI();
		
		visiteService.sauvegarderCollaborateur(new VisiteWeb(path, (after - before)));
			
		config.getServletContext().log(path + " : " + (after - before));
	}

	@Override
	public void destroy() {
	}

}
