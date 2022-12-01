package course.ensf607.assignment6.service;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.repository.MovieRepository;
import course.ensf607.assignment6.repository.ShowtimeRepository;
import course.ensf607.assignment6.repository.TheatreRepository;
import course.ensf607.assignment6.repository.UserRepository;
import org.hibernate.dialect.Database;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TheatreRepository theatreRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;

    public DatabaseLoader(UserRepository userRepository, TheatreRepository theatreRepository,
                          MovieRepository movieRepository, ShowtimeRepository showtimeRepository){
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.showtimeRepository = showtimeRepository;
        this.theatreRepository = theatreRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        this.userRepository.save(new User("Aaron", "Manuel", "amanuel1", "email@email.com", "password", 12345678, 202, 12232020));
        this.movieRepository.save(new Movie("The Cake Knight Rises", "A cake knight will rise.", LocalDate.now(), LocalDate.now()));
        this.theatreRepository.save(new Theatre("Canyon Meadows Theatre", "13226 Macleod Trail", 10, 10));
//        this.showtimeRepository.save(new Showtime());
    }
}
