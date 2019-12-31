package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Almacen;

public enum AlmacenDao {

	INSTANCE;
	private static final String PERSISTENCE_UNIT_NAME = "juguetes";
	private EntityManagerFactory factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	private AlmacenDao() {
		crearAlmacen("Almacén 1", "Granada", 37.197371, -3.624567);
		crearAlmacen("Almacén 2", "Sevilla", 37.380895, -5.990429);
		crearAlmacen("Almacén 3", "Cádiz", 36.526438, -6.287447);
	}

	public Almacen crearAlmacen(String nombre, String direccion, double latitud, double longitud) {
		Almacen nuevoAlmacen = null;
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();   // Nueva transacción
		boolean existeAlmacen = false;
		List<Almacen> almacenesActuales = getAlmacenes();
		for (Almacen almacen: almacenesActuales) {
			if (almacen.getNombre().equals(nombre) && almacen.getDireccion().equals(direccion)) {
				existeAlmacen = true;
				break;
			}
		}
		if (!existeAlmacen) {
			nuevoAlmacen = new Almacen(nombre, direccion, latitud, longitud);
			em.persist(nuevoAlmacen);
			em.getTransaction().commit();	// Salvar la entidad en la bd
		}
		em.close();   // Cerrar el gestor de entidades
		return nuevoAlmacen;
	}
	
	public List<Almacen> getAlmacenes() {
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();   // Nueva transacción
		Query q = em.createQuery("select j from Almacen j"); // Consulta
		List<Almacen>almacenes = new ArrayList<Almacen>();
		almacenes.addAll(q.getResultList());
		return almacenes;
	}
	
	public void eliminarAlmacen(String id) {
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();   // Nueva transacción
		Query q = em.createQuery("select j from Almacen j where j.id = :id");
		q.setParameter("id", id);
		Almacen almacen = (Almacen) q.getSingleResult(); // Obtener el almacén a borrar
	    em.remove(almacen);	// Eliminar el almacén
	    em.getTransaction().commit(); // Confirmar la eliminación de la entidad
	    em.close();	// Cerrar gestor de entidades
	}
}
