package course.ensf607.assignment6.controller;

import course.ensf607.assignment6.entity.Ticket;
import course.ensf607.assignment6.service.TicketService;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin({"*"})
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @GetMapping({"/api/v1/searchtickets"})
    public Optional<Ticket> searchTicket(@RequestParam Long ticketId){
        return this.ticketService.searchTicketById(ticketId);
    }

    @PostMapping({"/api/v1/cancelticket"})
    public Optional<Ticket> cancelTicket(@RequestParam Long ticketId){
        return this.ticketService.cancelTicket(ticketId);
    }

}
