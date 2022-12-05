package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**Showtime controller for testing showtime.html.
 */
@Controller
@RequestMapping(path = "/showtime")
public class ShowtimeViewController {

  private final ShowtimeService showtimeService;

  @Autowired
  public ShowtimeViewController(ShowtimeService showtimeService) {
    this.showtimeService = showtimeService;
  }

  @GetMapping
  public String showtimes(@RequestParam String movieName, Model model) {
    Iterable<Showtime> showtimes = showtimeService.getAllMovieShowtimes(movieName);
    model.addAttribute("showtimes", showtimes);
    boolean registeredUser = User.isLoggedIn();
    model.addAttribute("registeredUser", registeredUser);
    return "showtime";
  }
}
