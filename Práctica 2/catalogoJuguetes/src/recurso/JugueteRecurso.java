package recurso;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import dao.JugueteDao;
import modelo.Juguete;

@Path("/juguete")
public class JugueteRecurso {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	int id;
	
	public JugueteRecurso(UriInfo uriInfo, Request request, int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Juguete getXMLCliente() {
		return (JugueteDao.INSTANCE.crearJuguete("Spiderman", "Muñeco de acción", 5, 30.55));
	}

	@GET
	@Produces({ MediaType.TEXT_XML })
	public Juguete getHTMLNavegador() {
		return (JugueteDao.INSTANCE.crearJuguete("Spiderman", "Muñeco de acción", 5, 30.55));
	}
}
