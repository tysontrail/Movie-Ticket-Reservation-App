package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.UserService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**Register controller for testing the view for register.html.
 */
@Controller
@RequestMapping(path = "/register")
public class RegisterViewController {

  private final UserService userService;

  @Autowired
  public RegisterViewController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String registerForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping
  public String registerSubmit(
      @Valid @ModelAttribute User user, BindingResult result, Model model) {

    // Return form input errors
    if (result.hasErrors()) {
      return "register";
    }
    // Check if submitted User object email already exists
    Optional<User> existingUser = userService.searchUserByUserName(user.getUserName());

    if (existingUser.isPresent()) {

      // Throw error message and return to form
      ObjectError error = new ObjectError("globalError", "Username belongs to existing user.");
      result.addError(error);
      return "register";
    }
    // Add user to the database
    userService.registerUser(user);

    // Render results page
    model.addAttribute("user", user);
    return "result";
  }
}
