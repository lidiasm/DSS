package dao;

import java.util.HashMap;
import java.util.Map;

import modelo.Juguete;

public enum JugueteDao {
	INSTANCE;
	private Map<String, Juguete> proveedorContenidos = new HashMap<String, Juguete>();
	
	private JugueteDao() {
		// Creamos dos juguetes inicialmente
		Juguete j1 = new Juguete("1", "Barbie", "Muñeca con armario", 3, 56.99);
		Juguete j2 = new Juguete("2", "Bebé llorón", "Muñeco que llora", 5, 89.99);
		proveedorContenidos.put("1", j1);
		proveedorContenidos.put("2", j2);
	}
	
	public Map<String, Juguete> getModel(){
		return proveedorContenidos;
	}
}
