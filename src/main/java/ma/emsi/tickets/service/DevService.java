package ma.emsi.tickets.service;


import java.util.List;

import ma.emsi.tickets.models.Dev;

public interface DevService {

	List<Dev> getAll();
	
	Dev getByUsername(String username);
	
	Dev getById(long id);
	
	Dev save(Dev dev);
}
