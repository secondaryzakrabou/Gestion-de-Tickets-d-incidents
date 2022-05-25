package ma.emsi.tickets.service;

import java.util.List;

import ma.emsi.tickets.models.Admin;

public interface AdminService {

	List<Admin> getAll();
	
	Admin getByUsername(String username);
	
	Admin getById(long id);
	
	Admin save(Admin admin);
}
