package course.ensf607.assignment6;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.repository.MovieRepository;
import course.ensf607.assignment6.repository.ShowtimeRepository;
import course.ensf607.assignment6.repository.TheatreRepository;
import course.ensf607.assignment6.repository.UserRepository;
import course.ensf607.assignment6.service.ShowtimeService;
import course.ensf607.assignment6.service.TheatreService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
  private final UserRepository userRepository;
  private final TheatreRepository theatreRepository;
  private final MovieRepository movieRepository;
  private final ShowtimeRepository showtimeRepository;
  private final ShowtimeService showtimeService;
  private final TheatreService theatreService;

  public DatabaseLoader(
      UserRepository userRepository,
      TheatreRepository theatreRepository,
      MovieRepository movieRepository,
      ShowtimeRepository showtimeRepository,
      ShowtimeService showtimeService,
      TheatreService theatreService) {
    this.userRepository = userRepository;
    this.movieRepository = movieRepository;
    this.showtimeRepository = showtimeRepository;
    this.theatreRepository = theatreRepository;
    this.showtimeService = showtimeService;
    this.theatreService = theatreService;
  }

  @Override
  public void run(String... args) throws Exception {
    // Create some users.
    this.userRepository.save(
        new User(
            "Aaron", "Manuel", "amanuel1", "email@email.com", "password", 12345678, 202, 12232029));
    this.userRepository.save(
        new User(
            "Sue", "Martin", "smartin", "email2@email.com", "password", 87654321, 453, 11052024));
    // Create some movies.
    Movie movie1 =
        new Movie(
            "The Cake Knight Rises", "A cake knight will rise.", LocalDate.now(), LocalDate.now());
    Movie movie2 =
        new Movie(
            "Love, Probably", "A film about love, probably", LocalDate.now(), LocalDate.now());
    this.movieRepository.save(movie1);
    this.movieRepository.save(movie2);

    // Create a theatre.
    Theatre theatre1 = new Theatre("Canyon Meadows Theatre", "13226 Macleod Trail", 10, 10);

    // Add movie to theatre.
    this.theatreService.addMovieToTheatre(theatre1, "The Cake Knight Rises");
    this.theatreService.addMovieToTheatre(theatre1, "Love, Probably");
    //    this.theatreRepository.save(theatre1);

    // Setup the showtimes, then create showtimes with the theatre.
    LocalDateTime dateTime1 = LocalDateTime.of(2022, 12, 06, 14, 30);
    LocalDateTime dateTime2 = LocalDateTime.of(2022, 12, 06, 16, 30);
    Showtime showtime1 = new Showtime(dateTime1, theatre1);
    Showtime showtime2 = new Showtime(dateTime2, theatre1);
    this.showtimeRepository.save(showtime1);
    this.showtimeRepository.save(showtime2);

    // Tie the showtimes to a movie.
    this.showtimeService.addShowtimeToMovie(showtime1, movie1.getName());
    this.showtimeService.addShowtimeToMovie(showtime2, movie2.getName());

    // Save everything.
    this.movieRepository.save(movie1);
    this.movieRepository.save(movie2);
    //    this.theatreRepository.save(theatre1);
    //    this.showtimeRepository.save(showtime1);
    //    this.showtimeRepository.save(showtime2);
  }
}
