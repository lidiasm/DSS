package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Juguete;

public enum JugueteDao {
	INSTANCE;
	private static final String PERSISTENCE_UNIT_NAME = "juguetes";
	private EntityManagerFactory factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	private JugueteDao() {
		crearJuguete("Barbie", "Muñeca", 3, 53.99);
		crearJuguete("Batman", "Muñeco de acción", 5, 68.99);
	}

	public Juguete crearJuguete(String nombre, String descripcion, int minEdadRecomendada, double precio) {
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();   // Nueva transacción
		Juguete nuevoJuguete = new Juguete(nombre, descripcion, minEdadRecomendada, precio);
		em.persist(nuevoJuguete);
		em.getTransaction().commit();	// Salvar la entidad en la bd
		em.close();   // Cerrar el gestor de entidades
		return nuevoJuguete;
	}
	
	public List<Juguete> getJuguetes() {
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();   // Nueva transacción
		Query q = em.createQuery("select j from Juguete j"); // Consulta
		List<Juguete>juguetes = new ArrayList<Juguete>();
		juguetes.addAll(q.getResultList());
		return juguetes;
	}
	
	public boolean eliminarJuguete(String nombre, String descripcion, int minEdadRecomendada, double precio) {
		boolean jugueteEliminado = false;
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();   // Nueva transacción
		Query q = em.createQuery("select j from Juguete j where j.nombre = :nombre AND "
				+ "j.descripcion = :descripcion AND j.minEdadRecomendada = :minEdadRecomendada AND "
				+ "j.precio = :precio");
		// Ahora asigno los parámetros
		q.setParameter("nombre", nombre);
		q.setParameter("descripcion", descripcion);
		q.setParameter("minEdadRecomendada", minEdadRecomendada);
		q.setParameter("precio", precio);

		try {
		    Juguete juguete = (Juguete) q.getSingleResult(); // Obtener el juguete a borrar
		    em.remove(juguete);	// Eliminar el juguete
		    em.getTransaction().commit(); // Confirmar la eliminación de la entidad
		    List<Juguete> juguetesActuales = getJuguetes();
		    try {
		    	Juguete jugueteBorrado = (Juguete) q.getSingleResult();
		    }catch(NoResultException e) {	// Excepción que sucede cuando el objeto se ha encontrado y borrado
		    	jugueteEliminado = true;
		    }
		}catch(NoResultException e) {	// Excepción si no existe el objeto a borrar
	    }
	    em.close();	// Cerrar gestor de entidades
	    return jugueteEliminado;
	}
}
