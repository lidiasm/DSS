package recurso;
import javax.ws.rs.Consumes;
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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import modelo.Juguete;
import dao.JugueteDao;
import recurso.JugueteRecurso;

@Path("/juguetes")
public class JuguetesRecurso {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Juguete> getJuguetesNavegador() {
		List<Juguete> juguetes = new ArrayList<Juguete>();
		juguetes.addAll(JugueteDao.INSTANCE.getModel().values());
		return juguetes;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Juguete> getJuguetesCliente() {
		List<Juguete> juguetes = new ArrayList<Juguete>();
		juguetes.addAll(JugueteDao.INSTANCE.getModel().values());
		return juguetes;
	}
	
	@GET
	@Path("cont")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int cont = JugueteDao.INSTANCE.getModel().size();
		return String.valueOf(cont);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newJuguete(@FormParam("id") String id,
		@FormParam("nombre") String nombre,
		@FormParam("descripcion") String descripcion,
		@FormParam("minEdadRecomendada") int minEdadRecomendada,
		@FormParam("precio") double precio,
		@Context HttpServletResponse servletResponse) throws IOException {
		Juguete juguete = new Juguete(id, nombre, descripcion, minEdadRecomendada, precio);
		JugueteDao.INSTANCE.getModel().put(id, juguete);
		servletResponse.sendRedirect("../crear_juguete.html");
	}

	@Path("{juguete}")
	public JugueteRecurso getJuguete(@PathParam("juguete") String id) {
		return new JugueteRecurso(uriInfo, request, id);
	}
}
