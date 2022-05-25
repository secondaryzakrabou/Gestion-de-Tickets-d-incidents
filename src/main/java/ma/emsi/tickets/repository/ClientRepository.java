package ma.emsi.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.tickets.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	Client getByUsername(String username);
}
