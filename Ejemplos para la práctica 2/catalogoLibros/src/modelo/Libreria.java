package modelo;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "modelo")
public class Libreria {
	// XmLElementWrapper genera un elemento envoltorio alrededor de una representacion XML
	@XmlElementWrapper(name = "listaLibros")
	// XmlElement fija el nombre de las entidades
	@XmlElement(name = "libro")
	private ArrayList<Libro> listaLibros;
	private String nombre;
	private String ubicacion;
	public void setListaLibros(ArrayList<Libro> listaLibros) {
		this.listaLibros = listaLibros;
	}
	public ArrayList<Libro> getListaDeLibros() {
		return listaLibros;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}

