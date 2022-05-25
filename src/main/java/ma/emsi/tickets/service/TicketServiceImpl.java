package ma.emsi.tickets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.tickets.models.Ticket;
import ma.emsi.tickets.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository TicketRepo;
	
	@Override
	public List<Ticket> getAll() {
		return TicketRepo.findAll();
	}

	@Override
	public Ticket getById(long id) {
		return TicketRepo.findById(id).get();
	}

	@Override
	public Ticket save(Ticket ticket) {
		return TicketRepo.save(ticket);
	}

	@Override
	public List<Ticket> findUnassignedTickets() {
		return TicketRepo.findUnassignedTickets();
	}

	
}
