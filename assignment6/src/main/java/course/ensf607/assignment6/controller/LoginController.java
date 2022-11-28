package com.movie.movie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login(Model model) {
    return "index.html";
  }

  // @PostMapping("/login")
  // public String checkLogin(Model model) {}

  @GetMapping("/register")
  public String register(Model model) {
    return "register.html";
  }
}
