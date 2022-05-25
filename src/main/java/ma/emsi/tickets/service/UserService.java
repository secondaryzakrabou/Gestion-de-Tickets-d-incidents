package ma.emsi.tickets.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.emsi.tickets.models.AppUser;


public interface UserService extends UserDetailsService {

	List<AppUser> getAll();
	
	AppUser getByUsername(String username);
	
	AppUser getById(long id);
	
	AppUser save(AppUser admin);
}
