package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.UserService;
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

@Controller
@RequestMapping(path = "/login")
public class LoginViewController {

  private final UserService userService;

  @Autowired
  public LoginViewController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String login(Model model) {
    model.addAttribute("user", new User());
    return "login";
  }

  @GetMapping("/register")
  public String register(Model model) {
    return "register";
  }

  @PostMapping
  public String loginSubmit(@Valid @ModelAttribute User user, BindingResult result, Model model) {
    // Return form input errors
    if (result.hasErrors()) {
      return "login";
    }

    // Check if submitted Username and password are correct
    String errorMessage = userService.authenticate(user.getUserName(), user.getPassword());

    if (!errorMessage.isEmpty()) {
      // Throw error message and return to form
      ObjectError error = new ObjectError("globalError", errorMessage);
      result.addError(error);
      return "login";
    }

    return "theatre";
  }
}
