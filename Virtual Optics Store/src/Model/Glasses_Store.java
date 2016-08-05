package Model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Glasses_Store {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	@ManyToOne
	private Glasses glasses;
	@ManyToOne
	private Store store;
	@OneToMany(mappedBy = "glasses_store", cascade = CascadeType.ALL)
	private ArrayList<Admin_Quantity> admins;
	
	// Constructors
	public Glasses_Store() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Glasses_Store(Glasses glasses, Store store, int quantity) {
		super();
		this.admins = new ArrayList<Admin_Quantity>();
		this.setGlasses(glasses);
		this.setStore(store);
	}
	//Setters and Getters
	public Glasses getGlasses() {
		return glasses;
	}
	public void setGlasses(Glasses glasses) {
		this.glasses = glasses;
		glasses.addStore(this);
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
		store.addGlasses(this);
	}
	public ArrayList<Admin_Quantity> getAdminglasses() {
		return admins;
	}
	public void setAdminglasses(ArrayList<Admin_Quantity> admins) {
		this.admins = admins;
	}
	
	// rest of functions
	public void addAdminGlasses(Admin_Quantity admin){
		this.admins.add(admin);
		admin.setGlassesStore(this);
	}
	
}
