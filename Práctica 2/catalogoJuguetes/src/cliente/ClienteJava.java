package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class ClienteJava {
	
	private static List<Integer>idsJuguetes;

	public static void main(String[] args) throws IOException, SAXException {
		System.out.println("Bievenido al cat�logo de Juguetes.");
		System.out.println("�Con qu� rol desea iniciar la sesi�n?");
		System.out.println("1) Usuario\n2) Administrador");
		System.out.print("Introduzca el n�mero del rol deseado: ");
		Scanner es = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int rol = 0;
		// Cliente
		ClientConfig config = new DefaultClientConfig();
		Client cliente = Client.create(config);
		WebResource servicio = cliente.resource(getBaseURI());
		
		while (rol != 1 && rol != 2) {
			rol = es.nextInt();
			switch(rol) {
			case 1:
				System.out.println("Ha iniciado sesi�n como usuario");
				System.out.println("\nCat�logo de juguetes");
				String catalogo = servicio.path("rest").path("juguetes").
					accept(MediaType.TEXT_XML).get(String.class);
				mostrarCatalogoJuguetes(catalogo, false);
				break;
			case 2:
				System.out.println("\nHa iniciado sesi�n como administrador");
				System.out.println("�Qu� desea hacer?");
				System.out.println("1) A�adir un juguete\n2) Eliminar un juguete\n3) Consultar el cat�logo de juguetes");
				System.out.print("Escoja una de las opciones anteriores: ");
				int opcionAdministrador = 0;
				while (opcionAdministrador < 1 || opcionAdministrador > 3) {
					opcionAdministrador = es.nextInt();
					switch(opcionAdministrador) {
					case 1:
						System.out.println("A�adir un nuevo juguete.");
						System.out.print("Introduzca el nombre del juguete: ");
						String nombreJuguete = "";
						nombreJuguete = br.readLine();
						while (nombreJuguete == "") {
							System.out.print("Introduzca el nombre del juguete: ");
							nombreJuguete = br.readLine();
						}
						
						System.out.print("Introduzca la descripci�n del juguete: ");
						String descripcion = "";
						descripcion = br.readLine();
						while (descripcion == "") {
							System.out.print("Introduzca la descripcion del juguete: ");
							descripcion = br.readLine();
						}
						
						System.out.print("Introduzca una edad m�nima para el juguete: ");
						int minEdadRecomendada = -1;
						if (es.hasNextInt()) minEdadRecomendada = es.nextInt();
						else es.next();
						while (minEdadRecomendada < 0) {
							System.out.print("Introduzca una edad m�nima para el juguete: ");
							if (es.hasNextInt()) minEdadRecomendada = es.nextInt();
							else es.next();
						}
						
						System.out.print("Introduzca el precio del juguete (los decimales se separan con una coma): ");
						double precio = 0.0;
						if (es.hasNextDouble()) precio = es.nextDouble();
						else es.next();
						while (precio <= 0.0) {
							System.out.print("Introduzca el precio del juguete (los decimales se separan con una coma): ");
							if (es.hasNextDouble()) precio = es.nextDouble();
							else es.next();
						}
						
						Form form = new Form();
						form.add("nombre", nombreJuguete);
						form.add("descripcion", descripcion);
						form.add("minEdadRecomendada", minEdadRecomendada);
						form.add("precio", precio);
						ClientResponse respuestaCliente = servicio.path("rest").path("juguetes").path("crearJugueteForm").
							type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
						System.out.println("Juguete a�adido correctamente.");
						System.out.println("Cat�logo actualizado.");
						String catalogoActualizado = servicio.path("rest").path("juguetes").
								accept(MediaType.TEXT_XML).get(String.class);
						mostrarCatalogoJuguetes(catalogoActualizado, true);
						break;
					case 2:
						catalogo = servicio.path("rest").path("juguetes").
							accept(MediaType.TEXT_XML).get(String.class);
						obtenerIdsJuguetes(catalogo);
						System.out.println("Identificadores de los juguetes actuales: " + idsJuguetes.toString());
						System.out.print("Introduzca el id del juguete a borrar: ");
						int idJugueteABorrar = es.nextInt();
						while (!idsJuguetes.contains(idJugueteABorrar)) {
							System.out.print("Por favor, introduzca un id v�lido para borrar el juguete: ");
							idJugueteABorrar = es.nextInt();
						}
						servicio.path("rest").path("juguetes/"+Integer.toString(idJugueteABorrar)).delete();
						System.out.println("Borrado el juguete con identificador = " + idJugueteABorrar);
						System.out.println("Cat�logo de juguetes actual");
						String catalogoTrasBorrado = servicio.path("rest").path("juguetes").
								accept(MediaType.TEXT_XML).get(String.class);
						mostrarCatalogoJuguetes(catalogoTrasBorrado, true);
						break;
					case 3:
						String catalogoAdministrador = servicio.path("rest").path("juguetes").
						accept(MediaType.TEXT_XML).get(String.class);
						mostrarCatalogoJuguetes(catalogoAdministrador, true);
						break;
					}
				}
				break;
			default:
				System.out.print("Opci�n incorrecta. Por favor, introduzca una de las opciones anteriores: ");
				break;
			}
		}
	}
	
	private static void obtenerIdsJuguetes(String catalogo) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		parser.parse(new InputSource(new java.io.StringReader(catalogo)));
	    Document doc = parser.getDocument();
	    doc.getDocumentElement().normalize();
	    NodeList listaXML = doc.getElementsByTagName("juguete");
	    idsJuguetes = new ArrayList<Integer>();

	    for(int elem = 0; elem < listaXML.getLength(); elem++) {
    	  Node nodo = listaXML.item(elem);
    	  if(nodo.getNodeType() == Node.ELEMENT_NODE) {
    	    Element e = (Element) nodo;
    	    idsJuguetes.add(Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent()));
    	  }
	    }
	}
	
	private static void mostrarCatalogoJuguetes(String catalogo, boolean admin) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		parser.parse(new InputSource(new java.io.StringReader(catalogo)));
	    Document doc = parser.getDocument();
	    doc.getDocumentElement().normalize();
	    NodeList listaXML = doc.getElementsByTagName("juguete");

	    for(int elem = 0; elem < listaXML.getLength(); elem++) {
    	  Node nodo = listaXML.item(elem);
    	  if(nodo.getNodeType() == Node.ELEMENT_NODE) {
    	    Element e = (Element) nodo;
    	    if (admin) {
    	    	System.out.print("\nID del juguete: "+e.getElementsByTagName("id").
	    	    	item(0).getTextContent());
    	    }
    	    System.out.println("\nNombre del juguete: "+e.getElementsByTagName("nombre").
    	    	item(0).getTextContent());
    	    System.out.println("Descripci�n del juguete: "+e.getElementsByTagName("descripcion").
	    	    	item(0).getTextContent());
    	    System.out.println("Edad m�nima: "+e.getElementsByTagName("minEdadRecomendada").
	    	    	item(0).getTextContent());
    	    System.out.println("Precio del juguete: "+e.getElementsByTagName("precio").
	    	    	item(0).getTextContent());
    	    System.out.println("Nombre del almac�n: "+e.getElementsByTagName("nombreAlmacen").
        	    	item(0).getTextContent());
    	  }
	    }
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/catalogoJuguetes/").build();
	}
}
