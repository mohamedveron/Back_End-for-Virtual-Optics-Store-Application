package DBLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Model.Attempt;
import Model.Brand;
import Model.Customer;
import Model.Glasses;

public class AttemptDAO {
 public static List<Glasses> getAttempts(String email){
	 Customer customer = UserDAO.getCustomerByEmail(email);
	 EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
	 EntityManager manager = factory.createEntityManager();
	 manager.getTransaction().begin();
	 Query query = manager.createQuery("select a.glasses from Attempt a where a.customer = :param1");
	 query.setParameter("param1", customer);
	 List<Glasses>glasses = query.getResultList();
	 manager.getTransaction().commit();
	 manager.close();
	 factory.close();
	 if(glasses.size()>0)
	 return glasses;
	 return null;
 }
 
 public static String getMaxBrand(String email){
	 Integer max = 0;
	 String maxBrand="";
	 List<Glasses>atms = AttemptDAO.getAttempts(email);
		Map<String,Integer>brandCount = new HashMap<String,Integer>();
		for(int i=0;i<atms.size();i++){
			if(brandCount.containsKey(atms.get(i).getBrand().getName())){
				Integer count = brandCount.get(atms.get(i).getBrand().getName());
				if(count == null)
					count=0;
				count++;
				brandCount.put(atms.get(i).getBrand().getName(), count);
			}
			else
				brandCount.put(atms.get(i).getBrand().getName(),1);
			if(max<brandCount.get(atms.get(i).getBrand().getName())){
				max = brandCount.get(atms.get(i).getBrand().getName());
				maxBrand = atms.get(i).getBrand().getName();
			}
			System.out.println(brandCount.get(atms.get(i).getBrand().getName()));
		}
		return maxBrand;	
 }
 
 public static List<Glasses> getRecommendations(String email){
	 Brand brand = BrandDAO.getBrandByName(getMaxBrand(email));
	 EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
	 EntityManager manager = factory.createEntityManager();
	 manager.getTransaction().begin();
	 Query query = manager.createQuery("select g from Glasses g where g.brand = :param1");
	 query.setParameter("param1", brand);
	 List<Glasses>glasses = query.getResultList();
	 manager.getTransaction().commit();
	 manager.close();
	 factory.close();
	 return glasses;
 }
 public static boolean addAttempt(Attempt atm){
	 EntityManagerFactory factory = Persistence.createEntityManagerFactory(Globals.persistenceUnitName);
	 EntityManager manager = factory.createEntityManager();
	 manager.getTransaction().begin();
	 manager.persist(atm);
	 manager.getTransaction().commit();
	 manager.close();
	 factory.close();
	 return true;
 }
}
