package ma.emsi.tickets.models;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("DEV")
public class Dev extends AppUser {

	final static private String role = "DEV";
	@OneToMany
	@JoinColumn(name="dev_id")
	private List<Ticket> tickets;
	
	public static String getRole() {
		return role;
	}

	public Dev() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Dev(long id, String username, String address, String phone, String password, List<Ticket> tickets) {
		super(id, username, address, phone, password);
		this.tickets = tickets;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
	
	
}