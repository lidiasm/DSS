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

import dao.AlmacenDao;
import dao.DbConnection;
import dao.JugueteDao;
import modelo.Almacen;
import modelo.Juguete;

@Path("/juguetes")
public class JugueteRecurso {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() throws Exception {
		List<Juguete> l = JugueteDao.getAll(DbConnection.getConnection());
		String json = new Gson().toJson(l);
	    return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("almacen/{id}")
	public String getAllFromStore(@PathParam("id") String id) throws Exception {
		int ideAlmacen = Integer.parseInt(id);
		Juguete j = JugueteDao.get(DbConnection.getConnection(), ideAlmacen);
		String json = new Gson().toJson(j);
	    return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getOne(
		@PathParam("id") String id
		) throws Exception {
		int identJuguete = Integer.parseInt(id);
		Juguete j = JugueteDao.get(DbConnection.getConnection(), identJuguete);
		String json = new Gson().toJson(j);
	    return json;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String create (String msg) throws Exception {
		JSONObject params = (JSONObject) JSONValue.parse(msg);
		String nombre = (String)params.get("nombre");
		String descripcion = (String)params.get("descripcion");
		String edad = (String)params.get("minEdadRecomendada");
		String precioJuguete = (String)params.get("precio");
		String almacen = (String)params.get("almacen");
		String unidadesJuguete = (String)params.get("unidades");
		// Comprobaciones
		int minimaEdad;
		try {
			minimaEdad = Integer.parseInt(edad);
		} catch (Exception e) {
			minimaEdad = 1;
		}
		double precio;
		try {
			precio = Double.parseDouble(precioJuguete);
		} catch (Exception e) {
			precio = 0;
		}
		int idAlmacen;
		try {
			idAlmacen = Integer.parseInt(almacen);
		} catch (Exception e) {
			idAlmacen = 0;
		}
		int unidades;
		try {
			unidades = Integer.parseInt(unidadesJuguete);
		} catch (Exception e) {
			unidades = 1;
		}

		if (nombre != null && descripcion != null && precio != 0 && idAlmacen != 0) {
			// Comprobamos que no existe un juguete igual en la bd a partir del nombre
			Juguete j = JugueteDao.get(DbConnection.getConnection(), nombre);
			if (j == null) {
				j = new Juguete(nombre, descripcion, minimaEdad, precio, idAlmacen, unidades);
				if (nombre != null)
					j.setNombre(nombre);
				if (descripcion != null)
					j.setDescripcion(descripcion);
				if (minimaEdad > 0)
					j.setMinEdadRecomendada(minimaEdad);
				if (precio > 0)
					j.setPrecio(precio);
				if (idAlmacen > 0)
					j.setAlmacen(idAlmacen);
				if (unidades > 0)
					j.setUnidades(unidades);
				j = JugueteDao.create(DbConnection.getConnection(), j);
	
				return "{\"created\": true}";
			}
		}
		return "{\"created\": false}";
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String update (@PathParam("id") String id, String msg) throws Exception {
		JSONObject params = (JSONObject) JSONValue.parse(msg);
		String nombre = (String)params.get("nombre");
		String descripcion = (String)params.get("descripcion");
		String edad = (String)params.get("minEdadRecomendada");
		String precioJuguete = (String)params.get("precio");
		String almacen = (String)params.get("almacen");
		String unidadesJuguete = (String)params.get("unidades");
		// Comprobaciones
		int minimaEdad;
		try {
			minimaEdad = Integer.parseInt(edad);
		} catch (Exception e) {
			minimaEdad = 1;
		}
		double precio;
		try {
			precio = Double.parseDouble(precioJuguete);
		} catch (Exception e) {
			precio = 0;
		}
		int idAlmacen;
		try {
			idAlmacen = Integer.parseInt(almacen);
		} catch (Exception e) {
			idAlmacen = 0;
		}
		int unidades;
		try {
			unidades = Integer.parseInt(unidadesJuguete);
		} catch (Exception e) {
			unidades = 1;
		}
		
		int ideJuguete = Integer.parseInt(id);
		Juguete j = JugueteDao.get(DbConnection.getConnection(), ideJuguete);
		if (j != null) {
			if (nombre != null)
				j.setNombre(nombre);
			if (descripcion != null)
				j.setDescripcion(descripcion);
			if (minimaEdad > 0)
				j.setMinEdadRecomendada(minimaEdad);
			if (precio > 0)
				j.setPrecio(precio);
			if (idAlmacen > 0)
				j.setAlmacen(idAlmacen);
			if (unidades > 0)
				j.setUnidades(unidades);
			JugueteDao.update(DbConnection.getConnection(), j);
			return "{\"updated\": true}";
		}
		return "{\"updated\": false}";
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
    public String delete(
    		@PathParam("id") String id) throws Exception {
		int ideJuguete = Integer.parseInt(id);
		Juguete j = JugueteDao.get(DbConnection.getConnection(), ideJuguete);
		if (j != null) {
			JugueteDao.delete(DbConnection.getConnection(), j);
			return "{\"deleted\": true}";
		}
		return "{\"deleted\": false}";
	}
}
