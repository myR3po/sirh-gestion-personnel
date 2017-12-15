package dev.sgp.util;

import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
import dev.sgp.service.VisiteWebService;

public interface Constantes {
	CollaborateurService COLLAB_SERVICE = new CollaborateurService();
	DepartementService DEPARTEMENT_SERVICE = new DepartementService();
	VisiteWebService VISITE_WEB_SERVICE = new VisiteWebService();
}
