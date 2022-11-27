package course.ensf607.assignment6.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "ticket")
public class Ticket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="theatre_id", nullable=false)
	private Theatre theatre;

    @JsonIgnore
    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Seat seat;

    @JsonIgnore
    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Payment payment;

	private double balance;

    public Ticket(Long id, User user, Theatre theatre, Seat seat, Payment payment, double balance) {
        this.id = id;
        this.user = user;
        this.theatre = theatre;
        this.seat = seat;
        this.payment = payment;
        this.balance = balance;
    }

    public Ticket(User user, Theatre theatre, Seat seat, Payment payment, double balance) {
        this.user = user;
        this.theatre = theatre;
        this.seat = seat;
        this.payment = payment;
        this.balance = balance;
    }

}

