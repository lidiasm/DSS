package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Juguete;

public enum JugueteDao {
	INSTANCE;
	private static final String PERSISTENCE_UNIT_NAME = "juguetes";
	private EntityManagerFactory factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	private JugueteDao() {
		crearJuguete("Almac�n 1", "Barbie", "Mu�eca con ropero", 3, 53.99);
		crearJuguete("Almac�n 1", "Batman", "Mu�eco de acci�n", 5, 68.99);
		crearJuguete("Almac�n 1", "Nancy", "Mu�eca", 2, 85.99);
		crearJuguete("Almac�n 1", "Nenuco", "Mu�eco beb�", 2, 35.55);
		crearJuguete("Almac�n 1", "Cocinita", "Horno y cacharros", 4, 123.99);
		crearJuguete("Almac�n 1", "Beb� llor�n", "Mu�eco beb� que llora de verdad", 5, 55.66);
		crearJuguete("Almac�n 1", "Pony", "Mu�eco animal", 3, 69.99);
		crearJuguete("Almac�n 1", "Superman", "Mu�eco del hombre ara�a", 2, 20.99);
		crearJuguete("Almac�n 1", "Coche teledirigido", "Coche de juguete", 5, 69.99);
		crearJuguete("Almac�n 1", "Scalextric", "Pista de coches", 7, 140.59);
	}

	public Juguete crearJuguete(String nombreAlmacen, String nombre, String descripcion, int minEdadRecomendada, double precio) {
		Juguete nuevoJuguete = null;
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();   // Nueva transacci�n
		boolean existeJuguete = false;
		List<Juguete> juguetesActuales = getJuguetes();
		for (Juguete juguete: juguetesActuales) {
			if (juguete.getNombre().equals(nombre) && juguete.getDescripcion().equals(descripcion) &&
					juguete.getMinEdadRecomendada() == minEdadRecomendada && juguete.getPrecio() == precio) {
				existeJuguete = true;
				break;
			}
		}
		if (!existeJuguete) {
			nuevoJuguete = new Juguete(nombreAlmacen, nombre, descripcion, minEdadRecomendada, precio);
			em.persist(nuevoJuguete);
			em.getTransaction().commit();	// Salvar la entidad en la bd
		}
		em.close();   // Cerrar el gestor de entidades
		return nuevoJuguete;
	}
	
	public List<Juguete> getJuguetes() {
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();   // Nueva transacci�n
		Query q = em.createQuery("select j from Juguete j"); // Consulta
		List<Juguete>juguetes = new ArrayList<Juguete>();
		juguetes.addAll(q.getResultList());
		return juguetes;
	}
	
	public void eliminarJuguete(String id) {
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();   // Nueva transacci�n
		System.out.println(id);
		Query q = em.createQuery("select j from Juguete j where j.id = :id");
		q = q.setParameter("id", id);
		Juguete juguete = (Juguete) q.getSingleResult(); // Obtener el juguete a borrar
	    em.remove(juguete);	// Eliminar el juguete
	    em.getTransaction().commit(); // Confirmar la eliminaci�n de la entidad
	    em.close();	// Cerrar gestor de entidades
	}
}
