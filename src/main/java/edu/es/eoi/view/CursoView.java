package edu.es.eoi.view;

import java.util.List;
import java.util.Scanner;

import edu.es.eoi.contoller.CursoController;
import edu.es.eoi.entity.Alumno;
import edu.es.eoi.entity.Curso;
import edu.es.eoi.entity.Tutor;

public class CursoView {

	private static CursoController controller = new CursoController();
	
	public static void menu() {
		System.out.println("GESTI�N INSTITUTO V1");
		System.out.println("------------ Men� de Cursos ------------");
		System.out.println("1 - Crear curso");
		System.out.println("2 - Eliminar curso (Nombre Del Curso)");
		System.out.println("3 - A�adir Alumno (DNI)");
		System.out.println("4 - A�adir Tutor (DNI)");
		System.out.println("5 - Lista de Cursos");
		System.out.println("0 - VOLVER");
		System.out.println("----------------------------------------");
		
		try {
			int option = new Scanner(System.in).nextInt();
			controller.menuController(option);
		} catch (Exception e) {
			System.out.println("[ERROR] Opci�n incorrecta.");
			menu();
		}
	}
	
	public static void createView() {
		System.out.print("Nombre del nuevo curso: ");
		try {
			String nombre = new Scanner(System.in).nextLine();
			System.out.println(controller.create(nombre));
		} catch (Exception e) {
			System.out.println("[ERROR] Dato incorrecta.");
			menu();
		}
	}
	
	public static void deleteView() {
		System.out.print("Nombre del curso a BORRAR: ");
		
		try {
			String nombre = new Scanner(System.in).nextLine();
			System.out.println(controller.delete(nombre));
		} catch (Exception e) {
			System.out.println("[ERROR] Dato incorrecta.");
			menu();
		}
	}
	
	public static void addAlumnoView() {
		System.out.print("Nombre del curso al que quieres a�adir el alumno: ");
		
		try {
			String nombre = new Scanner(System.in).nextLine();
			
			System.out.print("DNI del Alumno: ");
			String nombreAlu = new Scanner(System.in).nextLine();
			
			System.out.println(controller.addAlumno(nombre, nombreAlu));
		} catch (Exception e) {
			System.out.println("[ERROR] Dato incorrecta.");
			menu();
		}
	}
	
	public static void addTutorView() {
		System.out.print("Nombre del curso al que quieres a�adir el tutor: ");
		
		try {
			String nombre = new Scanner(System.in).nextLine();
			
			System.out.print("DNI del Tutor: ");
			String nombreTutor = new Scanner(System.in).nextLine();
			
			System.out.println(controller.addTutor(nombre, nombreTutor));
		} catch (Exception e) {
			System.out.println("[ERROR] Dato incorrecta.");
			menu();
		}
	}
	
	// Listar todos los cursos.
	public static void listCursoView() {
		// 1� Obtenemos todos los cursos
		List<Curso> cursos = controller.getAll();
		System.out.println("\n*************** Cursos ***************");
		for (Curso curso : cursos) {
			// 2� Obtenemos el tutor del curso.
			Tutor tutor = controller.getTutorFromCurso(curso.getId());
			String nombreTutor = "Sin tutor";
			// Si el curso no tiene tutor mostraremos "Sin tutor".
			if(tutor != null) {
				nombreTutor = controller.getTutorFromCurso(curso.getId()).getNombre();
			}
			// 3� Obtenemos la lista de los alumnos del curso
			List<Alumno> alumnos = controller.getAlumnosFromCurso(curso.getId());
			
			System.out.println("> Curso: " + curso.getId() + " | Tutor: " + nombreTutor);
			
			for (Alumno alumno : alumnos) {
				System.out.println("    => Nombre: " + alumno.getNombre() + " " + alumno.getApellidos() + " | DNI: " + alumno.getDni());
			}
			
		}
		System.out.println("**************************************\n");
		
	}
	
}
