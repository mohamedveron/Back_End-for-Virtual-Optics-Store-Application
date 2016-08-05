package DBLayer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Model.Sale;
import Model.Store;

public class SaleDAO {
	public static void addSale(Sale sale){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(sale);
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
	
	public static void updateSale(Sale sale){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();

		manager.merge(sale);
        
        manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
}
