package modelo;

public class Juguete {
	private int id;
	private String nombre;
	private String descripcion;
	private int minEdadRecomendada;
	private double precio;
	private int almacen;
	private int unidades;
	
	public Juguete() {}
	
	public Juguete(String nombre, String descripcion, int minEdadRecomendada, double precio, int almacen, int unidades) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.minEdadRecomendada = minEdadRecomendada;
		this.precio = precio;
		this.almacen = almacen;
		this.unidades = unidades;
	}
	
	public Juguete(int id, String nombre, String descripcion, int minEdadRecomendada, double precio, int almacen, int unidades) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.minEdadRecomendada = minEdadRecomendada;
		this.precio = precio;
		this.almacen = almacen;
		this.unidades = unidades;
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
	
	public int getAlmacen() {
		return almacen;
	}
	public void setAlmacen(int almacen) {
		this.almacen = almacen;
	}
	
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	public String toString() {
		return "\nJuguete: " + nombre + "\n"
				+ "\tDescripciónn: " + descripcion + "\n"
				+ "\tMin. edad: " + minEdadRecomendada + "\n"
				+ "\tPrecio: " + precio + "\n"
				+ "\tId almacén: " + almacen + "\n"
				+ "\tUnidades: " + unidades;
	}
}