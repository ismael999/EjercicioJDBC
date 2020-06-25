package edu.es.eoi.contoller;

import java.util.List;

import edu.es.eoi.entity.Alumno;
import edu.es.eoi.entity.Curso;
import edu.es.eoi.entity.Tutor;
import edu.es.eoi.service.CursoService;
import edu.es.oi.view.CursoView;
import edu.es.oi.view.MainMenu;

public class CursoController {
	
	private CursoService service = new CursoService();
	
	public void menuController(int option) {
		switch (option) {
		case 1:
			CursoView.createView();
			CursoView.menu();
			break;
		case 2:
			CursoView.deleteView();
			CursoView.menu();
			break;
		case 3:
			CursoView.addAlumnoView();
			CursoView.menu();
			break;
		case 4:
			CursoView.addTutorView();
			CursoView.menu();
			break;
		case 5:
			CursoView.listCursoView();
			CursoView.menu();
		default:
			MainMenu.menu();
		}
	}
	
	public String create(String id) {
		if(service.create(new Curso(id))) {
			return "Curso creado.";
		}else {
			return "Error al crear el curso, es posible que ya exista.";
		}
	}
	
	public String delete(String id) {
		if(service.delete(id)) {
			return "Curso borrado.";
		}else {
			return "Error al borrar el curso, es posible que no exista.";
		}
	}
	
	public String addAlumno(String id_curso, String dni_alumno) {
		if(service.addAlumno(id_curso, dni_alumno)) {
			return "Alumno añadido al curso.";
		}else {
			return "Error al añadir el alumno, es posible que el alumno o el curso no existan.";
		}
	}
	
	public String addTutor(String id, String tutor) {
		if(service.addTutor(id, tutor)) {
			return "Tutor añadido al curso.";
		}else {
			return "Error al añadir el tutor, es posible que el tutor ya tenga asignado un curso o que el tutor o el curso no existan";
		}
	}
	
	public List<Alumno> getAlumnosFromCurso(String id){
		return service.getAlumnos(id);
	}
	
	public Tutor getTutorFromCurso(String id) {
		return service.getTutor(id);
	}
	
	public List<Curso> getAll(){
		return service.findAll();
	}
	
}
