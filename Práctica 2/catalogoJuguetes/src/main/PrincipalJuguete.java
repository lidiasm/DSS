package main;

import java.util.List;

import java.net.URI;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

import dao.JugueteDao;
import modelo.Juguete;

public class PrincipalJuguete {
	
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client cliente = Client.create(config);
		WebResource servicio = cliente.resource(getBaseURI());
		
		///////////// HAY QUE UTILIZAR LOS SERVICIOS REST, NO EL DAO
		List<Juguete> juguetes = JugueteDao.INSTANCE.getJuguetes();
		for (Juguete jug: juguetes) {
	      System.out.println(jug.toString());
	    }
		System.out.println("\nCreamos un nuevo juguete...");
		JugueteDao.INSTANCE.crearJuguete("Buddy", "Vaquero", 2, 25.99);
		juguetes = JugueteDao.INSTANCE.getJuguetes();
		for (Juguete jug: juguetes) {
	      System.out.println(jug.toString());
	    }
		System.out.println("\nBorramos el juguete Barbie...");
		boolean borrado = JugueteDao.INSTANCE.eliminarJuguete("Barbie", "Muñeca", 3, 53.99);
		if (borrado) System.out.println("\nJuguete borrado!!");
		else System.out.println("\n¡¡El juguete no se ha borrado!!");
		
		System.out.println("\nLista de juguetes actuales...");
		juguetes = JugueteDao.INSTANCE.getJuguetes();
		for (Juguete jug: juguetes) {
	      System.out.println(jug.toString());
	    }
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/catalogoJuguetes/").build();
	}
}