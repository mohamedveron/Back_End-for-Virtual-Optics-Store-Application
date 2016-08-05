package Model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Store {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	int ID;
	private String phone;
	private String address;
	@OneToMany (mappedBy = "Store" , cascade = CascadeType.ALL)
	private ArrayList<Admin> admins;
	@OneToMany (mappedBy = "Store" , cascade = CascadeType.ALL)
	private ArrayList<Glasses_Store> glasses;
	

	//Constructors
	public Store() {
		super();
	}

	public Store(String phone, String address, ArrayList<Admin> admins) {
		super();
		this.phone = phone;
		this.address = address;
		this.admins = admins;
	}

	//Setters and Getters
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(ArrayList<Admin> admins) {
		this.admins = admins;
	}
	
	public ArrayList<Glasses_Store> getGlasses() {
		return glasses;
	}

	public void setGlasses(ArrayList<Glasses_Store> glasses) {
		this.glasses = glasses;
	}
	
	//Rest of functions
	public void addGlasses(Glasses_Store glasses){
		this.glasses.add(glasses);
	}
	
	
	
}
