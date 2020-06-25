package edu.es.oi.view;

import java.util.List;
import java.util.Scanner;

import edu.es.eoi.contoller.CursoController;
import edu.es.eoi.entity.Alumno;
import edu.es.eoi.entity.Curso;
import edu.es.eoi.entity.Tutor;

public class CursoView {

	private static CursoController controller = new CursoController();
	
	public static void menu() {
		System.out.println("GESTIÓN INSTITUTO V1");
		System.out.println("------------ Menú de Cursos ------------");
		System.out.println("1 - Crear curso");
		System.out.println("2 - Eliminar curso (Nombre Del Curso)");
		System.out.println("3 - Añadir Alumno (DNI)");
		System.out.println("4 - Añadir Tutor (DNI)");
		System.out.println("5 - Lista de Cursos");
		System.out.println("0 - VOLVER");
		System.out.println("----------------------------------------");
		
		try {
			int option = new Scanner(System.in).nextInt();
			controller.menuController(option);
		} catch (Exception e) {
			System.out.println("[ERROR] Opción incorrecta.");
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
		System.out.print("Nombre del curso al que quieres añadir el alumno: ");
		
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
		System.out.print("Nombre del curso al que quieres añadir el tutor: ");
		
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
	
	public static void listCursoView() {
		List<Curso> cursos = controller.getAll();
		System.out.println("\n*************** Cursos ***************");
		for (Curso curso : cursos) {
			String tutor = "Sin tutor";
			if(controller.getTutorFromCurso(curso.getId()) != null) {
				tutor = controller.getTutorFromCurso(curso.getId()).getNombre();
			}
			List<Alumno> alumnos = controller.getAlumnosFromCurso(curso.getId());
			
			System.out.println("> Curso: " + curso.getId() + " | Tutor: " + tutor);
			
			for (Alumno alumno : alumnos) {
				System.out.println("    => Nombre: " + alumno.getNombre() + " " + alumno.getApellidos() + " | DNI: " + alumno.getDni());
			}
			
		}
		System.out.println("**************************************\n");
		
	}
	
}
