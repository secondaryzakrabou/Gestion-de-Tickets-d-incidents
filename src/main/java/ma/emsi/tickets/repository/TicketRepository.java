package ma.emsi.tickets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.emsi.tickets.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	@Query(value = "SELECT * FROM Ticket t WHERE t.dev_id is null", nativeQuery = true)
	List<Ticket> findUnassignedTickets();
}
