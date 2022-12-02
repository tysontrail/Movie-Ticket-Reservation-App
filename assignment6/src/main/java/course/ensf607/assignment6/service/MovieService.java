package course.ensf607.assignment6.service;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.repository.MovieRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  private final MovieRepository movieRepository;

  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public Optional<Movie> searchAllMovies(String name) {
    //        Iterable<Movie> movieList = getAllMovies();
    //        Optional<Movie> foundMovie = null;
    //        for(Movie m: movieList){
    //            if(m.getName().equals(name)){
    //                foundMovie = Optional.of(m);
    //                break;
    //            }
    //        }
    //        return foundMovie;
    Optional<Movie> foundMovie = movieRepository.findMovieByName(name);
    if (foundMovie.isPresent()) {
      return foundMovie;
    }
    return Optional.empty();
  }

  public Optional<Movie> searchAnnouncedMovies(String name) {
    Iterable<Movie> movieList = getAnnouncedMovies();
    Optional<Movie> foundMovie = null;
    for (Movie m : movieList) {
      if (m.getName().equals(name) && m.getPublicStart().compareTo(LocalDate.now()) <= 0) {
        foundMovie = Optional.of(m);
        break;
      }
    }
    return foundMovie;
  }

  public void selectMovie(Movie selectedMovie) {
    // TODO: include logic for storing the movie in the ticket?
    // Needs to get stored in ticket.
  }

  public void addMovie(Movie movie) {
    Optional<Movie> checkMovie = searchMovieByID(movie.getId());
    if (checkMovie.isPresent()) {
      throw new IllegalStateException("Movie ID already present in database.");
    }
    this.movieRepository.save(movie);
  }

  public void addMovie(
      String name, String description, LocalDate privateStart, LocalDate publicStart) {
    Movie newMovie = new Movie(name, description, privateStart, publicStart);
    Optional<Movie> matchingMovieName = movieRepository.findMovieByName(name);
    if (matchingMovieName.isPresent()) {
      throw new IllegalStateException("Movie name already in database.");
    }
    this.movieRepository.save(newMovie);
  }

  public Optional<Movie> searchMovieByID(long id) {
    return this.movieRepository.findMovieById(id);
  }

  public void deleteMovie(long id) {
    this.movieRepository.deleteById(id);
  }

  public Iterable<Movie> getAllMovies() {
    return this.movieRepository.findAll();
  }

  public Iterable<Movie> getAnnouncedMovies() {
    Iterable<Movie> movieList = this.movieRepository.findAllGreaterThanPublicStart();
    return movieList;
  }
}
