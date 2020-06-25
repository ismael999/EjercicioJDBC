package edu.es.eoi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.es.eoi.entity.Tutor;
import edu.es.eoi.utility.DataBase;

public class TutorRepository {

	private Connection openConnection() {

		Connection con = null;

		try {
			con = DriverManager.getConnection(DataBase.URL.getData(), DataBase.USER.getData(), DataBase.PASS.getData());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return con;
	}

	// Obtiene un tutor pasando su dni
	public Tutor findByDni(String dni) {
		Connection conn = openConnection();
		Tutor tutor = null;
		
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM tutor WHERE dni LIKE ?");
			pst.setString(1, dni);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				tutor = new Tutor(rs.getString("dni"), rs.getString("nombre"), rs.getString("mail"), rs.getInt("telefono"));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return tutor;
	}
	
	// Crea un tutor pasando un Objeto de tipo tutor.
	public boolean create(Tutor tutor) {
		Connection conn = openConnection();

		int res = 0;

		try {
			PreparedStatement pst = conn.prepareStatement("INSERT INTO tutor (dni, nombre, mail, telefono) VALUES (?, ?, ?, ?)");
			pst.setString(1, tutor.getDni());
			pst.setString(2, tutor.getNombre());
			pst.setString(3, tutor.getMail());
			pst.setInt(4, tutor.getTelefono());

			res = pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		if (res > 0) {
			return true;
		} else {
			return false;
		}

	}

	// Borra un tutor pasando su dni.
	public boolean delete (String dni) {
		Connection conn = openConnection();
		int res1 = 0;
		int res2 = 0;
		
		try {
			PreparedStatement pst1 = conn.prepareStatement("UPDATE curso SET dni_tutor = null WHERE dni_tutor LIKE ?");
			PreparedStatement pst2 = conn.prepareStatement("DELETE FROM tutor WHERE dni LIKE ?");
			
			pst1.setString(1, dni);
			pst2.setString(1, dni);
			
			res1 = pst1.executeUpdate();
			res2 = pst2.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		if (res1 > 0 && res2 > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// Obtiene el tutor de un curso pasando el id del curso.
	public Tutor getTutorFromCurso(String id) {
		Connection conn = openConnection();
		Tutor tutor = null;
		
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT TU.* FROM tutor AS TU INNER JOIN curso AS CU ON CU.dni_tutor = TU.dni WHERE CU.id LIKE ?");
			pst.setString(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				tutor = new Tutor(rs.getString("dni"), rs.getString("nombre"), rs.getString("mail"), rs.getInt("telefono"));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return tutor;
	}
	
}
