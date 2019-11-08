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
	private int id;
	private String nombre;
	private String descripcion;
	private int minEdadRecomendada;
	private double precio;
	
	public Juguete() {}
	
	public Juguete(String nombre, String descripcion, int minEdadRecomendada, double precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.minEdadRecomendada = minEdadRecomendada;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	public String toString() {
		return "\nJuguete: " + nombre + "\nDescripci�n: " + descripcion
			+ "\nM�nima edad: " + minEdadRecomendada + "\nPrecio: " + precio;
	}
}