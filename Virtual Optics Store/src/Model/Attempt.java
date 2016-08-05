package Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Attempt {
	
	@Id
	private int compositeKey;
	private Date date;
	@ManyToOne
	private Customer customer;
	@ManyToOne 
	private Glasses glasses;
	
	//Constructors
	public Attempt(){
		super();
	}
	public Attempt(int gID, int cID, Date date, Customer customer,
			Glasses glasses) {
		super();
		this.date = date;
		this.customer = customer;
		this.glasses = glasses;
		this.compositeKey = hash();
	}
	
	// Setters And Getters
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Glasses getGlasses() {
		return glasses;
	}
	public void setGlasses(Glasses glasses) {
		this.glasses = glasses;
	}
	
	//Rest of methods
	public int hash(){
		return customer.getID() + glasses.getID();
	}
}
