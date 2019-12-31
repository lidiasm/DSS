package modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Juguete {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;
	private String nombre;
	private String descripcion;
	private int minEdadRecomendada;
	private double precio;
	private String nombreAlmacen;
	
	public Juguete() {}
	
	public Juguete(String nombreAlmacen, String nombre, String descripcion, int minEdadRecomendada, double precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.minEdadRecomendada = minEdadRecomendada;
		this.precio = precio;
		this.nombreAlmacen = nombreAlmacen;
	}
	
	public Juguete(String id, String nombreAlmacen, String nombre, String descripcion, int minEdadRecomendada, double precio) {
		this.id = id;
		this.nombreAlmacen = nombreAlmacen;
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
	
	public String getNombreAlmacen() {
		return nombreAlmacen;
	}
	
	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
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
	
	public String toString() {
		return "\nJuguete: " + nombre + "\nDescripción: " + descripcion
			+ "\nMínima edad: " + minEdadRecomendada + "\nPrecio: " + precio + "\nNombre del almacén: " + nombreAlmacen;
	}
}