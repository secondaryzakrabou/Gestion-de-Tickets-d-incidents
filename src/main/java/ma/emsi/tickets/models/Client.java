package ma.emsi.tickets.models;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("CLIENT")
public class Client extends AppUser {

	final static private String role = "CLIENT";
	@OneToMany
	@JoinColumn(name="client_id")
	private List<Ticket> tickets;
	
	public static String getRole() {
		return role;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(long id, String username, String address, String phone, String password, List<Ticket> tickets) {
		super(id, username, address, phone, password);
		this.setTickets(tickets);
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
}