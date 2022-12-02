package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** Interface for user part of database. Has a find user by ID function for interface. */
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserById(Long id);

  Optional<User> findUserByEmail(String email);

  List<User> findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);

  Optional<User> findByUserName(String userName);
}
