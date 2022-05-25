package ma.emsi.tickets.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller()
@RequestMapping("")
public class HomeController {

	
	@GetMapping("")
    public RedirectView getUnassignedTickets(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		boolean admin = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		boolean dev = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_DEV"));
		
		if(admin)
			return new RedirectView("/admin");
		else if(dev)
			return new RedirectView("/dev");
		else 
			return new RedirectView("/client");
    }
	
}
