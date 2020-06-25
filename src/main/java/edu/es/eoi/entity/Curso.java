package edu.es.eoi.entity;

public class Curso {

	private String id;
	private String dniTutor;

	public Curso(String id, String dniTutor) {
		this.id = id;
		this.dniTutor = dniTutor;
	}
	
	public Curso(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDniTutor() {
		return dniTutor;
	}

	public void setDniTutor(String dniTutor) {
		this.dniTutor = dniTutor;
	}

}
