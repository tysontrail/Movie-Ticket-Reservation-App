package course.ensf607.assignment6.viewcontroller;

import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.service.TheatreService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/theatre")
public class TheatreViewController {

  private final TheatreService theatreService;

  @Autowired
  public TheatreViewController(TheatreService theatreService) {
    this.theatreService = theatreService;
  }

  @GetMapping
  public String theatres(Model model) {
    Theatre theatre = new Theatre("Cineplex", "123 ave", 10, 10);
    List<Theatre> allTheatres = new ArrayList<Theatre>();
    allTheatres.add(theatre);

    model.addAttribute("theatres", allTheatres);
    return "theatre";
  }
}
