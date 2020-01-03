package recurso;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.util.List;

import dao.DbConnection;
import dao.AlmacenDao;
import modelo.Almacen;

@Path("/almacenes")
public class AlmacenRecurso {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() throws Exception {
		List<Almacen> l = AlmacenDao.getAll(DbConnection.getConnection());
		String json = new Gson().toJson(l);
	    return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getOne(
		@PathParam("id") String id
		) throws Exception {
		int identAlmacen = Integer.parseInt(id);
		Almacen a = AlmacenDao.get(DbConnection.getConnection(), identAlmacen);
		String json = new Gson().toJson(a);
	    return json;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String create (String msg) throws Exception {
		JSONObject params = (JSONObject) JSONValue.parse(msg);
		String nombre = (String)params.get("nombre");
		String direccion = (String)params.get("direccion");
		double lat;
		try {
			String latitud = (String)params.get("lat");
			lat = Double.parseDouble(latitud);
		} catch (Exception e) {
			lat = -999999;
		}
		double lon;
		try {
			String longitud = (String)params.get("lon");
			lon = Double.parseDouble(longitud);
		} catch (Exception e) {
			lon = -999999;
		}

		if (nombre != null && direccion != null) {
			// Comprobamos que no existe un almacén igual en la bd a partir del nombre
			Almacen a = AlmacenDao.get(DbConnection.getConnection(), nombre);
			if (a == null) {
				a = new Almacen(nombre, direccion, lat, lon);
				if (nombre != null)
					a.setNombre(nombre);
				if (direccion != null)
					a.setDireccion(direccion);
				if (lat != -999999)
					a.setLat(lat);
				if (lon != -999999)
					a.setLon(lon);
				a = AlmacenDao.create(DbConnection.getConnection(), a);
	
				return "{\"created\": true}";
			}
		}
		return "{\"created\": false}";
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
    public String update(
			@PathParam("id") String id,
			String msg) throws Exception {
		JSONObject params = (JSONObject) JSONValue.parse(msg);
		String nombre = (String)params.get("nombre");
		String direccion = (String)params.get("direccion");
		double lat;
		try {
			String latitud = (String)params.get("lat");
			lat = Double.parseDouble(latitud);
		} catch (Exception e) {
			lat = -999999;
		}
		double lon;
		try {
			String longitud = (String)params.get("lon");
			lon = Double.parseDouble(longitud);
		} catch (Exception e) {
			lon = -999999;
		}
		int ideAlmacen = Integer.parseInt(id);
		Almacen a = AlmacenDao.get(DbConnection.getConnection(), ideAlmacen);
		if (a != null) {
			if (nombre != null)
				a.setNombre(nombre);
			if (direccion != null)
				a.setDireccion(direccion);
			if (lat != -999999)
				a.setLat(lat);
			if (lon != -999999)
				a.setLon(lon);

			AlmacenDao.update(DbConnection.getConnection(), a);
			return "{\"updated\": true}";
		}
		return "{\"updated\": false}";
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
    public String delete(
    		@PathParam("id") String id) throws Exception {
		int ideAlmacen = Integer.parseInt(id);
		Almacen a = AlmacenDao.get(DbConnection.getConnection(), ideAlmacen);
		if (a != null) {
			AlmacenDao.delete(DbConnection.getConnection(), a);
			return "{\"deleted\": true}";
		}
		return "{\"deleted\": false}";
	}
}
