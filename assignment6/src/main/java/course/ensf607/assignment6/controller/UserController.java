package course.ensf607.assignment6.controller;

import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** Rest controller and api endpoint for user stuff. */
@RestController
@CrossOrigin({"*"})
public class UserController {

  /** Aggregate the user service functions with the API */
  @Autowired private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * In case user decides to access the root of API.
   *
   * @return welcome message.
   */
  @GetMapping({"/"})
  public String index() {
    return "Welcome to the movies! Login or sign in as guest to buy a ticket.";
  }

  /**
   * AddUser communicator to frontend.
   *
   * @param user
   */
  @PostMapping({"/api/v1/addregistereduser"})
  public void addUser(@RequestBody User user) {
    this.userService.addUser(user);
  }

  // For the admin just in case.
  @PostMapping({"/api/v1/deleteregistereduser"})
  public void deleteUser(long id) {
    this.userService.deleteUserByID(id);
  }

  // Delete this later.
  @GetMapping({"api/v1/viewallusers"})
  public Iterable<User> viewAllUsers() {
    return this.userService.getAllUsers();
  }
}
