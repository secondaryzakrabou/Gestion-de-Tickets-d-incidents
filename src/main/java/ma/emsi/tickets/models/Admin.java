package ma.emsi.tickets.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends AppUser {

	final static private String role = "ADMIN";

	public static String getRole() {
		return role;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(long id, String username, String address, String phone, String password) {
		super(id, username, address, phone, password);
		// TODO Auto-generated constructor stub
	}
	
	
}