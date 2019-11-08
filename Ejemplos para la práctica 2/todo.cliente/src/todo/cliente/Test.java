package todo.cliente;

import java.net.URI;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientConfig config = new DefaultClientConfig();
		Client cliente = Client.create(config);
		WebResource servicio = cliente.resource(getBaseURI());
		// Mostrar el contenido del recurso Todos como texto XML
		System.out.println("Mostrar contenido del recurso como Texto XML Plano");
		System.out.println(servicio.path("rest").path("todo").accept(MediaType.TEXT_XML).get(String.class));
		// Mostrar contenido para aplicaciones XML
		System.out.println("Mostrar contenido del recurso para aplicacion XML");
		System.out.println(servicio.path("rest").path("todo").accept(MediaType.APPLICATION_XML).get(String.class));
	}
	private static URI getBaseURI(){
		return UriBuilder.fromUri("http://localhost:8080/todo/").build();
	}
}