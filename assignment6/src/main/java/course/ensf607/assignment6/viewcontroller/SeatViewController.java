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
    Iterable<Seat> seats = showtimeService.getShowtimeSeats(showtimeId);
    ArrayList<Seat> row1 = new ArrayList<Seat>();
    ArrayList<Seat> row2 = new ArrayList<Seat>();
    ArrayList<Seat> row3 = new ArrayList<Seat>();
    ArrayList<Seat> row4 = new ArrayList<Seat>();
    ArrayList<Seat> row5 = new ArrayList<Seat>();
    ArrayList<Seat> row6 = new ArrayList<Seat>();
    ArrayList<Seat> row7 = new ArrayList<Seat>();
    ArrayList<Seat> row8 = new ArrayList<Seat>();
    ArrayList<Seat> row9 = new ArrayList<Seat>();
    ArrayList<Seat> row10 = new ArrayList<Seat>();
    ArrayList<ArrayList<Seat>> seats = new ArrayList<ArrayList<Seat>>();
    seats.add(row1);
    seats.add(row2);
    seats.add(row3);
    seats.add(row4);
    seats.add(row5);
    seats.add(row6);
    seats.add(row7);
    seats.add(row8);
    seats.add(row9);
    seats.add(row10);
    model.addAttribute("seats", seats);
    boolean registeredUser = User.isLoggedIn();
    model.addAttribute("registeredUser", registeredUser);
    return "seat-grid";
  }
}
