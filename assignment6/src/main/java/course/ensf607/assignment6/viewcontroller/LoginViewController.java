package course.ensf607.assignment6.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/register")
public class LoginViewController {

  @GetMapping("/login")
  public String login(Model model) {
    return "index.html";
  }

  @GetMapping("/register")
  public String register(Model model) {
    return "register.html";
  }
}
