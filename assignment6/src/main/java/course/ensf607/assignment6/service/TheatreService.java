package course.ensf607.assignment6.service;

import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.repository.TheatreRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {

  private final TheatreRepository theatreRepository;

  public TheatreService(TheatreRepository theatreRepository) {
    this.theatreRepository = theatreRepository;
  }

  public Optional<Theatre> searchTheatreByName(String name) {
    return this.searchTheatreByName(name);
  }

  public Optional<Theatre> searchTheatreByID(long id) {
    return this.theatreRepository.findTheatreById(id);
  }

  public void selectTheatre(Theatre selectedTheatre) {
    // TODO: include logic for storing the theatre in the ticket.
    // Needs to get stored in ticket.
  }

  public void addTheatre(Theatre theatre) {
    Optional<Theatre> checkTheatre = searchTheatreByID(theatre.getId());
    if (checkTheatre.isPresent()) {
      throw new IllegalStateException("Theatre ID already present in database.");
    }
  }

  public void addTheatre(String name, String address, int seatCols, int seatRows) {
    Theatre newTheatre = new Theatre(name, address, seatCols, seatRows);
    Optional<Theatre> matchingTheatreName = theatreRepository.findByName(name);
    if (matchingTheatreName.isPresent()) {
      throw new IllegalStateException("Theatre name already in database.");
    }
    this.theatreRepository.save(newTheatre);
  }

  public void deleteTheatre(long id) {
    this.theatreRepository.deleteById(id);
  }

  public Iterable<Theatre> getAllTheatres() {
    return this.theatreRepository.findAll();
  }
}
