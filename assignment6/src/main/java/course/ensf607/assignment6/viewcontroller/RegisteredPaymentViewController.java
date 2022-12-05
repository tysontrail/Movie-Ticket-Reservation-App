package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.Seat;
import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.entity.Ticket;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.SeatService;
import course.ensf607.assignment6.service.TicketService;
import course.ensf607.assignment6.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**Registered payment controller for testing the view controller on the html templates in resources..
 */
@Controller
@RequestMapping(path = "/payment")
public class RegisteredPaymentViewController {

  private final SeatService seatService;
  private final TicketService ticketService;
  private final UserService userService;

  @Autowired
  public RegisteredPaymentViewController(
      SeatService seatService, TicketService ticketService, UserService userService) {
    this.seatService = seatService;
    this.ticketService = ticketService;
    this.userService = userService;
  }

  @GetMapping
  public String paymentConfirmation(@RequestParam Long seatId, Model model) {

    User user = User.getInstance();
    model.addAttribute("user", user);
    Seat seat = seatService.SearchSeatById(seatId).get();
    model.addAttribute("sear", seat);
    Showtime showtime = seat.getShowtime();
    model.addAttribute("showtime", showtime);
    Theatre theatre = showtime.getTheatre();
    model.addAttribute("theatre", theatre);
    Movie movie = showtime.getMovie();
    model.addAttribute("movie", movie);

    return "payment-confirmation";
  }

  @GetMapping("/result")
  public String paymentResult(@RequestParam Long seatId, Long userId, Model model) {
    // Seat seat = seatService.getSeatById();
    // model.addAttribute("seat", seat);
    User user;
    boolean registeredUser = User.isLoggedIn();
    if (registeredUser) {
      user = User.getInstance();
    } else {
      Optional<User> userOptional = userService.searchUserByID(userId);
      if (userOptional.isPresent()) user = userOptional.get();
    }
    // 'model.addAttribute("user", user);

    Ticket ticket = new Ticket();
    ticketService.addTicket(ticket);
    // userService.addTicketToUser(user.getId(), ticket.getId());
    // seatService.addTicketToSeat(seat.getId(), ticket.getId());
    model.addAttribute("ticket", ticket);
    return "payment";
  }
}
