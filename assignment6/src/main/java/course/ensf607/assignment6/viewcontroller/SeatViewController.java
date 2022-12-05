package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.Seat;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.ShowtimeService;
import java.util.ArrayList;
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
    ArrayList<Seat> seatsList = showtimeService.getShowtimeSeats(showtimeId);
    ArrayList<ArrayList<Seat>> seats = new ArrayList<ArrayList<Seat>>();

    int counter = 0;

    for (int i = 0; i < 10; i++) {
      ArrayList<Seat> row = new ArrayList<Seat>();
      for (int j = 0; j < 10; j++) {
        row.add(seatsList.get(counter));
        counter++;
      }
      seats.add(i, row);
    }
    model.addAttribute("seats", seats);
    boolean registeredUser = User.isLoggedIn();
    model.addAttribute("registeredUser", registeredUser);
    return "seat-grid";
  }
}
