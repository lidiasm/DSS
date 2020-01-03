package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {
	private String username;
	private String password;
	private String name;
	private double lat;
	private double lon;
	private String preferencias;
	private String pedidos;
	private String carrito;
	private int rol;

	
	public static String encriptarPassword(String input) 
    { 
        try { 
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            }
            return hashtext; 
        }
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 

	public Usuario() {}
	public Usuario(String username, String password) {
		this.rol = 1;
		this.username = username;		
		this.password = Usuario.encriptarPassword(password);
//		37.851950,-6.413554             37.851950,-2.202395
//		36.750358,-6.413554             36.750358,-2.202395
		this.lat = 36.750358 + (Math.random() * (37.851950-36.750358));
		this.lon = -6.413554 + (Math.random() * ((-2.202395)-(-6.413554)));
		this.preferencias = "default";
	}
	
	public Usuario(String username, String password, String name,
			double lat, double lon, String preferencias, String pedidos, String carrito, int rol) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.preferencias = preferencias;
		this.pedidos = pedidos;
		this.carrito = carrito;
		this.rol = rol;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = Usuario.encriptarPassword(password);;
	}
	public boolean checkPassword(String password) {
		if (this.password.equals(Usuario.encriptarPassword(password)))
			return true;
		return false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setUbicacion(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public String getPreferencias() {
		return preferencias;
	}
	public void setPreferencias(String preferencias) {
		this.preferencias = preferencias;
	}
	
	public String getPedidos() {
		return pedidos;
	}
	public void setPedidos(String pedidos) {
		this.pedidos = pedidos;
	}
	
	public String getCarrito() {
		return carrito;
	}
	public void setCarrito(String carrito) {
		this.carrito = carrito;
	}
	
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}
	
	public String toString() {
		return "Usuario: " + name + "\n"
				+ "\tUsername: " + username + "\n"
				+ "\tUbicación: (" + lat + "," + lon + ")\n"
				+ "\tPreferencias: " + preferencias + "\n"
				+ "\tPedidos: " + pedidos + "\n"
				+ "\tCarrito: " + carrito + "\n"
				+ "\tRol: " + rol;
	}
}
