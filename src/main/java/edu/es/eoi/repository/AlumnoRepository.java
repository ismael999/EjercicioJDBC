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

	// Método que abre la conexión con la base de datos
	private Connection openConnection() {

		Connection con = null;

		try {
			// Los datos de la base de datos están en un ENUM (edu.es.eoi.utility).
			con = DriverManager.getConnection(DataBase.URL.getData(), DataBase.USER.getData(), DataBase.PASS.getData());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return con;
	}

	// Busca un alumno pasando su dni
	public Alumno findByDni(String dni) {
		Connection conn = openConnection();

		ResultSet rs = null;
		Alumno alumno = null;
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM alumno WHERE dni LIKE ?");
			pst.setString(1, dni);

			rs = pst.executeQuery();

			if (rs.next()) {
				alumno = new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getInt("edad"), rs.getString("id_curso"));
			}

			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return alumno;
	}

	// Obtiene todos los alumnos.
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

	// crea un alumno pasandole un Objeto de tipo Alumno.
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

	// Borra un alumno pasandole su dni.
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

	// Actualiza un alumno pasando su dni, el nombre y sus apellidos
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

	// Obtiene todos los alumnos de un curso pasando el id del curso.
	public List<Alumno> getAlumnosFromCurso(String id) {
		Connection conn = openConnection();
		List<Alumno> alumnos = new ArrayList<Alumno>();

		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM alumno WHERE id_curso LIKE ?");
			pst.setString(1, id);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				alumnos.add(new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getInt("edad")));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return alumnos;
	}
}
