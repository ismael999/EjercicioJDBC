package edu.es.eoi.view;

import java.util.Scanner;

import edu.es.eoi.contoller.MainMenuController;

public class MainMenu {

	private static MainMenuController controller = new MainMenuController();
	
	public static void menu() {
		System.out.println("GESTIÓN INSTITUTO V1");
		System.out.println("-----------------------");
		System.out.println("1 - Menu de Alumnos");
		System.out.println("2 - Menu de Tutores");
		System.out.println("3 - Menu de Cursos");
		System.out.println("0 - SALIR");
		System.out.println("-----------------------");
		
		try {
			int option = new Scanner(System.in).nextInt();
			
			controller.menuController(option);
		} catch (Exception e) {
			System.out.println("[ERROR] Opción no válida.");
			menu();
		}
	}
	
	
	
}
