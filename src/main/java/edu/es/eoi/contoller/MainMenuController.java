package edu.es.eoi.contoller;

import edu.es.oi.view.AlumnoView;
import edu.es.oi.view.CursoView;
import edu.es.oi.view.TutorView;

public class MainMenuController {

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
