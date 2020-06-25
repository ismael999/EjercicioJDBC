package edu.es.eoi.view;


import java.util.List;
import java.util.Scanner;

import edu.es.eoi.contoller.AlumnoController;
import edu.es.eoi.entity.Alumno;

public class AlumnoView {
	
	private static AlumnoController controller = new AlumnoController();
	
	// Menu principal
	public static void menu(){
		
		System.out.println("GESTIÓN INSTITUTO V1");
		System.out.println("------------ Menú de Alumno ------------");
		System.out.println("1 - Listado Alumnos");
		System.out.println("2 - Buscar Alumno (DNI)");
		System.out.println("3 - Crear Alumno");
		System.out.println("4 - Modificar Alumno");
		System.out.println("5 - Eliminar Alumno");
		System.out.println("0 - VOLVER");
		System.out.println("----------------------------------------");
		
		try {
			int option = new Scanner(System.in).nextInt();
			controller.menuController(option);
		} catch (Exception e) {
			System.out.println("[ERROR] Opción no válido.");
			menu();
		}

		
	}
	
	// Muestra todos los Alumnos
	public static void viewAll(List<Alumno> alumnos) {
		
		System.out.println("****************** Listado de Alumnos ******************");
		
		for (Alumno alumno : alumnos) {
			String curso = null;
			if(alumno.getCurso() == null) {
				curso = "Sin asignar";
			}else {
				curso = alumno.getCurso();
			}
			System.out.println("> Nombre: ".concat(alumno.getNombre()).concat(" ").concat(alumno.getApellidos()).concat(" Edad: ").concat(String.valueOf(alumno.getEdad())).concat(" DNI: ").concat(alumno.getDni()).concat(" Curso: ").concat(curso));
		}
		
		System.out.println("******************");
	}
	
	// Muestra un alumno seleccionado
	public static void viewAlumno() {
		
		System.out.print("Escribe el DNI del alumno: ");
		Alumno alumno = null;
		
		try {
			String dni = new Scanner(System.in).next();
			alumno = controller.findByDni(dni);
		} catch (Exception e) {
			System.out.println("[ERROR] Dato no válido.");
			menu();
		}
		
		if(alumno == null) {
			System.out.println("******************");
			System.out.println("> El alumno no existe");
			System.out.println("******************");
		}else {
			System.out.println("******************");
			String curso = null;
			if(alumno.getCurso() == null) {
				curso = "Sin asignar";
			}else {
				curso = alumno.getCurso();
			}
			System.out.println("> Nombre: ".concat(alumno.getNombre()).concat(" ").concat(alumno.getApellidos()).concat(" Edad: ").concat(String.valueOf(alumno.getEdad())).concat(" DNI: ").concat(alumno.getDni()).concat(" Curso: ").concat(curso));
			System.out.println("******************");
		}
	}
	
	// Vista para crear un Alumno
	public static void viewCreate() {
		try {
			System.out.print("DNI: ");
			String dni = new Scanner(System.in).next();
			
			System.out.print("\nNombre: ");
			String nombre = new Scanner(System.in).next();
			
			System.out.print("\nApellidos: ");
			String apellidos = new Scanner(System.in).next();
			
			System.out.print("\nEdad: ");
			int edad = new Scanner(System.in).nextInt();
			
			System.out.println("******************");
			System.out.println(controller.create(new Alumno(dni, nombre, apellidos, edad)));
			System.out.println("******************");
		} catch (Exception e) {
			System.out.println("[ERROR] Dato no válido.");
			menu();
		}
	}
	
	// Vista para modificar un alumno
	public static void viewModify() {
		try {
			System.out.print("DNI del usuario: ");
			String dni = new Scanner(System.in).next();
			
			System.out.print("\nNuevo nombre: ");
			String nombre = new Scanner(System.in).next();
			
			System.out.print("\nNuevos apellidos: ");
			String apellidos = new Scanner(System.in).next();
			
			System.out.println("******************");
			System.out.println(controller.modify(dni, nombre, apellidos));
			System.out.println("******************");
		} catch (Exception e) {
			System.out.println("[ERROR] Dato no válido.");
			menu();
		}
	}
	
	// Vista para eliminar un Alumno
	public static void viewDelete() {
		try {
			System.out.print("DNI del usuario a BORRAR: ");
			String dni = new Scanner(System.in).next();
			
			System.out.println("******************");
			System.out.println(controller.delete(dni));
			System.out.println("******************");
		} catch (Exception e) {
			System.out.println("[ERROR] Dato no válido.");
			menu();
		}
	}
	
}
