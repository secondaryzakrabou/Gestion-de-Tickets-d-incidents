package ma.emsi.tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ma.emsi.tickets.models.Dev;
import ma.emsi.tickets.models.Ticket;
import ma.emsi.tickets.service.DevService;
import ma.emsi.tickets.service.TicketService;

@Controller()
@RequestMapping("/dev")
public class DevWebController {

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
    public String getDevTickets(Model model) {
		model.addAttribute("tickets", getCurrentDev().getTickets());
        return "dev.html";
    }
	
	@PostMapping("/tickets")
    public RedirectView setStatus(@RequestParam("ticket_id") long ticket_id, @RequestParam("statut") String statut) {
		Ticket ticket = ticketService.getById(ticket_id);
		ticket.setEtat(statut);
		ticketService.save(ticket);
        return new RedirectView("/dev");
    }
	
	
}
