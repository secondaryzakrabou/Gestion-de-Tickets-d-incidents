package ma.emsi.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.tickets.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	Admin getByUsername(String username);
}
