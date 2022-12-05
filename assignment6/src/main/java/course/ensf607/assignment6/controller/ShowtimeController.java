package course.ensf607.assignment6.controller;

import course.ensf607.assignment6.entity.Seat;
import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.Theatre;
import course.ensf607.assignment6.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin({"*"})
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    public ShowtimeController(ShowtimeService showtimeService){
        this.showtimeService = showtimeService;
    }

    @PostMapping({"/api/v1/addshowtime"})
    public void addShowtimeToTheatre(@RequestParam
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                         LocalDateTime showtime, @RequestParam String theatreName,
                                            @RequestParam String movieName){
        Optional<Showtime> newShowtime = this.showtimeService.addShowtimeToTheatre(showtime, theatreName);
        this.showtimeService.addShowtimeToMovie(newShowtime.get(), movieName);
    }

    @GetMapping({"/api/v1/viewshowtimes"})
    public Iterable<Showtime> getAllMovieShowtimes(String movieName){
        return this.showtimeService.getAllMovieShowtimes(movieName);
    }

//    // The object version.
//    @PostMapping({"/api/v1/selectshowtime"})
//    public void selectShowtime(@RequestBody Showtime showtime) {
//        this.showtimeService.selectShowtime(showtime);
//    }

    // The ID version, the body/object might be a little complicated b/c of relationships.
    @PostMapping({"/api/v1/selectshowtime"})
    public void selectShowtime(@RequestParam long showtimeId) {
        this.showtimeService.selectShowtime(showtimeId);
    }

    @GetMapping({"/api/v1/getshowtimeseats"})
        public ArrayList<Seat> getShowtimeSeats(Long id){
        return this.showtimeService.getShowtimeSeats(id);
    }
}
