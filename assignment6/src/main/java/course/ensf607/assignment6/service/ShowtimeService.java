package course.ensf607.assignment6.service;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.entity.Ticket;
import course.ensf607.assignment6.repository.ShowtimeRepository;
import course.ensf607.assignment6.repository.TicketRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ShowtimeService {

  private final TheatreService theatreService;
  private final MovieService movieService;
  private final ShowtimeRepository showtimeRepository;
  // TODO: May need to delete the ticket repositories after testing is complete and everything is
  // working.
  private final TicketRepository ticketRepository;

  public ShowtimeService(
      TheatreService theatreService,
      ShowtimeRepository showtimeRepository,
      MovieService movieService,
      TicketRepository ticketRepository) {
    this.theatreService = theatreService;
    this.showtimeRepository = showtimeRepository;
    this.movieService = movieService;
    this.ticketRepository = ticketRepository;
  }

  // Showtime object version
  public void selectShowtime(Showtime showtime) {
    Ticket theTicket = Ticket.getInstance();
    // Probably have to find showtime by id because of the relationships.
    Optional<Showtime> theShowtime = showtimeRepository.findById(showtime.getId());
    if (theShowtime.isPresent()) {
      theTicket.setShowtime(showtime.getStartTime());
      this.ticketRepository.save(theTicket);
    } else {
      throw new IllegalStateException("Could not find showtime.");
    }
  }

  // Showtime Id version
  public void selectShowtime(long showtimeId) {
    Ticket theTicket = Ticket.getInstance();
    // Probably have to find showtime by id because of the relationships.
    Optional<Showtime> theShowtime = showtimeRepository.findById(showtimeId);
    if (theShowtime.isPresent()) {
      theTicket.setShowtime(theShowtime.get().getStartTime());
      this.ticketRepository.save(theTicket);
    } else {
      throw new IllegalStateException("Could not find showtime.");
    }
  }

  //    public Iterable<Showtime> getAllTheatreShowtimes(String theatreName){
  //
  //        return null;
  //    }

  public Iterable<Showtime> getAllMovieShowtimes(String movieName) {
    return showtimeRepository.findAllShowtimesByMovie(movieName);
  }

  public void addShowtimeToTheatre(LocalDateTime startTime, Theatre theatre) {
    Showtime newShowtime = new Showtime(startTime, theatre);
    showtimeRepository.save(newShowtime);
  }

  public Optional<Showtime> addShowtimeToTheatre(LocalDateTime startTime, String theatreName) {
    Optional<Theatre> addTheatre = this.theatreService.searchTheatreByName(theatreName);
    if (!addTheatre.isPresent()) {
      throw new IllegalStateException("Could not find theatre.");
    } else {
      Showtime newShowtime = new Showtime(startTime, addTheatre.get());
      newShowtime.setTheatre(addTheatre.get());
      this.showtimeRepository.save(newShowtime);
      return Optional.of(newShowtime);
    }
  }

  public void addShowtimeToMovie(Showtime showtime, String movieName) {
    Optional<Movie> addMovie = this.movieService.searchAllMovies(movieName);
    if (addMovie.isPresent()) {
      showtime.addMovieToShowtime(addMovie);
      showtimeRepository.save(showtime);
    }
  }

  public void deleteShowtime(long id) {
    this.showtimeRepository.deleteById(id);
  }

  // public Optional<Showtime> Showtime findShowtimeById(long id){
  //   return this.showtimeRepository.findById(id);
  // }
}
