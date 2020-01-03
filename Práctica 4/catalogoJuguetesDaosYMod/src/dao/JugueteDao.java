package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Factura;
import modelo.Juguete;

public class JugueteDao {
	 
	///////////////////////////// CAMBIAR desription POR descripcion!!!!!!!!!!!!!!!!!!!!!!!!///////////////////////////
	////////////////////////////
	///////////////////////
	public static ArrayList<Juguete> getAll(Connection connection, int id) throws Exception {
		try {
			ArrayList<Juguete> l = new ArrayList<Juguete>();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM juguete WHERE almacen = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Juguete j = new Juguete(rs.getInt("id"), rs.getString("nombre"), rs.getString("desripcion"), 
						rs.getInt("minEdadRecomendada"), rs.getDouble("precio"), rs.getInt("almacen"), rs.getInt("unidades"));
				l.add(j);
			}
			return l;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static ArrayList<Juguete> getAll(Connection connection) throws Exception {
		try {
			ArrayList<Juguete> l = new ArrayList<Juguete>();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM juguete");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Juguete j = new Juguete(rs.getInt("id"), rs.getString("nombre"), rs.getString("desripcion"), 
						rs.getInt("minEdadRecomendada"), rs.getDouble("precio"), rs.getInt("almacen"), rs.getInt("unidades"));
				l.add(j);
			}
			return l;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Juguete get(Connection connection, int id) throws Exception {
		try {
			Juguete j = null;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM juguete WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				j = new Juguete(rs.getInt("id"), rs.getString("nombre"), rs.getString("desripcion"), 
						rs.getInt("minEdadRecomendada"), rs.getDouble("precio"), rs.getInt("almacen"), rs.getInt("unidades"));
			}
			return j;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Juguete get(Connection connection, String nombre) throws Exception {
		try {
			Juguete j = null;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM juguete WHERE nombre = ?");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				j = new Juguete(rs.getInt("id"), rs.getString("nombre"), rs.getString("desripcion"), 
						rs.getInt("minEdadRecomendada"), rs.getDouble("precio"), rs.getInt("almacen"), rs.getInt("unidades"));
			}
			return j;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Juguete create(Connection connection, Juguete juguete) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO juguete(nombre, desripcion, minEdadRecomendada,"
					+ "precio, almacen, unidades) VALUES (?, ?, ?, ?, ?, ?)"
				);
			ps.setString(1, juguete.getNombre());
			ps.setString(2, juguete.getDescripcion());
			ps.setInt(3, juguete.getMinEdadRecomendada());
			ps.setDouble(4, juguete.getPrecio());
			ps.setInt(5, juguete.getAlmacen());			
			ps.setInt(6, juguete.getUnidades());
			ps.executeUpdate();
			
			ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			int id=0;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			
			return JugueteDao.get(connection, id);

		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Juguete update(Connection connection, Juguete juguete) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE juguete SET nombre = ?, desripcion = ?, minEdadRecomendada = ?, precio = ?, almacen = ?, "
					+ "unidades = ? WHERE id = ?"
				);
			ps.setString(1, juguete.getNombre());
			ps.setString(2, juguete.getDescripcion());
			ps.setInt(3, juguete.getMinEdadRecomendada());
			ps.setDouble(4, juguete.getPrecio());
			ps.setInt(5, juguete.getAlmacen());			
			ps.setInt(6, juguete.getUnidades());
			ps.setInt(7, juguete.getId());
			
			ps.executeUpdate();

			return get(connection, juguete.getId());
		} catch (Exception e) {
			throw e;
		}
	}

	public static boolean delete(Connection connection, Juguete juguete) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"DELETE FROM juguete WHERE id = ?"
				);
			ps.setInt(1, juguete.getId());
			
			int registrosActualizados = ps.executeUpdate();
			if (registrosActualizados == 1)
				return true;
			return false;
		} catch (Exception e) {
			throw e;
		}
	}
}


