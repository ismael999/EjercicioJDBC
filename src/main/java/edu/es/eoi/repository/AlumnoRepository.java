package edu.es.eoi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.es.eoi.entity.Alumno;
import edu.es.eoi.utility.DataBase;

public class AlumnoRepository {

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

	public Alumno findByDni(String dni) {
		Connection conn = openConnection();

		ResultSet rs = null;
		Alumno alumno = null;
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM alumno WHERE dni LIKE ?");
			pst.setString(1, dni);

			rs = pst.executeQuery();

			if (rs.next()) {
				alumno = new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad"), rs.getString("id_curso"));
			}

			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return alumno;
	}

	public List<Alumno> findAll() {
		Connection conn = openConnection();

		List<Alumno> alumnos = new ArrayList<Alumno>();
		try {
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM alumno");
			while (rs.next()) {
				alumnos.add(new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getInt("edad"), rs.getString("id_curso")));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return alumnos;
	}

	public boolean create(Alumno alumno) {
		Connection conn = openConnection();

		int res = 0;
		try {
			PreparedStatement pst = conn
					.prepareStatement("INSERT INTO alumno (dni, nombre, apellidos, edad) VALUES (?, ?, ?, ?)");

			pst.setString(1, alumno.getDni());
			pst.setString(2, alumno.getNombre());
			pst.setString(3, alumno.getApellidos());
			pst.setInt(4, alumno.getEdad());

			res = pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		if (res > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean delete(String dni) {
		Connection conn = openConnection();
		PreparedStatement pst = null;
		int res = 0;
		try {
			pst = conn.prepareStatement("DELETE FROM alumno WHERE dni LIKE ?");
			pst.setString(1, dni);

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

	public boolean update(String dni, String nombre, String apellidos) {
		Connection conn = openConnection();

		int res = 0;

		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE alumno SET nombre=?, apellidos=? WHERE dni LIKE ?");

			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setString(3, dni);

			res = pst.executeUpdate();

			conn.close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		if (res > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Alumno> getAlumnosFromCurso(String id){
		Connection conn = openConnection();
		List<Alumno> alumnos = new ArrayList<Alumno>();
		
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM alumno WHERE id_curso LIKE ?");
			pst.setString(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				alumnos.add(new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad")));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return alumnos;
	}
}
