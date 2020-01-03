package modelo;

public class Factura {
	private int id;
	private double precio;
	private boolean pagado;
	private String idJuguetesComprados;
	private String fechaEmision;
	
	public Factura() {}
	
	public Factura(double precio, boolean pagado, String idJuguetesComprados, String fechaEmision) {
		this.pagado = pagado;
		this.idJuguetesComprados = idJuguetesComprados;
		this.fechaEmision = fechaEmision;
		this.precio = precio;
	}
	
	public Factura(int id, double precio, boolean pagado, String idJuguetesComprados, String fechaEmision) {
		this.id = id;
		this.pagado = pagado;
		this.idJuguetesComprados = idJuguetesComprados;
		this.fechaEmision = fechaEmision;
		this.precio = precio;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getPagado() {
		return pagado;
	}
	
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	public String getIdJuguetesComprados() {
		return idJuguetesComprados;
	}
	public void setIdJuguetesComprados(String idJuguetesComprados) {
		this.idJuguetesComprados = idJuguetesComprados;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String toString() {
		String resultado = "\nFecha de emisión: " + fechaEmision + "\nPrecio: " + precio;
		if (pagado) resultado += "\nPagada: Sí";
		else resultado += "\nPagada: No";
		resultado += "\nIds de los juguetes comprados: "+idJuguetesComprados;
		return resultado;
	}
}
