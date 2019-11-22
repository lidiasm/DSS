package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import dao.JugueteDao;
import modelo.Juguete;

@WebServlet(urlPatterns = { "/borrarJuguete" })
public class BorrarJugueteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BorrarJugueteServlet() {
		super();
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String idJuguete = (String) request.getParameter("id");
		Client client = ClientBuilder.newClient();
		WebTarget OrderByIdTarget = client.target(
			"http://localhost:8080/catalogoJuguetes/rest/juguetes/eliminar/"+idJuguete);
        String respuesta = OrderByIdTarget.request().delete(String.class);
 		request.setAttribute("resultadoOperaciones", respuesta);
 		
 		List<Juguete> juguetes = JugueteDao.INSTANCE.getJuguetes();
		HttpURLConnection conn=null;
        BufferedReader reader=null;
        // Obtener catálogo de juguetes
        URL url = new URL("http://localhost:8080/catalogoJuguetes/rest/juguetes/administrador");  
        conn = (HttpURLConnection)url.openConnection();  
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "text/html");

        // Leer la respuesta
        reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        String output = null;  
        StringBuilder strBuf = new StringBuilder();
        while ((output = reader.readLine()) != null)  
            strBuf.append(output);
    
 		request.setAttribute("catalogo", strBuf);
         
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/catalogo.jsp");
        dispatcher.forward(request, response);
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
