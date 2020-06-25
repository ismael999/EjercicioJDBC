package edu.es.eoi.service;

import java.util.List;

import edu.es.eoi.entity.Alumno;
import edu.es.eoi.repository.AlumnoRepository;

public class AlumnoService {

	private AlumnoRepository repository;

	public AlumnoService() {
		this.repository = new AlumnoRepository();
	}
	
	public Alumno findByDni(String dni) {
		return repository.findByDni(dni);
	}
	
	public List<Alumno> findAll() {
		return repository.findAll();
	}
	
	public boolean create(Alumno alumno)  {
		return repository.create(alumno);
	}
	
	public boolean delete(String dni) {
		return repository.delete(dni);
	}
	
	public boolean update (String dni, String nombre, String apellidos) {
		return repository.update(dni, nombre, apellidos);
	}

}
