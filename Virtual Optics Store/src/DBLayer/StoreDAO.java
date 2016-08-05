package DBLayer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Model.Store;

public class StoreDAO {
	
		public static boolean addStore(Store store){
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
			EntityManager manager = factory.createEntityManager();
			manager.getTransaction().begin();
			
			if(getStoreByAddress(store.getAddress()) == null){
				manager.persist(store);
				manager.getTransaction().commit();
				manager.close();
				factory.close();
				return true;
			}
			manager.getTransaction().commit();
			manager.close();
			factory.close();
			return false;
		}
		
		public static Store getStoreByAddress(String address){
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
			EntityManager manager = factory.createEntityManager();
			manager.getTransaction().begin();
			
			Query query = manager.createQuery("select s from Store s where s.address = :param1");
			query.setParameter("param1", address);
			List<Store> result = query.getResultList();
			
			manager.getTransaction().commit();
			return (result.size() == 0) ? null : result.get(0);
		}
		
		public static Store getStoreByID(int ID){
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
			EntityManager manager = factory.createEntityManager();
			manager.getTransaction().begin();
			
			Query query = manager.createQuery("select s from Store s where s.ID = :param1");
			query.setParameter("param1", ID);
			List<Store> result = query.getResultList();
			
			manager.getTransaction().commit();
			manager.close();
			factory.close();
			return (result.size() == 0) ? null : result.get(0);
		}
}
