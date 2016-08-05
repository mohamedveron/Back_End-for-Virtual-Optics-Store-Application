package DBLayer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Model.Admin;
import Model.Customer;

public class UserDAO {

	public static boolean createCustomer(Customer customer) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		if(getCustomerByEmail(customer.getEmail()) == null){
			manager.persist(customer);
			
			manager.getTransaction().commit();
			manager.close();
			factory.close();
			return true;
		}
		else{
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return false;}
	}
	
	public static void updateCustomer(Customer customer){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		manager.merge(customer);
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
	public static Customer getCustomerByEmailAndPassword(String email,
			String password) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query query = manager
				.createQuery("select c from Customer c where c.email = :param1"
						+ " and c.password = :param2");
		query.setParameter("param1", email);
		query.setParameter("param2", password);
		List<Customer> result = query.getResultList();
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	public static Customer getCustomerByEmail(String email) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query query = manager
				.createQuery("select c from Customer c where c.email = :param1");
		query.setParameter("param1", email);
		List<Customer> result = query.getResultList();
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return (result.size() == 0) ? null : result.get(0);
	}
	
	public static boolean createAdmin(Admin admin){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		if(getAdminByUserName(admin.getUserName()) == null){
			manager.persist(admin);
			manager.getTransaction().commit();
			manager.clear();
			factory.close();
			return true;
		}
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return false;
	}
	
	public static void updateAdmin(Admin admin){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		manager.merge(admin);
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
	
	public static Admin getAdminByUserNameAndPassword(String userName, String password){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("select a from Admin a where a.userName = :param1"
				+ " and a.password = :param2");
		query.setParameter("param1", userName);
		query.setParameter("param2", password);
		List<Admin> result = query.getResultList();
		
		manager.getTransaction().commit();
		return (result.size() == 0) ? null : result.get(0);
	}
	
	public static Admin getAdminByUserName(String userName){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("select a from Admin a where a.userName = :param1");
		query.setParameter("param1", userName);
		List<Admin> result = query.getResultList();
		
		manager.getTransaction().commit();
		return (result.size() == 0) ? null : result.get(0);
	}
}
