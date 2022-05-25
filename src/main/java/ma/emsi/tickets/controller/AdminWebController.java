package ma.emsi.tickets.controller;

import java.util.List;
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
import ma.emsi.tickets.models.Admin;
import ma.emsi.tickets.models.Dev;
import ma.emsi.tickets.models.Ticket;
import ma.emsi.tickets.service.AdminService;
import ma.emsi.tickets.service.DevService;
import ma.emsi.tickets.service.TicketService;


@Controller()
@RequestMapping("/admin")
public class AdminWebController {

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
    public List<Ticket> getUnassignedTickets(Model model) {
		model.addAttribute("tickets", ticketService.findUnassignedTickets());
		model.addAttribute("devs", devService.getAll());
        return ticketService.findUnassignedTickets();
    }
	
	@PostMapping("/assign")
    public RedirectView assign(@RequestParam("ticket_id") long ticket_id, @RequestParam("dev_id") long dev_id) {
		Dev dev = devService.getById(dev_id);
		Ticket ticket = ticketService.getById(ticket_id);
		dev.getTickets().add(ticket);
		devService.save(dev);
        return new RedirectView("/admin");
    }
}
