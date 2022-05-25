package ma.emsi.tickets.service;

import java.util.List;

import ma.emsi.tickets.models.Client;

public interface ClientService {

	List<Client> getAll();
	
	Client getByUsername(String username);
	
	Client getById(long id);
	
	Client save(Client client);
}
