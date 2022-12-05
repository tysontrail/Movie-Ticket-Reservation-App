package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.RegularUser;
import course.ensf607.assignment6.service.SeatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/regular")
public class RegularViewController {
  private final SeatService seatService;

  public RegularViewController(SeatService seatService) {
    this.seatService = seatService;
  }

  @GetMapping
  public String registerForm(@RequestParam Long seatId, Model model) {
    model.addAttribute("seatId", seatId);
    model.addAttribute("user", new RegularUser());
    return "regular";
  }
}
