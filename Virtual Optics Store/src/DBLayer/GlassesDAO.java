package DBLayer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



import Model.Glasses;

public class GlassesDAO {


	public static boolean addGlasses(Glasses glasses) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();
		
		Glasses oldGlasses = getGlassesByModelName(glasses.getModelName());
		
		if(oldGlasses == null){
			entityManager.persist(glasses);
			entityManager.getTransaction().commit();
			entityManager.close();
			entityManagerFactory.close();
			return true;
		}
		else{
			entityManager.getTransaction().commit();
			entityManager.close();
			entityManagerFactory.close();
			return false;
		}
	}

	public static List<Glasses> getGlasses() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("Select g from Glasses g");
		List<Glasses> glasses = query.getResultList();
		manager.close();
		factory.close();
		return glasses;
	}
	
	public static void updateGlasses(Glasses glasses){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		manager.merge(glasses);
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
	
	public static Glasses getGlassesByID(int ID){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("select g from Glasses g where g.ID = :param1");
		query.setParameter("param1", ID);
		List<Glasses> glasses = query.getResultList(); 
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return (glasses.size() == 0) ? null : glasses.get(0);
		
	}
	
	public static Glasses getGlassesByModelName(String name){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("select g from Glasses g where g.modelName = :param1");
		query.setParameter("param1", name);
		List<Glasses> result = query.getResultList();
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		
		return (result.size() == 0) ? null : result.get(0);
	}
	
}
