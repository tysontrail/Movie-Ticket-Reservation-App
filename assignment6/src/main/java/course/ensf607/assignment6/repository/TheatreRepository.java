package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    Optional<Theatre> findTheatreById(Long id);

    Optional<Theatre> findByName(String name);

}
