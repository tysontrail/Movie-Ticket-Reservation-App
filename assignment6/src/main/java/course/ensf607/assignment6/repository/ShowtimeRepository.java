package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

  // @Query(value="SELECT showtime.id, showtime.theatre_id, showtime.start_time " +
  //         "FROM (showtime JOIN showtime_movies ON showtime.id=showtime_movies.showtime_id) " +
  //         "JOIN movie ON movie.id = showtime_movies.movie_id " +
  //         "WHERE movie.name = :moviename", nativeQuery = true)
  // List<Showtime> findAllShowtimesByMovie(@Param("moviename") String moviename);
  Iterable<Showtime> findByMovieName(String movieName);
}
