package course.ensf607.assignment6.service;

import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Login service/controller for logging in as user, is a controller class but is a spring boot service class.
 */
@Service
public class LoginService {

  @Autowired private UserRepository userRepository;

  @Autowired
  public LoginService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> authenticate(String username, String password) {
    Optional<User> foundUser = this.userRepository.findByUserName(username);
    if (foundUser.get().getPassword().equals(password)) {
      return foundUser;
    } else {
      return Optional.empty();
    }
  }
}
