// package com.movie.movie;
package course.ensf607.assignment6.controller;

import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.LoginService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**Login api used mostly for testing on postman.
 */
@RestController
@CrossOrigin({"*"})
public class LoginController {
  private LoginService loginService;

  @Autowired
  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/api/v1/authenticate")
  public Optional<User> submitLogin(@RequestParam String username, @RequestParam String password) {
    return loginService.authenticate(username, password);
  }
}
