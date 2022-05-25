package ma.emsi.tickets.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ma.emsi.tickets.models.Client;
import ma.emsi.tickets.models.Ticket;
import ma.emsi.tickets.service.ClientService;
import ma.emsi.tickets.service.TicketService;

@RestController()
@RequestMapping("/api/client")
public class ClientController {

	@Autowired
	TicketService ticketService;
	@Autowired
	ClientService clientService;

	public Client getCurrentClient() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		
		return clientService.getByUsername(username);
	}

	
	@GetMapping("")
    public List<Ticket> getClientTickets() {
        return getCurrentClient().getTickets();
    }
	
	@PostMapping("/tickets")
    public List<Ticket> setStatus(@RequestBody Ticket t) {
		ticketService.save(t);
		Client client = getCurrentClient();
		client.getTickets().add(t);
		clientService.save(client);
		
        return getCurrentClient().getTickets();
    }
	
}
