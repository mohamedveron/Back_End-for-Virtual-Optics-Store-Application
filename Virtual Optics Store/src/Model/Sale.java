package Model;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Sale {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	private int percentage;
	private Date startDate;
	private Date endDate;
	
	@ManyToMany
	  @JoinTable(
	      name="Has_offer",
	      joinColumns=@JoinColumn(name="EMP_ID", referencedColumnName="ID"),
	      inverseJoinColumns=@JoinColumn(name="PROJ_ID", referencedColumnName="ID"))
	private ArrayList<Glasses>glasses;
	
	//Constructors
	public Sale(){
		super();
	}
	
	public Sale(int saleID, int percentage, Date startDate, Date endDate) {
		super();
		this.ID = saleID;
		this.percentage = percentage;
		this.startDate = startDate;
		this.endDate = endDate;
		glasses = new ArrayList<Glasses>();
	}
	
	// Setters and Getters
	public int getSaleID() {
		return ID;
	}
	public void setSaleID(int saleID) {
		this.ID = saleID;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	// rest of functions
	public void addGlasses(Glasses g){
		this.glasses.add(g);
	}
	
}
