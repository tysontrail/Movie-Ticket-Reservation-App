package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.User;
<<<<<<< HEAD
=======
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
>>>>>>> 9d09d8b6107a2af8500f44a39339f3331d334073
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** Interface for user part of database. Has a find user by ID function for interface. */
public interface UserRepository extends JpaRepository<User, Long> {
<<<<<<< HEAD
  Optional<User> findUserById(Long id);

  Optional<User> findUserByEmail(String email);
=======
    Optional<User> findUserById(Long id);

    List<User> findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);

    Optional<User> findByUserName(String userName);
>>>>>>> 9d09d8b6107a2af8500f44a39339f3331d334073
}
