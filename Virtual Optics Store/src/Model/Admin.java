package Model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Admin extends User{
	
	@OneToMany (mappedBy = "Admin" , cascade = CascadeType.ALL)
	private ArrayList<News> news;
	@OneToMany (mappedBy = "Admin" , cascade = CascadeType.ALL)
	private ArrayList<Admin_Quantity> glasses;
	private String userName;
	@ManyToOne
	private Store store;
	@OneToMany(mappedBy = "Admin", cascade = CascadeType.ALL)
	private ArrayList<Transaction> transactions;
	
	//Constructors
	public Admin() {
		super();
	}
	
	public Admin(String fName, String lName, String password,
			String phone, String address, String gender,
			String userName, Store store) {
		super(fName, lName, password, phone, address, gender);
		this.news = new ArrayList<News>();
		this.glasses = new ArrayList<Admin_Quantity>();
		this.userName = userName;
		this.store = store;
		this.transactions = new ArrayList<Transaction>();
	}
	
	//Getters and Setter
	public ArrayList<News> getNews() {
		return news;
	}
	public void setNews(ArrayList<News> news) {
		this.news = news;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public ArrayList<Admin_Quantity> getGlasses() {
		return glasses;
	}

	public void setGlasses(ArrayList<Admin_Quantity> glasses) {
		this.glasses = glasses;
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	//rest of functions
	public void addNews(News news){
		this.news.add(news);
		news.setAdmin(this);
	}
	public void addGlasses(Admin_Quantity gs){
		this.glasses.add(gs);
		gs.setAdmin(this);
	}
	public void addTransaction(Transaction transaction){
		this.transactions.add(transaction);
	}
	
}
