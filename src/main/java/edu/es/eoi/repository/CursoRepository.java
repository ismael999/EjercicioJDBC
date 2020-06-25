package edu.es.eoi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.es.eoi.entity.Curso;
import edu.es.eoi.utility.DataBase;

public class CursoRepository {

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

	public Curso findById(String id) {
		Connection conn = openConnection();
		
		Curso curso = null;
		
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM curso WHERE id LIKE ?");
			pst.setString(1, id);
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			curso = new Curso(rs.getString("id"), rs.getString("dni_tutor"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return curso;
		
	}
	
	public boolean create(Curso curso) {
		Connection conn = openConnection();

		int res = 0;

		try {
			PreparedStatement pst = conn.prepareStatement("INSERT INTO curso (id) VALUES (?)");
			pst.setString(1, curso.getId());

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
	
	public boolean delete (String id) {
		Connection conn = openConnection();
		int res1 = 0;
		int res2 = 0;
		try {
			PreparedStatement pst1 = conn.prepareStatement("UPDATE alumno SET id_curso = null WHERE id_curso LIKE ?");
			PreparedStatement pst2 = conn.prepareStatement("DELETE FROM curso WHERE id LIKE ?");
			pst1.setString(1, id);
			pst2.setString(1, id);
			
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

	public boolean addAlumno(String id, String dni_alumno) {
		Connection conn = openConnection();
		int res = 0;
		
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE alumno SET id_curso = ? WHERE dni LIKE ?");
			pst.setString(1, id);
			pst.setString(2, dni_alumno);
			
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
	
	public boolean addTutor (String id, String tutor){
		Connection conn = openConnection();
		int res = 0;
		
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE curso SET dni_tutor = ? WHERE id LIKE ?");
			pst.setString(1, tutor);
			pst.setString(2, id);
			
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
	
	public List<Curso> findAllCurso(){
		Connection conn = openConnection();
		List<Curso> cursos = new ArrayList<Curso>();
		
		try {
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM curso");
			
			while(rs.next()) {
				cursos.add(new Curso(rs.getString("id"), rs.getString("dni_tutor")));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return cursos;
	}
}
