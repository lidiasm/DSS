package modelo;

public class Almacen {
	private int id;
	private String nombre;
	private String direccion;
	private double lat;
	private double lon;
	
	public Almacen() {}
	
	public Almacen(String nombre, String direccion, double lat, double lon) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.lat = lat;
		this.lon = lon;
	}
	
	public Almacen(int id, String nombre, String direccion, double lat, double lon) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.lat = lat;
		this.lon = lon;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public String toString() {
		return "\nAlmacén: " + nombre + "\n"
				+ "Dirección: " + direccion + "\n"
				+ "Ubicación: (" + lat + "," + lon+")";
	}
}
