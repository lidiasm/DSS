package recurso;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import modelo.Factura;
import dao.DbConnection;
import dao.FacturaDao;

@Path("/facturas")
public class FacturasRecurso {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idFactura}")
	public String getFactura(@PathParam("idFactura") String id) throws Exception {
		int identFactura = Integer.parseInt(id);
		Factura factura = FacturaDao.get(DbConnection.getConnection(), identFactura);
		String jsonFactura = new Gson().toJson(factura);
		return jsonFactura;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getFacturas() throws Exception {
		List<Factura> facturas = FacturaDao.getAll(DbConnection.getConnection());
		String jsonFacturas = new Gson().toJson(facturas);
		return jsonFacturas;
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN) 
	public String getCount() throws Exception { 
		int tamanio = FacturaDao.getAll(DbConnection.getConnection()).size(); 
		return Integer.toString(tamanio);
	}
}
