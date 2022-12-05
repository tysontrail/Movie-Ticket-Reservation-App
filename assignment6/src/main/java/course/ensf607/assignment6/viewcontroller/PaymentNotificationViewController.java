package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.Payment;
import course.ensf607.assignment6.entity.RegularUser;
import course.ensf607.assignment6.entity.Seat;
import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.entity.Ticket;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.repository.PaymentRepository;
import course.ensf607.assignment6.repository.SeatRepository;
import course.ensf607.assignment6.repository.TicketRepository;
import course.ensf607.assignment6.service.PaymentService;
import course.ensf607.assignment6.service.SeatService;
import course.ensf607.assignment6.service.TicketService;
import course.ensf607.assignment6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/notification")
public class PaymentNotificationViewController {

  private final SeatService seatService;
  private final TicketService ticketService;
  private final UserService userService;
  private final PaymentService paymentService;
  private final PaymentRepository paymentRepository;
  private final SeatRepository seatRepository;
  private final TicketRepository ticketRepository;

  @Autowired
  public PaymentNotificationViewController(
      SeatService seatService,
      TicketService ticketService,
      UserService userService,
      PaymentService paymentService,
      PaymentRepository paymentRepository,
      SeatRepository seatRepository,
      TicketRepository ticketRepository) {
    this.seatService = seatService;
    this.ticketService = ticketService;
    this.userService = userService;
    this.paymentService = paymentService;
    this.paymentRepository = paymentRepository;
    this.seatRepository = seatRepository;
    this.ticketRepository = ticketRepository;
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
      payment.setTicket(ticket);

      paymentRepository.save(payment);
      seatRepository.save(seat);
      ticketRepository.save(ticket);
      model.addAttribute("user", user);
      model.addAttribute("seat", seat);
      model.addAttribute("showtime", showtime);
      model.addAttribute("theatre", theatre);
      model.addAttribute("movie", movie);
      model.addAttribute("ticket", ticket);
      return "payment-receipt";
    } else {
      return "payment-error";
    }
  }

  @GetMapping("/regular")
  public String paymentReceiptRegular(
      @ModelAttribute RegularUser user, @RequestParam Long seatId, Model model) {

    boolean paymentConfirmation;
    Seat seat = seatService.SearchSeatById(seatId).get();
    Showtime showtime = seat.getShowtime();
    Theatre theatre = showtime.getTheatre();
    Movie movie = showtime.getMovie();

    paymentConfirmation =
        paymentService.regularUserPayTicket(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getCreditCard(),
            user.getCvcNumber(),
            user.getExpiryDate());
    if (paymentConfirmation) {
      Payment payment = new Payment(user, seat.getPrice(), paymentConfirmation);
      Ticket ticket = new Ticket(user, theatre, seat, payment, seat.getPrice());
      payment.setTicket(ticket);

      paymentRepository.save(payment);
      seatRepository.save(seat);
      ticketRepository.save(ticket);
      model.addAttribute("user", user);
      model.addAttribute("seat", seat);
      model.addAttribute("showtime", showtime);
      model.addAttribute("theatre", theatre);
      model.addAttribute("movie", movie);
      model.addAttribute("ticket", ticket);
      return "payment-receipt";
    } else {
      return "payment-error";
    }
  }
}
