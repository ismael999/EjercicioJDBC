package edu.es.eoi.contoller;

import edu.es.eoi.view.AlumnoView;
import edu.es.eoi.view.CursoView;
import edu.es.eoi.view.TutorView;

public class MainMenuController {

	// Controlador del Menú principal.
	public void menuController(int option) {
		
		switch (option) {
		case 1:
			AlumnoView.menu();
			break;
		case 2:
			TutorView.menu();
			break;
		case 3:
			CursoView.menu();
			break;
		default:
			System.out.println("Adiós");
		}
		
	}
	
	
	
}
