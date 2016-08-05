package DBLayer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Model.Brand;

public class BrandDAO {
	public static boolean addBrand(Brand brand){
		
		if(getBrandByName(brand.getName()) == null){
			EntityManagerFactory factory =  Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
			EntityManager manager = factory.createEntityManager();
			manager.getTransaction().begin();
			
			manager.persist(brand);
			
			manager.getTransaction().commit();
			manager.close();
			factory.close();
			return true;
		}
		return false;
	}
	
	public static Brand getBrandByName(String name){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("select b from Brand b where b.name = :param1");
		query.setParameter("param1", name);
		List<Brand> result = query.getResultList();
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		
		return (result.size() == 0) ? null : result.get(0); 
	}
	
	public static List<Brand> getBrands(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("select b from Brand b");
		List<Brand> result = query.getResultList();
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return result;
	}
	public static Brand getBrandsByName(String name){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("select b from brand b where b.name = :param1");
		query.setParameter("param1", name);
		List<Brand> result = query.getResultList();
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return result.get(0);
	}
}
