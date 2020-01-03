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
import dao.UsuarioDao;
import modelo.Usuario;

@Path("/usuarios")

public class UsuariosRecurso {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() throws Exception {
		List<Usuario> l = UsuarioDao.getAll(DbConnection.getConnection());
		String json = new Gson().toJson(l);
	    return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{username}")
	public String getOne(
			@PathParam("username") String username
			) throws Exception {
		Usuario u = UsuarioDao.get(DbConnection.getConnection(), username);
		String json = new Gson().toJson(u);
	    return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login/{username}")
	public String login(
			@PathParam("username") String username,
			String msg
			) throws Exception {
		JSONObject params = (JSONObject) JSONValue.parse(msg);
		String password = (String)params.get("password");
		Usuario u = UsuarioDao.get(DbConnection.getConnection(), username);
		if (u != null) {
			if (u.checkPassword(password)) {
				return "{\"login\": true}";
			}
		}
		return "{\"login\": false}";
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String create (String msg) throws Exception {
		JSONObject params = (JSONObject) JSONValue.parse(msg);
		String username = (String)params.get("username");
		String password = (String)params.get("password");
		String name = (String)params.get("name");
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
		String preferencias = (String)params.get("preferencias");
		String pedidos = (String)params.get("pedidos");
		String carrito = (String)params.get("carrito");
		int rol;
		try {
			String rolUsuario = (String)params.get("rol");
			rol = Integer.parseInt(rolUsuario);
		} catch (Exception e) {
			rol = -1;
		}
        
		if (username != null && password != null) {
			Usuario u = UsuarioDao.get(DbConnection.getConnection(), username);
			if (u == null) {
				u = new Usuario(username, password);
				if (name != null)
					u.setName(name);
				if (lat != -999999)
					u.setLat(lat);
				if (lon != -999999)
					u.setLon(lon);
				if (preferencias != null)
					u.setPreferencias(preferencias);
				if (pedidos != null)
					u.setPedidos(pedidos);
				if (carrito != null)
					u.setCarrito(carrito);
				u = UsuarioDao.create(DbConnection.getConnection(), u);
	
				return "{\"created\": true}";
			}
		}
		return "{\"created\": false}";
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{username}")
    public String update(
			@PathParam("username") String username,
			String msg) throws Exception {
		JSONObject params = (JSONObject) JSONValue.parse(msg);
		String password = (String)params.get("password");
		String name = (String)params.get("name");
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
		String preferencias = (String)params.get("preferencias");
		String pedidos = (String)params.get("pedidos");
		String carrito = (String)params.get("carrito");
		int rol;
		try {
			String rolUsuario = (String)params.get("rol");
			rol = Integer.parseInt(rolUsuario);
		} catch (Exception e) {
			rol = -1;
		}
        
		Usuario u = UsuarioDao.get(DbConnection.getConnection(), username);
		if (u != null) {
			if (password != null)
				u.setPassword(password);
			if (name != null)
				u.setName(name);
			if (lat != -999999)
				u.setLat(lat);
			if (lon != -999999)
				u.setLon(lon);
			if (preferencias != null)
				u.setPreferencias(preferencias);
			if (pedidos != null)
				u.setPedidos(pedidos);
			if (carrito != null)
				u.setCarrito(carrito);
			UsuarioDao.update(DbConnection.getConnection(), u);
			return "{\"updated\": true}";
		}
		return "{\"updated\": false}";
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{username}")
    public String delete(
    		@PathParam("username") String username) throws Exception {
		Usuario u = UsuarioDao.get(DbConnection.getConnection(), username);
		if (u != null) {
			UsuarioDao.delete(DbConnection.getConnection(), u);
			return "{\"deleted\": true}";
		}
		return "{\"deleted\": false}";
	}
	
}
