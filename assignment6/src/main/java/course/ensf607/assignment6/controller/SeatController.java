package course.ensf607.assignment6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import course.ensf607.assignment6.entity.Seat;
import course.ensf607.assignment6.service.SeatService;

/**Seat controller class used mostly for testing on postman.
 * Note that controller means rest controller as per springboot.
 */
@RestController
@CrossOrigin({"*"})
public class SeatController {

@Autowired
private SeatService seatservice;

@Autowired
public SeatController (SeatService seatService){
    this.seatservice = seatService;
}

@PostMapping({"/api/v1/cancelSeat"})
public void cancelSeat(Seat seat ){
    this.seatservice.CancelSeat(seat);
}

@PostMapping({"/api/v1/ReserveSeat"})
public void ReserveSeat(@RequestParam Seat seat ){
    this.seatservice.ReserveSeat(seat);
}

@GetMapping({"/api/v1/ViewAllSeats"})
public Iterable<Seat> ViewAllSeats(){
    return this.seatservice.ViewAllSeats();
}


@GetMapping({"/api/v1/isAvailable"})
public boolean isAvailable(@RequestBody Seat seat){
    return this.seatservice.isAvailable(seat);
}

@GetMapping({"/api/v1/searchSeatById"})
public void searchSeatById(@RequestParam Long id ){
    this.seatservice.SearchSeatById(id);
}

}
