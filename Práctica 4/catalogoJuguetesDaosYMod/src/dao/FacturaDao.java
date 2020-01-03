package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Almacen;
import modelo.Factura;

public class FacturaDao {

	public static ArrayList<Factura> getAll(Connection connection) throws Exception {
		try {
			ArrayList<Factura> l = new ArrayList<Factura>();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM factura");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Factura f = new Factura(rs.getInt("id"), rs.getDouble("precio"), rs.getBoolean("pagado"),
						rs.getString("idJuguetesComprados"), rs.getString("fechaEmision"));
				l.add(f);
			}
			return l;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Factura get(Connection connection, int id) throws Exception {
		try {
			Factura f = null;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM factura WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				f = new Factura(rs.getInt("id"), rs.getDouble("precio"), rs.getBoolean("pagado"),
						rs.getString("idJuguetesComprados"), rs.getString("fechaEmision"));
			}
			return f;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Factura create(Connection connection, Factura factura) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO factura(precio, pagado, idJuguetesComprados, fechaEmision)" + 
				"VALUES (?, ?, ?, ?)"
				);
			ps.setDouble(1, factura.getPrecio());
			ps.setBoolean(2, factura.getPagado());
			ps.setString(3, factura.getIdJuguetesComprados());
			ps.setString(4, factura.getFechaEmision());
			ps.executeUpdate();
			ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			int id = 0;
		    if (rs.next()) {
		      id = rs.getInt(1);
		    }

		    return FacturaDao.get(connection, id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Factura update(Connection connection, Factura factura) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE factura SET precio = ?, pagado = ?, idJuguetesComprados = ?, fechaEmision = ?  WHERE id = ?"
				);
			ps.setDouble(1, factura.getPrecio());
			ps.setBoolean(2, factura.getPagado());
			ps.setString(3, factura.getIdJuguetesComprados());
			ps.setString(4, factura.getFechaEmision());
			ps.executeUpdate();

			return get(connection, factura.getId());
		} catch (Exception e) {
			throw e;
		}
	}

	public static boolean delete(Connection connection, Factura factura) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"DELETE FROM factura WHERE id = ?"
				);
			ps.setInt(1, factura.getId());
			
			int registrosActualizados = ps.executeUpdate();
			if (registrosActualizados == 1)
				return true;
			return false;
		} catch (Exception e) {
			throw e;
		}
	}
}
