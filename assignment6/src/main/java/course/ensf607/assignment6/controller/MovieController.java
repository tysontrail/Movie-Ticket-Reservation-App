package course.ensf607.assignment6.controller;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.service.MovieService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
public class MovieController {

  @Autowired private MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping({"/api/v1/searchannouncedmovies"})
  public Optional<Movie> searchAnnouncedMovie(@RequestParam String name) {
    return this.movieService.searchAnnouncedMovies(name);
  }

  @GetMapping({"/api/v1/searchallmovies"})
  public Optional<Movie> searchAllMovies(@RequestParam String name) {
    return this.movieService.searchAllMovies(name);
  }

//  @PostMapping({"/api/v1/selectmovie"})
//  public void selectMovie(@RequestBody Movie theMovie) {
//    this.movieService.selectMovie(theMovie);
//  }

  @PostMapping({"/api/v1/selectmovie"})
  public void selectMovie(@RequestParam String movieName) {
    this.movieService.selectMovie(movieName);
  }

  @PostMapping({"/api/v1/addmovie"})
  public void addMovie(@RequestBody Movie movie) {
    this.movieService.addMovie(
        movie.getName(), movie.getDescription(), movie.getPrivateStart(), movie.getPublicStart());
  }

  @PostMapping({"/api/v1/deletemovie"})
  public void deleteMovie(long id) {
    this.movieService.deleteMovie(id);
  }

  @GetMapping({"api/v1/viewannouncedmovies"})
  public Iterable<Movie> viewAnnouncedMovies() {
    return this.movieService.getAnnouncedMovies();
  }

  @GetMapping({"api/v1/viewallmovies"})
  public Iterable<Movie> viewAllMovies() {
    return this.movieService.getAllMovies();
  }
}
