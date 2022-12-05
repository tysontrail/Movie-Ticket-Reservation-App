package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.Payment;
import course.ensf607.assignment6.entity.Seat;
import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.entity.Ticket;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.PaymentService;
import course.ensf607.assignment6.service.SeatService;
import course.ensf607.assignment6.service.TicketService;
import course.ensf607.assignment6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/notification")
public class PaymentNotificationViewController {

  private final SeatService seatService;
  private final TicketService ticketService;
  private final UserService userService;
  private final PaymentService paymentService;

  @Autowired
  public PaymentNotificationViewController(
      PaymentService paymentService,
      SeatService seatService,
      TicketService ticketService,
      UserService userService) {
    this.paymentService = paymentService;
    this.seatService = seatService;
    this.ticketService = ticketService;
    this.userService = userService;
  }

  @GetMapping("/registered")
  public String paymentReceiptRegistered(@RequestParam Long seatId, Model model) {
    boolean paymentConfirmation;

    User user = User.getInstance();
    Seat seat = seatService.SearchSeatById(seatId).get();
    Showtime showtime = seat.getShowtime();
    Theatre theatre = showtime.getTheatre();
    Movie movie = showtime.getMovie();

    paymentConfirmation = paymentService.registeredUserPayTicket(User.getInstance().getUserName());
    if (paymentConfirmation) {
      Payment payment = new Payment(user, seat.getPrice(), paymentConfirmation);
      Ticket ticket = new Ticket(user, theatre, seat, payment, seat.getPrice());
      model.addAttribute("user", user);
      model.addAttribute("seat", seat);
      model.addAttribute("showtime", showtime);
      model.addAttribute("theatre", theatre);
      model.addAttribute("movie", movie);
      model.addAttribute("ticket", ticket);
      return "payment-receipt";
    } else {
      return "You are poor";
    }
  }

  @GetMapping("/regular")
  public String paymentReceiptRegular(@RequestParam Long seatId, Model model) {
    boolean paymentConfirmation;

    // if (User.isLoggedIn()) {
    //   paymentConfirmation =
    //       paymentService.registeredUserPayTicket(User.getInstance().getUserName());
    //   if (paymentConfirmation) {
    //     Payment payment = new Payment(User.getInstance(), seat.getPrice(), paymentConfirmation);
    //     Ticket ticket = new Ticket(User.getInstance(), theatre, seat, payment, seat.getPrice());
    //   }
    // } else {
    //   // paymentConfirmation = paymentService.regularUserPayTicket(user);
    //   // if(paymentConfirmation){
    //   //   Payment payment = new Payment(user, seat.getPrice(), paymentConfirmation);
    //   //   Ticket ticket = new Ticket(user,theatre, seat, payment, seat.getPrice());
    //   // }
    // }
    // if (User.isLoggedIn()) {
    //   model.addAttribute("user", User.getInstance());
    // } else {
    //   model.addAttribute("user", user);
    // }
    // model.addAttribute("user", User.getInstance());
    // model.addAttribute("seat", seat);
    // Showtime showtime = seat.getShowtime();
    // model.addAttribute("showtime", showtime);
    // model.addAttribute("theatre", theatre);
    // Movie movie = showtime.getMovie();
    // model.addAttribute("movie", movie);
    return "payment-receipt";
  }
}
