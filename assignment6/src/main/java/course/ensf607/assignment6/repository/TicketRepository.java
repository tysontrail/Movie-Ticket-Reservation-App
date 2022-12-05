package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**Interface for the Ticket database, has abstract methods for finding theatre by id and name.
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public Optional<Ticket> findTicketById(Long id);

}
