package course.ensf607.assignment6.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import course.ensf607.assignment6.entity.Seat;
import course.ensf607.assignment6.entity.Showtime;
import course.ensf607.assignment6.entity.Ticket;
import course.ensf607.assignment6.repository.SeatRepository;
import course.ensf607.assignment6.repository.ShowtimeRepository;
@Service
public class SeatService {

    private final SeatRepository seatrepository ;

    // private final ShowtimeRepository showtimeRepository;
    private final ShowtimeService showtimeService;


    private final TicketService ticketService;


    // private Optional<Seat> seats;
    
    public SeatService(SeatRepository seatrepository, ShowtimeService showtimeService, TicketService ticketService) {
        this.seatrepository = seatrepository;
        this.showtimeService = showtimeService;
        this.ticketService = ticketService;
    }




    public Optional<Seat> SearchSeatById(Long id){
        return this.seatrepository.findSeatById(id);
        
    }

    public boolean  isAvailable(Seat seat){
        // Optional<Seat> checkSeat = SearchSeatById(id);
        Optional<Seat> checkSeat = SearchSeatById(seat.getId());


        if (checkSeat.isPresent()){

            return checkSeat.get().isReserved();
        }else{
            return false;
        }

    }


    public Iterable<Seat> ViewAllSeats(){
        return this.seatrepository.findAll();
    }

    public void ReserveSeat(Seat seat){
        Optional<Seat> reserveSeat = SearchSeatById(seat.getId());
        if(reserveSeat.isPresent())
        reserveSeat.get().setReserved(true);
        else{
            throw new IllegalStateException("Seat not found");
        }

    }

    public void CancelSeat(Seat seat){
        Optional<Seat> cancelSeat = SearchSeatById(seat.getId());
        if(cancelSeat.isPresent())
        cancelSeat.get().setReserved(false);
        else{
            throw new IllegalStateException("Seat not found");
        }
    }
    public void addSeatToTicket(Ticket ticket,  Optional<Seat> seat ){
        Optional<Ticket> addTicket = this.ticketService.searchTicketById(ticket.getId());
       
        if (addTicket.isPresent()){
            seat.get().setTicket(addTicket.get());
        }else{
            throw new IllegalStateException("Could not find ticket");
        }
    }



    public void addSeatToShowtime(Showtime showtime, Seat seat){
    //  showtimeService.selectShowtime(showtime);
     seat.setShowtime(showtime);
     }

    


}
