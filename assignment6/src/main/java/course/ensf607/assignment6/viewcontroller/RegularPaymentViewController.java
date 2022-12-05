package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.RegularUser;
import course.ensf607.assignment6.entity.Seat;
import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.service.SeatService;
import course.ensf607.assignment6.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/payment/regular")
public class RegularPaymentViewController {

  private final SeatService seatService;

  @Autowired
  public RegularPaymentViewController(SeatService seatService, TicketService ticketService) {
    this.seatService = seatService;
  }

  @PostMapping
  public String paymentConfirmation(
      @ModelAttribute RegularUser user, @RequestParam Long seatId, Model model) {
    model.addAttribute("user", user);
    Seat seat = seatService.SearchSeatById(seatId).get();
    model.addAttribute("seat", seat);
    Showtime showtime = seat.getShowtime();
    model.addAttribute("showtime", showtime);
    Theatre theatre = showtime.getTheatre();
    model.addAttribute("theatre", theatre);
    Movie movie = showtime.getMovie();
    model.addAttribute("movie", movie);

    return "payment-confirmation";
  }
}
