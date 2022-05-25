package ma.emsi.tickets.models;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type")
public class AppUser {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;
	private String username;
	private String password;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AppUser() {
		super();
	}
	public AppUser(long id, String username, String address, String phone, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	
}
