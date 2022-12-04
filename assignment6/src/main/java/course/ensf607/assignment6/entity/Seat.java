package course.ensf607.assignment6.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "seat")
public class Seat implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name="showtime_id", nullable=false)
	private Showtime showtime;

	private boolean reserved;

    private int seatRow;

    private int seatColumn;

    public Seat(Long id, Ticket ticket, Showtime showtime, int row, int column) {
        this.id = id;
        this.ticket = ticket;
        this.showtime = showtime;
        this.seatRow = row;
        this.seatColumn = column;
        this.reserved = false;
    }

    public Seat(Ticket ticket, Showtime showtime, int row, int column) {
        this.ticket = ticket;
        this.showtime = showtime;
        this.seatRow = row;
        this.seatColumn = column;
        this.reserved = false;
    }

    public Seat(Showtime showtime, int row, int column) {
        this.showtime = showtime;
        this.seatRow = row;
        this.seatColumn = column;
        this.reserved = false;
    }

    public Seat(Long id, Showtime showtime, int row, int column) {
        this.id = id;
        this.showtime = showtime;
        this.seatRow = row;
        this.seatColumn = column;
        this.reserved = false;
    }

    // public void ReserveSeat(Ticket ticket, Showtime showtime,Long id, int row, int column ){
    //     this.ticket = ticket;
    //     this.showtime = showtime;
    //     this.id = id;
    //     this.seatRow = row;
    //     this.seatColumn = column;
    //     this.reserved = true;        

    // }



}

