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

import dao.JugueteDao;
import modelo.Juguete;

@Path("/juguete")
public class JugueteRecurso {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	
	public JugueteRecurso(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
		
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Juguete getXMLCliente() {
		Juguete juguete = JugueteDao.INSTANCE.getJuguetes().get(Integer.parseInt(id));
		if (juguete == null) throw new RuntimeException("El juguete con " + id + " no existe.");
		return juguete;
	}

	@GET
	@Produces({ MediaType.TEXT_XML })
	public Juguete getHTMLNavegador() {
		Juguete juguete = JugueteDao.INSTANCE.getJuguetes().get(Integer.parseInt(id));
		if (juguete == null) throw new RuntimeException("El juguete con " + id + " no existe.");
		return juguete;
	}
	
	@DELETE
	@Produces({ MediaType.TEXT_XML, MediaType.TEXT_HTML })
	public void eliminarJuguete() {
		JugueteDao.INSTANCE.eliminarJuguete(id);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response aniadirJuguete(JAXBElement<Juguete> juguete) {
		Juguete nuevoJuguete = juguete.getValue();
		Response respuesta;
		Juguete jugueteBuscado = JugueteDao.INSTANCE.getJuguetes().get(Integer.parseInt(nuevoJuguete.getId()));
		if (jugueteBuscado != null) respuesta = Response.noContent().build();
		else respuesta = Response.created(uriInfo.getAbsolutePath()).build();
		JugueteDao.INSTANCE.crearJuguete("Almacén 1", nuevoJuguete.getNombre(), nuevoJuguete.getDescripcion(),
				nuevoJuguete.getMinEdadRecomendada(), nuevoJuguete.getPrecio());
		return respuesta;
	}
}
