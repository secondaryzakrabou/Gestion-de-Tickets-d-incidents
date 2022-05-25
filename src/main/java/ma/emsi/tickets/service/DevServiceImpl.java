package ma.emsi.tickets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.emsi.tickets.models.Dev;
import ma.emsi.tickets.repository.DevRepository;

@Service
public class DevServiceImpl implements DevService {

	@Autowired
	DevRepository devRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<Dev> getAll() {
		return devRepo.findAll();
	}

	@Override
	public Dev getByUsername(String username) {
		return devRepo.getByUsername(username);
	}

	@Override
	public Dev getById(long id) {
		return devRepo.findById(id).get();
	}

	@Override
	public Dev save(Dev dev) {
		dev.setPassword(passwordEncoder.encode(dev.getPassword()));
		return devRepo.save(dev);
	}
	
}
