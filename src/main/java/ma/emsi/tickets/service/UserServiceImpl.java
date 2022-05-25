package ma.emsi.tickets.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.emsi.tickets.models.AppUser;
import ma.emsi.tickets.repository.AdminRepository;
import ma.emsi.tickets.repository.AppUserRepository;
import ma.emsi.tickets.repository.ClientRepository;
import ma.emsi.tickets.repository.DevRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	DevRepository devRepo;
	@Autowired
	AdminRepository adminRepo;
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	AppUserRepository userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = devRepo.getByUsername(username);
		String role = "DEV";
		if (user == null) { 
			user = adminRepo.getByUsername(username);
			role = "ADMIN";
		}
		if (user == null) {
			user = clientRepo.getByUsername(username);
			role = "CLIENT";
		}
		if (user == null)
			 throw new UsernameNotFoundException("Nom d'utilisateur ou mot de passe incorrect");
		
			 
		List<String> roles = new ArrayList<String>();
		roles.add(role);
	
		 
		return new User(user.getUsername(), user.getPassword(), roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" +r)).collect(Collectors.toList())); 
	}

	@Override
	public List<AppUser> getAll() {
		return userRepo.findAll();
	}

	@Override
	public AppUser getByUsername(String username) {
		return userRepo.getByUsername(username);
	}

	@Override
	public AppUser getById(long id) {
		return userRepo.findById(id).get();
	}

	@Override
	public AppUser save(AppUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

}
