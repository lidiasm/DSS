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
	@Path("usuario")
	@Produces(MediaType.TEXT_HTML)
	public String getCatalogoJuguetesUsuario() {
		List<Juguete> juguetes = JugueteDao.INSTANCE.getJuguetes();
		String catalogo = "<table>"+"<tr>"+"<td>Juguete</td>"+"<td style='padding-left:60px'>Descripción</td>"+
			"<td>Mín. edad recomendada</td>"+"<td style='padding:20px'>Precio</td>"+"<td style='padding:20px'>Almacén</td>"+"</tr>";

		for (Juguete juguete: juguetes) {
			catalogo += "<tr>";
			catalogo += "<td>"+juguete.getNombre()+"</td>";
			catalogo += "<td style='padding-left:50px'>"+juguete.getDescripcion()+"</td>";
			catalogo += "<td style='padding-left:100px'>"+juguete.getMinEdadRecomendada()+"</td>";
			catalogo += "<td style='padding-left:20px'>"+juguete.getPrecio()+"€</td>";
			catalogo += "<td style='padding-left:20px'>"+juguete.getNombreAlmacen()+"</td>";
			catalogo += "</tr>";
		}
		catalogo += "</table>";
		return catalogo;
	}
	
	@GET
	@Path("administrador")
	@Produces(MediaType.TEXT_HTML)
	public String getCatalogoJuguetesAdministrador() {
		List<Juguete> juguetes = JugueteDao.INSTANCE.getJuguetes();
		String catalogo = "<a style='font-size:20px;background-color: antiquewhite;color: black;padding: 14px 10px;"+
				"text-align: center;text-decoration: none; float:right;' href='crear_juguete.html'>Añadir juguete.</a>";
		catalogo += "<table>"+"<tr>"+"<td>ID</td>"+"<td style='padding-left:60px'>Juguete</td>"+"<td style='padding-left:60px'>Descripción</td>"+
			"<td>Mín. edad recomendada</td>"+"<td style='padding:20px'>Precio</td>"+"<td style='padding:20px'>Almacén</td>"+"</tr>";

		for (Juguete juguete: juguetes) {
			catalogo += "<tr>";
			catalogo += "<td>"+juguete.getId()+"</td>";
			catalogo += "<td style='padding-left:50px'>"+juguete.getNombre()+"</td>";
			catalogo += "<td style='padding-left:50px'>"+juguete.getDescripcion()+"</td>";
			catalogo += "<td style='padding-left:100px'>"+juguete.getMinEdadRecomendada()+"</td>";
			catalogo += "<td style='padding-left:20px'>"+juguete.getPrecio()+"€</td>";
			catalogo += "<td style='padding-left:20px'>"+juguete.getNombreAlmacen()+"</td>";
			catalogo += "<td style='padding-left:20px'>"
				+ "<a style='background-color: #d8e9f1;color: black;text-align: center;text-decoration: none;' "
				+ "href='http://localhost:8080/catalogoJuguetes/borrarJuguete?id="+juguete.getId()+"'>Borrar</a></td>";
			catalogo += "</tr>";
		}
		catalogo += "</table>";
		return catalogo;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Juguete> getJuguetesNavegador() {
		return JugueteDao.INSTANCE.getJuguetes();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Juguete> getJuguetesCliente() {
		return (JugueteDao.INSTANCE.getJuguetes());
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int tamanio = (JugueteDao.INSTANCE.getJuguetes()).size();
		return Integer.toString(tamanio);
	}

	@POST
	@Path("crearJugueteRest")
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String newJugueteRest(
		@FormParam("nombre") String nombre,
		@FormParam("descripcion") String descripcion,
		@FormParam("minEdadRecomendada") String minEdadRecomendada,
		@FormParam("precio") String precio,
		@Context HttpServletResponse servletResponse) throws IOException {
		int minimaEdad = Integer.parseInt(minEdadRecomendada);
		double pr = Double.parseDouble(precio);
		List<Juguete> juguetesActuales = JugueteDao.INSTANCE.getJuguetes();
		boolean existeJuguete = false;
		String resultado = "";
		for (Juguete juguete: juguetesActuales) {
			if (juguete.getNombre().equals(nombre) && juguete.getDescripcion().equals(descripcion)
					&& juguete.getMinEdadRecomendada() == minimaEdad && juguete.getPrecio() == pr) {
				existeJuguete = true;
				resultado = "El juguete ya existe.";
				break;
			}
		}
		if (!existeJuguete) {
			resultado = "El juguete se ha insertado correctamente.";
			Juguete nuevoJuguete = JugueteDao.INSTANCE.crearJuguete("Almacén 1", nombre, descripcion, minimaEdad, pr);
			if (nuevoJuguete == null) resultado = "El juguete no se ha podido insertar.";
		}
		
		return resultado;
	}
	
	@POST
	@Path("crearJugueteForm")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newJugueteForm(
		@FormParam("nombre") String nombre,
		@FormParam("descripcion") String descripcion,
		@FormParam("minEdadRecomendada") String minEdadRecomendada,
		@FormParam("precio") String precio,
		@Context HttpServletResponse servletResponse) throws IOException {
		int minimaEdad = Integer.parseInt(minEdadRecomendada);
		double pr = Double.parseDouble(precio);
		List<Juguete> juguetesActuales = JugueteDao.INSTANCE.getJuguetes();
		boolean existeJuguete = false;
		for (Juguete juguete: juguetesActuales) {
			if (juguete.getNombre().equals(nombre) && juguete.getDescripcion().equals(descripcion)
					&& juguete.getMinEdadRecomendada() == minimaEdad && juguete.getPrecio() == pr) {
				existeJuguete = true;
				break;
			}
		}
		if (!existeJuguete) {
			JugueteDao.INSTANCE.crearJuguete("Almacén 1", nombre, descripcion, minimaEdad, pr);
			servletResponse.sendRedirect("http://localhost:8080/catalogoJuguetes/crear_juguete.html");
		}
	}

	@Path("{juguete}")
	public JugueteRecurso getJuguete(@PathParam("juguete") String id) {
		return new JugueteRecurso(uriInfo, request, id);
	}
	
	@DELETE
	@Path("eliminar/{idJuguete}")
	public String eliminarJuguete(
		@PathParam("idJuguete") String id) {
		JugueteRecurso juguete = new JugueteRecurso(uriInfo, request, id);
		juguete.eliminarJuguete();
		return "Juguete borrado correctamente.";
	}
}
