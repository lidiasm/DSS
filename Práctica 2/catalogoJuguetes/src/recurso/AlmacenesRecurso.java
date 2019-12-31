package recurso;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;

import modelo.Almacen;
import dao.AlmacenDao;
import recurso.AlmacenRecurso;

@Path("/almacenes")

public class AlmacenesRecurso {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Almacen> getAlmacenesNavegador() {
		return AlmacenDao.INSTANCE.getAlmacenes();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Almacen> getAlmacenesCliente() {
		return (AlmacenDao.INSTANCE.getAlmacenes());
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int tamanio = (AlmacenDao.INSTANCE.getAlmacenes()).size();
		return Integer.toString(tamanio);
	}
	
	@POST
	@Path("crearAlmacenRest")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String newAlmacenRest(
		@FormParam("nombre") String nombre,
		@FormParam("direccion") String direccion,
		@FormParam("latitud") double latitud,
		@FormParam("longitud") double longitud,
		@Context HttpServletResponse servletResponse) throws IOException {
		List<Almacen> almacenesActuales = AlmacenDao.INSTANCE.getAlmacenes();
		boolean existeAlmacen = false;
		String resultado = "";
		for (Almacen almacen: almacenesActuales) {
			if (almacen.getNombre().equals(nombre) && almacen.getDireccion().equals(direccion)
					&& almacen.getLatitud() == latitud && almacen.getLongitud() == longitud) {
				existeAlmacen = true;
				resultado = "El almacén ya existe.";
				break;
			}
		}
		if (!existeAlmacen) {
			resultado = "El almacén se ha insertado correctamente.";
			Almacen nuevoAlmacen = AlmacenDao.INSTANCE.crearAlmacen(nombre, direccion, latitud, longitud);
			if (nuevoAlmacen == null) resultado = "El almacén no se ha podido insertar.";
		}
		
		return resultado;
	}

	@POST
	@Path("crearAlmacenForm")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newAlmacenForm(
		@FormParam("nombre") String nombre,
		@FormParam("direccion") String direccion,
		@FormParam("latitud") double latitud,
		@FormParam("longitud") double longitud,
		@Context HttpServletResponse servletResponse) throws IOException {
		List<Almacen> almacenesActuales = AlmacenDao.INSTANCE.getAlmacenes();
		boolean existeAlmacen = false;
		for (Almacen almacen: almacenesActuales) {
			if (almacen.getNombre().equals(nombre) && almacen.getDireccion().equals(direccion)
					&& almacen.getLatitud() == latitud && almacen.getLongitud() == longitud) {
				existeAlmacen = true;
				break;
			}
		}
		if (!existeAlmacen) {
			AlmacenDao.INSTANCE.crearAlmacen(nombre, direccion, latitud, longitud);
			servletResponse.sendRedirect("../crear_almacen.html");
		}
	}

	@Path("{almacen}")
	public AlmacenRecurso getAlmacen(@PathParam("almacen") String id) {
		return new AlmacenRecurso(uriInfo, request, id);
	}
	
	@DELETE
	@Path("eliminar/{idAlmacen}")
	public String eliminarAlmacen(
		@PathParam("idAlmacen") String id) {
		AlmacenRecurso almacen = new AlmacenRecurso(uriInfo, request, id);
		almacen.eliminarAlmacen();
		return "Almacén borrado correctamente.";
	}
}
