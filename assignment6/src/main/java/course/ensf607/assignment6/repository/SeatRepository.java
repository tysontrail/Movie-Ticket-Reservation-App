package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.Seat;
import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.Ticket;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    
    Optional<Seat> findSeatById(Long id);

    Optional<Seat> findByShowtime(Showtime showtimeid);
    
    Optional<Seat> findByTicket (Ticket ticketid);

    @Query(value="SELECT * FROM seat WHERE showtime_id = :id ORDER BY ticket_id", nativeQuery = true)
    List<Seat> findAllSeatsByShowtimeId(@Param("id") Long id);
}
