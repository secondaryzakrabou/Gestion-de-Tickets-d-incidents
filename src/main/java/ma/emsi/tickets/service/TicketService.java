package ma.emsi.tickets.service;


import java.util.List;

import ma.emsi.tickets.models.Ticket;

public interface TicketService {

	List<Ticket> getAll();
	
	Ticket getById(long id);
	
	Ticket save(Ticket ticket);

	List<Ticket> findUnassignedTickets();
}
