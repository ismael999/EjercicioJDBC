package edu.es.eoi.service;

import edu.es.eoi.entity.Tutor;
import edu.es.eoi.repository.TutorRepository;

public class TutorService {

	private TutorRepository repository = new TutorRepository();
	
	public boolean create(Tutor tutor) {
		return repository.create(tutor);
	}
	
	public boolean delete (String dni) {
		return repository.delete(dni);
	}
	
	public Tutor findByDni(String dni) {
		return repository.findByDni(dni);
	}
}
