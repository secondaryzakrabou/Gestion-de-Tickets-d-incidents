package ma.emsi.tickets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.emsi.tickets.models.Client;
import ma.emsi.tickets.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<Client> getAll() {
		return clientRepo.findAll();
	}

	@Override
	public Client getByUsername(String username) {
		return clientRepo.getByUsername(username);
	}

	@Override
	public Client getById(long id) {
		return clientRepo.findById(id).get();
	}

	@Override
	public Client save(Client client) {
		client.setPassword(passwordEncoder.encode(client.getPassword()));
		return clientRepo.save(client);
	}
	
	
}
