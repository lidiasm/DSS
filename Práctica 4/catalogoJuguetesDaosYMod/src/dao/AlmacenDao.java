package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Almacen;

public class AlmacenDao {
	 
	public static ArrayList<Almacen> getAll(Connection connection) throws Exception {
		try {
			ArrayList<Almacen> l = new ArrayList<Almacen>();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM almacen");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Almacen a = new Almacen(rs.getInt("id"), rs.getString("nombre"), rs.getString("direccion"),
						rs.getDouble("lat"), rs.getDouble("lon"));
				l.add(a);
			}
			return l;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Almacen get(Connection connection, int id) throws Exception {
		try {
			Almacen a = null;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM almacen WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new Almacen(rs.getInt("id"), rs.getString("nombre"), rs.getString("direccion"),
						rs.getDouble("lat"), rs.getDouble("lon"));
			}
			return a;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Almacen get(Connection connection, String nombre) throws Exception {
		try {
			Almacen a = null;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM almacen WHERE nombre = ?");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new Almacen(rs.getInt("id"), rs.getString("nombre"), rs.getString("direccion"),
						rs.getDouble("lat"), rs.getDouble("lon"));
			}
			return a;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Almacen create(Connection connection, Almacen almacen) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO almacen(nombre, direccion, lat, lon)" + 
				"VALUES (?, ?, ?, ?)"
				);
			ps.setString(1, almacen.getNombre());
			ps.setString(2, almacen.getDireccion());
			ps.setDouble(3, almacen.getLat());
			ps.setDouble(4, almacen.getLon());
			ps.executeUpdate();
			ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			int id = 0;
		    if (rs.next()) {
		      id = rs.getInt(1);
		    }
		    return AlmacenDao.get(connection, id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Almacen update(Connection connection, Almacen almacen) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE almacen SET nombre = ?, direccion = ?, lat = ?, lon = ? WHERE id = ?"
				);
			ps.setString(1, almacen.getNombre());
			ps.setString(2, almacen.getDireccion());
			ps.setDouble(3, almacen.getLat());
			ps.setDouble(4, almacen.getLon());
			ps.setInt(5, almacen.getId());
			ps.executeUpdate();

			return get(connection, almacen.getId());
		} catch (Exception e) {
			throw e;
		}
	}

	public static boolean delete(Connection connection, Almacen almacen) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"DELETE FROM almacen WHERE id = ?"
				);
			ps.setInt(1, almacen.getId());
			
			int registrosActualizados = ps.executeUpdate();
			if (registrosActualizados == 1)
				return true;
			return false;
		} catch (Exception e) {
			throw e;
		}
	}
}
