package ma.emsi.tickets.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import ma.emsi.tickets.service.AdminService;
import ma.emsi.tickets.service.ClientService;
import ma.emsi.tickets.service.DevService;
import ma.emsi.tickets.service.TicketService;

@Controller
@RestController("/api/test")
public class TestController {

	@Autowired
	TicketService ticketService;
	@Autowired
	ClientService clientService;
	@Autowired
	DevService devService;
	@Autowired
	AdminService adminService;
	
	
}
