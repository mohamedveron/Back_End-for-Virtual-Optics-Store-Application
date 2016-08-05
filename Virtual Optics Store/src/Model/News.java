package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	private String content;
	@ManyToOne
	private Admin admin;
	
	//Constructors
	public News() {
		super();
	}

	public News(int ID, String content, Admin admin) {
		super();
		this.ID = ID;
		this.content = content;
		this.admin = admin;
	}

	//Setters and Getters
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	
	
	
}
