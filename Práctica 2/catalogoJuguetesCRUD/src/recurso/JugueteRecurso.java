package recurso;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

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
	public Juguete getXMLCliete() {
		Juguete juguete = new Juguete("1", "Spiderman", "Muñeco de acción", 3, 31.99);
		return juguete;
	}

	@GET
	@Produces({ MediaType.TEXT_XML })
		public Juguete getHTMLNavegador() {
		Juguete juguete = new Juguete("2", "Batman", "Muñeco de acción", 3, 50.99);
		return juguete;
	}
}
