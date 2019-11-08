package modelo;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Juguete {
	private String id;
	private String nombre;
	private String descripcion;
	private int minEdadRecomendada;
	private double precio;
	
	public Juguete() {}
	
	public Juguete(String id, String nombre, String descripcion, int minEdadRecomendada, double precio) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.minEdadRecomendada = minEdadRecomendada;
		this.precio = precio;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getMinEdadRecomendada() {
		return minEdadRecomendada;
	}
	public void setMinEdadRecomendada(int minEdadRecomendada) {
		this.minEdadRecomendada = minEdadRecomendada;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
