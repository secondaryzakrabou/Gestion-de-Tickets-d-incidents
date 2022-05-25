package ma.emsi.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.tickets.models.Dev;

public interface DevRepository extends JpaRepository<Dev, Long> {

	Dev getByUsername(String username);
}
