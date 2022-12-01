package course.ensf607.assignment6.service;

import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.repository.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  /** Aggregate the user repository for changes. */
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * AddUser communicator to the database interface.
   *
   * @param user
   */
  public void addUser(User user) {
    Optional<User> checkUser = searchUserByID(user.getId());
    if (checkUser.isPresent()) {
      throw new IllegalStateException("User ID already present in database.");
    }
    this.userRepository.save(user);
  }

  // Same as above but for new users.
  public void addUser(
      String firstName,
      String lastName,
      String email,
      String password,
      int creditCard,
      int cvcNumber,
      int expiryDate) {
    User newUser =
        new User(firstName, lastName, email, password, creditCard, cvcNumber, expiryDate);
    this.userRepository.save(newUser);
  }

  public Optional<User> searchUserByID(long id) {
    return this.userRepository.findUserById(id);
  }

  public Optional<User> searchUserByEmail(String email) {
    return this.userRepository.findUserByEmail(email);
  }

  public void deleteUserByID(long id) {
    this.userRepository.deleteById(id);
  }

  public Iterable<User> getAllUsers() {
    return this.userRepository.findAll();
  }
}
