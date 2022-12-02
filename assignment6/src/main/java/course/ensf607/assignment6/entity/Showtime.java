package course.ensf607.assignment6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "showtime")
public class Showtime implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  private LocalDateTime startTime;

  @ManyToMany
  @JoinTable(
      name = "showtime_movies",
      joinColumns = @JoinColumn(name = "showtime_id"),
      inverseJoinColumns = @JoinColumn(name = "movie_id"))
  private Set<Movie> showtimeToMovie = new HashSet<Movie>();

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "theatre_id", nullable = false)
  private Theatre theatre;

  @OneToMany(mappedBy = "showtime")
  private Set<Seat> seats;

  public Showtime(Long id, LocalDateTime startTime, Theatre theatre) {
    this.id = id;
    this.startTime = startTime;
    //        this.movie = movie;
    this.theatre = theatre;
    for (int i = 1; i <= theatre.getSeatRows(); i++) {
      for (int j = 1; j <= theatre.getSeatCols(); j++) {
        seats.add(new Seat(this, i, j));
      }
    }
  }

  public Showtime(LocalDateTime startTime, Theatre theatre) {
    this.id = id;
    this.startTime = startTime;
    this.seats = new HashSet<>();
    //        this.movie = movie;
    this.theatre = theatre;
    for (int i = 1; i <= theatre.getSeatRows(); i++) {
      for (int j = 1; j <= theatre.getSeatCols(); j++) {
        seats.add(new Seat(this, i, j));
      }
    }
  }

  public void addMovieToShowtime(Optional<Movie> movie) {
    if (movie.isPresent()) {
      showtimeToMovie.add(movie.get());
    }
  }
}
