package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.MovieService;
import course.ensf607.assignment6.service.ShowtimeService;
import course.ensf607.assignment6.service.TheatreService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/movie")
public class MovieViewController {

  private final MovieService movieService;

  @Autowired
  public MovieViewController(
      MovieService movieService, TheatreService theatreService, ShowtimeService showtimeService) {
    this.movieService = movieService;
  }

  @GetMapping
  public String movies(Model model) {
    boolean registeredUser = User.isLoggedIn();
    Iterable<Movie> allMovies;
    // If there's a logged in registered user
    if (registeredUser) {
      allMovies = movieService.getAllMovies();
    } else {
      allMovies = movieService.getAnnouncedMovies();
    }
    model.addAttribute("movies", allMovies);
    model.addAttribute("registeredUser", registeredUser);
    return "movie";
  }

  @GetMapping("/menu")
  public String movieMenu(Model model) {

    boolean registeredUser = User.isLoggedIn();
    // If there's a logged in registered user
    model.addAttribute("movie", new Movie());
    model.addAttribute("registeredUser", registeredUser);
    return "movie-menu";
  }

  @PostMapping
  public String movieSearch(@ModelAttribute Movie movie, BindingResult result, Model model) {
    boolean registeredUser = User.isLoggedIn();
    Optional<Movie> movieResult;

    if (registeredUser) {
      movieResult = movieService.searchAllMovies(movie.getName());
    } else {
      movieResult = movieService.searchAnnouncedMovies(movie.getName());
    }

    if (!movieResult.isPresent()) {
      // Throw error message and return to form
      ObjectError error = new ObjectError("globalError", "Movie not available.");
      result.addError(error);
      return "movie-menu";
    }

    model.addAttribute("movie", movieResult.get());
    model.addAttribute("registeredUser", registeredUser);
    return "movie-search-results";
  }
}
