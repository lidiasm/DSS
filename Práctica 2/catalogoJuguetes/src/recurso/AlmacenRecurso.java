package recurso;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import javax.ws.rs.core.Response;

import dao.AlmacenDao;
import modelo.Almacen;

@Path("/almacen")

public class AlmacenRecurso {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	
	public AlmacenRecurso(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
		
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Almacen getXMLCliente() {
		Almacen almacen = AlmacenDao.INSTANCE.getAlmacenes().get(Integer.parseInt(id));
		if (almacen == null) throw new RuntimeException("El almacén con " + id + " no existe.");
		return almacen;
	}

	@GET
	@Produces({ MediaType.TEXT_XML })
	public Almacen getHTMLNavegador() {
		Almacen almacen = AlmacenDao.INSTANCE.getAlmacenes().get(Integer.parseInt(id));
		if (almacen == null) throw new RuntimeException("El almacén con " + id + " no existe.");
		return almacen;
	}
	
	@DELETE
	@Produces({ MediaType.TEXT_XML, MediaType.TEXT_HTML })
	public void eliminarAlmacen() {
		AlmacenDao.INSTANCE.eliminarAlmacen(id);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response aniadirAlmacen(JAXBElement<Almacen> almacen) {
		Almacen nuevoAlmacen = almacen.getValue();
		Response respuesta;
		Almacen almacenBuscado = AlmacenDao.INSTANCE.getAlmacenes().get(Integer.parseInt(nuevoAlmacen.getId()));
		if (almacenBuscado != null) respuesta = Response.noContent().build();
		else respuesta = Response.created(uriInfo.getAbsolutePath()).build();
		AlmacenDao.INSTANCE.crearAlmacen(nuevoAlmacen.getNombre(), nuevoAlmacen.getDireccion(), 
			nuevoAlmacen.getLatitud(), nuevoAlmacen.getLongitud());
		return respuesta;
	}
}
