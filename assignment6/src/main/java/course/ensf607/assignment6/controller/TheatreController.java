package course.ensf607.assignment6.controller;

import course.ensf607.assignment6.entity.Movie;
import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.service.TheatreService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
public class TheatreController {

  @Autowired private TheatreService theatreService;

  @Autowired
  public TheatreController(TheatreService theatreService) {
    this.theatreService = theatreService;
  }

  // View all theatres and search theatres can't be viewed due to some issues with
  // the result set eg tickets and seats haven't been created yet.
  // TODO Fix.
  @GetMapping({"api/v1/viewalltheatres"})
  public Iterable<Theatre> viewAllTheatres() {
    return this.theatreService.getAllTheatres();
  }

  @GetMapping({"/api/v1/searchtheatres"})
  public Optional<Theatre> searchTheatres(@RequestParam String name) {
    return this.theatreService.searchTheatreByName(name);
  }

  // Not yet implemented.
  @PostMapping({"/api/v1/selecttheatre"})
  public void selectTheatre(@RequestBody Theatre theTheatre) {
    this.theatreService.selectTheatre(theTheatre);
  }

  @PostMapping({"/api/v1/addtheatre"})
  public void addTheatre(@RequestBody Theatre theatre) {
    this.theatreService.addTheatre(
        theatre.getName(), theatre.getAddress(), theatre.getSeatCols(), theatre.getSeatRows());
  }

  @PostMapping({"/api/v1/deletetheatre"})
  public void deleteTheatre(@RequestParam long id) {
    this.theatreService.deleteTheatre(id);
  }

  @PostMapping({"/api/v1/addmovietotheatre"})
  public void addTheatreToMovie(Movie movie) {}
}
