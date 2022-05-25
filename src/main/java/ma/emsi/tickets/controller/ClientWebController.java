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
import ma.emsi.tickets.models.Client;
import ma.emsi.tickets.models.Ticket;
import ma.emsi.tickets.service.ClientService;
import ma.emsi.tickets.service.TicketService;

@Controller()
@RequestMapping("/client")
public class ClientWebController {

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
    public String getClientTickets(Model model) {
		model.addAttribute("tickets", getCurrentClient().getTickets());
        return "ticketsList.html";
    }
	@GetMapping("/new_ticket")
    public String newTicket() {
        return "newTicket.html";
    }

	@PostMapping("/new_ticket")
    public RedirectView setStatus(@RequestParam("Description") String description, @RequestParam("env") String env,
    		@RequestParam("Logiciel") String logiciel, @RequestParam("Urgence") String urgence) {
		Ticket t = new Ticket();
		t.setDescription(description);
		t.setEnv(env);
		t.setEtat("Ouverture");
		t.setLogiciel(logiciel);
		t.setUrgence(urgence);
		ticketService.save(t);
		Client client = getCurrentClient();
		client.getTickets().add(t);
		clientService.save(client);

        return new RedirectView("/client/new_ticket");
    }
}
