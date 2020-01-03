package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Juguete;
import modelo.Usuario;

public class UsuarioDao {
	 
	public static ArrayList<Usuario> getAll(Connection connection) throws Exception {
		try {
			ArrayList<Usuario> l = new ArrayList<Usuario>();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuario");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario(rs.getString("username"), rs.getString("password"), rs.getString("name"),
						rs.getDouble("lat"), rs.getDouble("lon"), rs.getString("preferencias"), rs.getString("pedidos"), 
						rs.getString("carrito"), rs.getInt("rol"));
				l.add(u);
			}
			return l;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Usuario get(Connection connection, String username) throws Exception {
		try {
			Usuario u = null;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuario WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u = new Usuario(rs.getString("username"), rs.getString("password"), rs.getString("name"),
					rs.getDouble("lat"), rs.getDouble("lon"), rs.getString("preferencias"), rs.getString("pedidos"), 
					rs.getString("carrito"), rs.getInt("rol"));
			}
			return u;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Usuario create(Connection connection, Usuario usuario) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO usuario (password, name, lat, lon, preferencias,"
					+ "pedidos, carrito, rol, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
				);
			ps.setString(1, usuario.getPassword());
			ps.setString(2, usuario.getName());
			ps.setDouble(3, usuario.getLat());
			ps.setDouble(4, usuario.getLon());
			ps.setString(5, usuario.getPreferencias());
			ps.setString(6, usuario.getPedidos());
			ps.setString(7, usuario.getCarrito());
			ps.setInt(8, usuario.getRol());
			ps.setString(9, usuario.getUsername());
			ps.executeUpdate();
			
			return get(connection, usuario.getUsername());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Usuario update(Connection connection, Usuario usuario) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE usuario SET password = ?, name = ?, lat = ?, lon = ?, preferencias = ?, "
					+ "pedidos = ?, carrito = ?, rol = ? WHERE username = ?"
				);
			ps.setString(1, usuario.getPassword());
			ps.setString(2, usuario.getName());
			ps.setDouble(3, usuario.getLat());
			ps.setDouble(4, usuario.getLon());
			ps.setString(5, usuario.getPreferencias());
			ps.setString(6, usuario.getPedidos());
			ps.setString(7, usuario.getCarrito());
			ps.setInt(8, usuario.getRol());
			ps.setString(9, usuario.getUsername());
			
			ps.executeUpdate();

			return get(connection, usuario.getUsername());
		} catch (Exception e) {
			throw e;
		}
	}

	public static boolean delete(Connection connection, Usuario usuario) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"DELETE FROM usuario WHERE username = ?"
				);
			ps.setString(1, usuario.getUsername());
			
			int registrosActualizados = ps.executeUpdate();
			if (registrosActualizados == 1)
				return true;
			return false;
		} catch (Exception e) {
			throw e;
		}
	}
}
