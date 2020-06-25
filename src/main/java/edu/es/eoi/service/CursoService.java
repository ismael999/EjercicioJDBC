package edu.es.eoi.service;

import java.util.List;

import edu.es.eoi.entity.Alumno;
import edu.es.eoi.entity.Curso;
import edu.es.eoi.entity.Tutor;
import edu.es.eoi.repository.AlumnoRepository;
import edu.es.eoi.repository.CursoRepository;
import edu.es.eoi.repository.TutorRepository;

public class CursoService {

	private CursoRepository repository = new CursoRepository();
	private AlumnoRepository alumnoRepository = new AlumnoRepository();
	private TutorRepository tutorRepository = new TutorRepository();

	public boolean create(Curso curso) {
		return repository.create(curso);
	}
	
	public boolean delete(String id) {
		return repository.delete(id);
	}
	
	public boolean addAlumno(String id, String alumno) {
		return repository.addAlumno(id, alumno);
	}

	public boolean addTutor(String id, String tutor) {
		return repository.addTutor(id, tutor);
	}
	
	public List<Curso> findAll() {
		return repository.findAllCurso();
	}
	
	public Curso findById(String id) {
		return repository.findById(id);
	}
	
	public Tutor getTutor(String id) {
		return tutorRepository.getTutorFromCurso(id);
	}
	
	public List<Alumno> getAlumnos(String id){
		return alumnoRepository.getAlumnosFromCurso(id);
	}
}
