package course.ensf607.assignment6.service;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.entity.Ticket;
import course.ensf607.assignment6.repository.TheatreRepository;
import java.util.Optional;

import course.ensf607.assignment6.repository.TicketRepository;
import org.springframework.stereotype.Service;

/**Theatre service class that allows for search, view and select.
 */
@Service
public class TheatreService {

  private final TicketRepository ticketRepository;

  private final TheatreRepository theatreRepository;

  private final MovieService movieService;

  public TheatreService(TheatreRepository theatreRepository, MovieService movieService, TicketRepository ticketRepository) {
    this.theatreRepository = theatreRepository;
    this.movieService = movieService;
    this.ticketRepository = ticketRepository;
  }

  public Optional<Theatre> searchTheatreByName(String name) {
    return this.theatreRepository.findByName(name);
  }

  public Optional<Theatre> searchTheatreByID(long id) {
    return this.theatreRepository.findTheatreById(id);
  }

  //Body-object version
  public void selectTheatre(Theatre selectedTheatre) {
    Ticket theTicket = Ticket.getInstance();
    theTicket.setTheatre(selectedTheatre);
  }

  //String name then find it edition.
  public void selectTheatre(String theatreName) {
    Optional<Theatre> selectedTheatre = theatreRepository.findByName(theatreName);
    if(selectedTheatre.isPresent()){
      Ticket theTicket = Ticket.getInstance();
      theTicket.setTheatre(selectedTheatre.get());
      this.ticketRepository.save(theTicket);

    }
    else{
      throw new IllegalStateException("Could not find theatre.");
    }
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

  public void addMovieToTheatre(Theatre theatre, String movieName) {
    Optional<Movie> addMovie = this.movieService.searchAllMovies(movieName);
    if (addMovie.isPresent()) {
      theatre.addMovieToTheatre(addMovie);
      theatreRepository.save(theatre);
    } else {
      throw new IllegalStateException("Could not find movie.");
    }
  }

  public void deleteTheatre(long id) {
    this.theatreRepository.deleteById(id);
  }

  public Iterable<Theatre> getAllTheatres() {
    return this.theatreRepository.findAll();
  }
}
