package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/theatre")
public class TheatreViewController {

  private final TheatreService theatreService;

  @Autowired
  public TheatreViewController(TheatreService theatreService) {
    this.theatreService = theatreService;
  }

  @GetMapping
  public String theatres(@RequestParam String movieName, Model model) {
    Iterable<Theatre> allTheatres = theatreService.getAllTheatres();
    model.addAttribute("theatres", allTheatres);
    model.addAttribute("movieName", movieName);
    boolean registeredUser = User.isLoggedIn();
    model.addAttribute("registeredUser", registeredUser);
    return "theatre";
  }
}
