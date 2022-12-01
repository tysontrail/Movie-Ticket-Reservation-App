package course.ensf607.assignment6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

  @GetMapping
  public String login(Model model) {
    return "login.html";
  }

  @GetMapping("/register")
  public String register(Model model) {
    return "register.html";
  }
}
