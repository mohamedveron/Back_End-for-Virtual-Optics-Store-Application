package Model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	@ManyToOne
	private Admin admin;
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private Glasses glasses;
	private Date date;
	
	//Constructors
	public Transaction() {
		super();
	}
	public Transaction(int ID, Admin admin, Customer customer, Glasses glasses,
			Date date) {
		super();
		this.ID = ID;
		this.setAdmin(admin);
		this.setCustomer(customer);
		this.setGlasses(glasses);
		this.date = date;
	}
	
	//Setters and getters
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
		admin.addTransaction(this);
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
		customer.addTransaction(this);
	}
	public Glasses getGlasses() {
		return glasses;
	}
	public void setGlasses(Glasses glasses) {
		this.glasses = glasses;
		glasses.addTransaction(this);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
