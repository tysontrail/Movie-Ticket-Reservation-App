package course.ensf607.assignment6.controller;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.repository.MovieRepository;
import course.ensf607.assignment6.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
public class ShowtimeService {

    private final TheatreService theatreService;
    private final MovieService movieService;
    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(TheatreService theatreService, ShowtimeRepository showtimeRepository,
                           MovieService movieService){
        this.theatreService = theatreService;
        this.showtimeRepository = showtimeRepository;
        this.movieService = movieService;
    }

    public void selectShowtime(){

    }

//    public Iterable<Showtime> getAllTheatreShowtimes(String theatreName){
//
//        return null;
//    }

    public Iterable<Showtime> getAllMovieShowtimes(String movieName){
        return showtimeRepository.findAllShowtimesByMovie(movieName);
    }

    public void addShowtimeToTheatre(LocalDateTime startTime, Theatre theatre){
        Showtime newShowtime = new Showtime(startTime, theatre);
        showtimeRepository.save(newShowtime);
    }

    public Optional<Showtime> addShowtimeToTheatre(LocalDateTime startTime, String theatreName){
        Optional<Theatre> addTheatre = this.theatreService.searchTheatreByName(theatreName);
        if(!addTheatre.isPresent()){
            throw new IllegalStateException("Could not find theatre.");
        }
        else{
            Showtime newShowtime = new Showtime(startTime, addTheatre.get());;
            newShowtime.setTheatre(addTheatre.get());
            this.showtimeRepository.save(newShowtime);
            return Optional.of(newShowtime);
        }
    }

    public void addShowtimeToMovie(Showtime showtime, String movieName){
        Optional<Movie> addMovie = this.movieService.searchAllMovies(movieName);
        if(addMovie.isPresent()){
            showtime.addMovieToShowtime(addMovie);
            showtimeRepository.save(showtime);
        }
    }

    public void deleteShowtime(long id){
        this.showtimeRepository.deleteById(id);
    }
}
