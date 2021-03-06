package edu.es.eoi.contoller;

import edu.es.eoi.entity.Alumno;
import edu.es.eoi.service.AlumnoService;
import edu.es.eoi.view.AlumnoView;
import edu.es.eoi.view.MainMenu;

public class AlumnoController {

	// Instanciamos el servicio de Alumnos
	private AlumnoService service = new AlumnoService();

	// M�todo controlador del menu de Alumnos
	public void menuController(int option) {

		switch (option) {
		case 1:
			AlumnoView.viewAll(service.findAll());
			AlumnoView.menu();
			break;
		case 2:
			AlumnoView.viewAlumno();
			AlumnoView.menu();
			break;
		case 3:
			AlumnoView.viewCreate();
			AlumnoView.menu();
			break;
		case 4:
			AlumnoView.viewModify();
			AlumnoView.menu();
			break;
		case 5:
			AlumnoView.viewDelete();
			AlumnoView.menu();
			break;
		default:
			MainMenu.menu();
		}

	}

	// Busca por dni un Alumno.
	public Alumno findByDni(String dni) {
		return service.findByDni(dni);
	}
	
	// Crea un Alumno pasandole un Objeto de tipo Alumno
	public String create(Alumno alumno) {

		if (service.create(alumno)) {
			return "> Usuario creado.";
		} else {
			return "> Error al crear el usuario, es posible que el alumno ya exista.";
		}

	}

	// Modifica un Alumno pasandole un dni, el nombre y los apellidos
	public String modify(String dni, String nombre, String apellidos) {

		if (service.update(dni, nombre, apellidos)) {
			return "> Usuario actualizado.";
		} else {
			return "> Error al actualizar el usuario, es posible que el DNI introducido sea incorrecto.";
		}

	}

	// Borra un Alumno pasandole un dni.
	public String delete(String dni) {

		if (service.delete(dni)) {
			return "> Usuario borrado.";
		} else {
			return "> Error al borrar el usuario, es probable que el DNI introducido sea incorrecto.";
		}

	}

}
