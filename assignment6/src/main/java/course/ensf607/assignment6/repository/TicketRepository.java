package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public Optional<Ticket> findTicketById(Long id);

}
