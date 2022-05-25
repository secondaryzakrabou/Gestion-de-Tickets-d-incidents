package ma.emsi.tickets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.emsi.tickets.models.Admin;
import ma.emsi.tickets.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<Admin> getAll() {
		return adminRepo.findAll();
	}

	@Override
	public Admin getByUsername(String username) {
		return adminRepo.getByUsername(username);
	}

	@Override
	public Admin getById(long id) {
		return adminRepo.findById(id).get();
	}

	@Override
	public Admin save(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminRepo.save(admin);
	}

}
