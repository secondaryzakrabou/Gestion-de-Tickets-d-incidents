package ma.emsi.tickets.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ma.emsi.tickets.models.Dev;
import ma.emsi.tickets.models.Ticket;
import ma.emsi.tickets.service.DevService;
import ma.emsi.tickets.service.TicketService;


@RestController()
@RequestMapping("/api/dev")
public class DevController{

	@Autowired
	DevService devService;
	@Autowired
	TicketService ticketService;
	
	public Dev getCurrentDev() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		
		return devService.getByUsername(username);
	}
	
	@GetMapping("")
    public List<Ticket> getDevTickets() {
        return getCurrentDev().getTickets();
    }
	
	@PostMapping("/tickets/{ticket_id}")
    public List<Ticket> setStatus(@PathVariable("ticket_id") long ticket_id, @RequestParam("statut") String statut) {
		Ticket ticket = ticketService.getById(ticket_id);
		ticket.setEtat(statut);
		ticketService.save(ticket);
        return getCurrentDev().getTickets();
    }
	
}
