package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**Interface for the movie database.
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findMovieById(Long id);

    //For search all movies
    Optional<Movie> findMovieByName(String name);

    //For search announced movies only for reg users. Uses jpa custom query.
    @Query(value="SELECT * FROM movie m WHERE m.public_start <= CURDATE();", nativeQuery = true)
    List<Movie> findAllGreaterThanPublicStart();

}
