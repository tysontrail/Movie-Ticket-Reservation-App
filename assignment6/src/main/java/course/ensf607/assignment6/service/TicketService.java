package course.ensf607.assignment6.service;

import course.ensf607.assignment6.entity.Ticket;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**Theatre service class that allows for search, view and select.
 */
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public Optional<Ticket> searchTicketById(Long id){
        return this.ticketRepository.findTicketById(id);
    }

    public Optional<Ticket> cancelRegisteredUserTicket(Long id){
        Optional<Ticket> ticket = this.ticketRepository.findTicketById(id);
        if(ticket.isPresent()){
            ticket.get().setMovieName("cancelled");
            ticket.get().setShowtime(null);
            ticket.get().setTheatre(null);
            this.ticketRepository.save(ticket.get());
            return ticket;
        }
        else{
            throw new IllegalStateException("Ticket could not be cancelled.");
        }
    }

    public Optional<Ticket> cancelRegularUserTicket(Long id){
        Optional<Ticket> ticket = this.ticketRepository.findTicketById(id);
        if(ticket.isPresent()){
            ticket.get().setMovieName("cancelled");
            ticket.get().setShowtime(null);
            ticket.get().setTheatre(null);
            double newBalance = ticket.get().getBalance() * 0.85;
            ticket.get().setBalance(newBalance);
            this.ticketRepository.save(ticket.get());
            return ticket;
        }
        else{
            throw new IllegalStateException("Ticket could not be cancelled.");
        }
    }

//    public Ticket createTicket(){
//        return new Ticket();
//    }

public void addTicket(Ticket ticket) {
    Optional<Ticket> checkTicket = searchTicketById(ticket.getId());
    if (checkTicket.isPresent()) {
      throw new IllegalStateException("Ticket ID already present in database.");
    }
    this.ticketRepository.save(ticket);
  }
}