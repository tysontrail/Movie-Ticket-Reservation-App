package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.Seat;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/seat")
public class SeatViewController {

  private final ShowtimeService showtimeService;

  @Autowired
  public SeatViewController(ShowtimeService showtimeService) {
    this.showtimeService = showtimeService;
  }

  @GetMapping
  public String seats(@RequestParam Long showtimeId, Model model) {
    Iterable<Seat> seats = showtimeService.getShowtimeSeats(showtimeId);
    model.addAttribute("seats", seats);
    boolean registeredUser = User.isLoggedIn();
    model.addAttribute("registeredUser", registeredUser);
    return "seat";
  }
}
