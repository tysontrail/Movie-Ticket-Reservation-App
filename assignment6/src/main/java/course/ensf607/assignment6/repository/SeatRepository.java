package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}