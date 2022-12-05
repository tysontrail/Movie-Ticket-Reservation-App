package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.MovieService;
import course.ensf607.assignment6.service.ShowtimeService;
import course.ensf607.assignment6.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**View controller for testing on the view forms and templates for the html stuff.
 */
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
    System.out.println(registeredUser);
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
}
