package edu.es.oi.view;

import java.util.Scanner;

import edu.es.eoi.contoller.TutorController;

public class TutorView {

	private static TutorController controller = new TutorController();
	
	public static void menu() {
		System.out.println("GESTIÓN INSTITUTO V1");
		System.out.println("------------ Menú de Tutor ------------");
		System.out.println("1 - Crear tutor");
		System.out.println("2 - Eliminar tutor (DNI)");
		System.out.println("0 - VOLVER");
		System.out.println("----------------------------------------");
		
		try {
			int option = new Scanner(System.in).nextInt();
			controller.menuController(option);
		} catch (Exception e) {
			System.out.println("[ERROR] Opción no válida.");
			menu();
		}
	}
	
	public static void createView() {
		System.out.print("DNI: ");
		
		try {
			String dni = new Scanner(System.in).next();
			
			System.out.println("\nNombre: ");
			String nombre = new Scanner(System.in).next();
			
			System.out.println("\nMail: ");
			String mail = new Scanner(System.in).next();
			
			System.out.println("\nTelefono: ");
			int telefono = new Scanner(System.in).nextInt();
			
			System.out.println(controller.create(dni, nombre, mail, telefono));
		} catch (Exception e) {
			System.out.println("[ERROR] Dato no válido.");
			menu();
		}
		
	}
	
	public static void deleteView() {
		System.out.print("Escribe el DNI del tutor a BORRAR: ");
		
		try {
			String dni = new Scanner(System.in).next();
			
			System.out.println(controller.delete(dni));
		} catch (Exception e) {
			System.out.println("[ERROR] Dato no válido.");
			menu();
		}
	}
}
