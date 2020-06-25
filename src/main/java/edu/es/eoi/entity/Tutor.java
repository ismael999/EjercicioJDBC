package edu.es.eoi.entity;

public class Tutor {

	private String dni;
	private String nombre;
	private String mail;
	private int telefono;

	public Tutor(String dni, String nombre, String mail, int telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.mail = mail;
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}
