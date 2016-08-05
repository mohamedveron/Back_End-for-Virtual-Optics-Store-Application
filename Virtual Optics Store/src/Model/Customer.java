package Model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Customer extends User{
	
	private double leftSight;
	private double rightSight;
	private String email;
	@OneToMany(mappedBy= "Customer", cascade = CascadeType.ALL)
	ArrayList<Attempt>attempts;
	@OneToMany(mappedBy = "Customer", cascade = CascadeType.ALL)
	private ArrayList<Transaction> transactions;
	
	//Constructors
	public Customer(){
		super();
	}
	
	public Customer(String fName, String lName, String password,
			String phone, String address, String gender, double leftSight,
			double rightSight, String email) {
		super(fName, lName, password, phone, address, gender);
		this.leftSight = leftSight;
		this.rightSight = rightSight;
		this.email = email;
		this.attempts = new ArrayList<Attempt>();
		this.transactions = new ArrayList<Transaction>();
	}

	//Setters and Getters
	public double getLeftSight() {
		return leftSight;
	}
	public void setLeftSight(double leftSight) {
		this.leftSight = leftSight;
	}
	public double getRightSight() {
		return rightSight;
	}
	public void setRightSight(double rightSight) {
		this.rightSight = rightSight;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Attempt> getAttempts() {
		return attempts;
	}

	public void setAttempts(ArrayList<Attempt> attempts) {
		this.attempts = attempts;
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	//Rest of functions
	public void addAttempts(Attempt attempt){
		this.attempts.add(attempt);
		attempt.setCustomer(this);
	}
	public void addTransaction(Transaction transaction){
		this.transactions.add(transaction);
	}
}
