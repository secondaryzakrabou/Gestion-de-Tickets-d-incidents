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
import ma.emsi.tickets.models.Admin;
import ma.emsi.tickets.models.Dev;
import ma.emsi.tickets.models.Ticket;
import ma.emsi.tickets.service.AdminService;
import ma.emsi.tickets.service.DevService;
import ma.emsi.tickets.service.TicketService;

@RestController()
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	TicketService ticketService;
	@Autowired
	AdminService adminService;
	@Autowired
	DevService devService;
	
	public Admin getCurrenAdmin() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		
		return adminService.getByUsername(username);
	}
	
	@GetMapping("")
    public List<Ticket> getUnassignedTickets() {
        return ticketService.findUnassignedTickets();
    }
	
	@PostMapping("/ticket/{ticket_id}/dev")
    public List<Ticket> assign(@PathVariable("ticket_id") long ticket_id, @RequestParam("dev_id") long dev_id) {
		Dev dev = devService.getById(dev_id);
		Ticket ticket = ticketService.getById(ticket_id);
		dev.getTickets().add(ticket);
		devService.save(dev);
        return ticketService.findUnassignedTickets();
    }
	
}
