package course.ensf607.assignment6.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {

  private final UserService userService;

  @Autowired
  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String registerForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping
  public String registerSubmit(@Valid User user, BindingResult result, Model model) {

    // Check if submitted User object email already exists
    Optional<User> existingUser = userService.searchUserByEmail(user.getEmail());

    if (!existingUser.isPresent()) {

      // Throw error message and return to form
      ObjectError error = new ObjectError("globalError", "Email belongs to existing user.");
      result.addError(error);
      return "/error/register";
    }
    // Add user to the database
    userService.addUser(user);

    // Render results page
    model.addAttribute("user", user);
    return "result";
  }
}
