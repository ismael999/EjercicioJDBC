package edu.es.eoi.contoller;

import edu.es.eoi.entity.Tutor;
import edu.es.eoi.service.TutorService;
import edu.es.eoi.view.MainMenu;
import edu.es.eoi.view.TutorView;

public class TutorController {
	
	// Instanciamos el servicio de Tutor
	private TutorService service = new TutorService();
	
	// Controlador del menú de Tutor
	public void menuController(int option){
		switch (option) {
		case 1:
			TutorView.createView();
			TutorView.menu();
			break;
		case 2:
			TutorView.deleteView();
			TutorView.menu();
			break;
		default:
			MainMenu.menu();
		}
	}
	
	// Crea un tutor pasandole todos los datos necesarios (dni, nombre, mail, telefono).
	public String create(String dni, String nombre, String mail, int telefono) {
		if(service.create(new Tutor(dni, nombre, mail, telefono))) {
			return "Tutor Creado.";
		}else {
			return "El tutor NO se ha creado";
		}
	}
	
	// Borra un Tutor pasando su dni.
	public String delete(String dni) {
	
		if(service.delete(dni)) {
			return "Tutor Borrado.";
		}else {
			return "El tutor NO se ha podido borrar.";
		}
	}
}
