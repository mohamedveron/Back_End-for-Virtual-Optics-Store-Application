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
public class Glasses {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	private String color;
	private String modelName;
	private String model;
	private int convertable;
	private String shape;
	private int type;
	private String material;
	private double price;
	@ManyToOne
	private Brand brand;
	@OneToMany(mappedBy = "Glasses", cascade = CascadeType.ALL)
	ArrayList<Attempt> attempts;
	@OneToMany(mappedBy = "Glasses", cascade = CascadeType.ALL)
	ArrayList<Transaction> transactions;
	@OneToMany(mappedBy = "Glasses", cascade = CascadeType.ALL)
	ArrayList<Glasses_Store> stores;
	
	// Constructors
	public Glasses(){
		super();
	}
	public Glasses(String color, String modelName, String model,
			int convertable, String shape, int type, String material, double price,Brand brand) {
		super();
		this.color = color;
		this.modelName = modelName;
		this.model = model;
		this.convertable = convertable;
		this.shape = shape;
		this.type = type;
		this.material = material;
		this.price = price;
		this.brand = brand;
	}
	// Setters and Getters
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public ArrayList<Attempt> getAttempts() {
		return attempts;
	}
	public void setAttempts(ArrayList<Attempt> attempts) {
		this.attempts = attempts;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getConvertable() {
		return convertable;
	}
	public void setConvertable(int convertable) {
		this.convertable = convertable;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public ArrayList<Glasses_Store> getStore(){
		return this.stores;
	}
	public void setStores(ArrayList<Glasses_Store> stores){
		this.stores = stores;
	}
	
	//Rest of methods
	public void addAttempt(Attempt attempt){
		this.attempts.add(attempt);
		attempt.setGlasses(this);
	}
	public void addTransaction(Transaction transaction){
		this.transactions.add(transaction);
	}
	public void addStore(Glasses_Store store){
		this.stores.add(store);
	}
}
