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
@Table(name = "payment")
public class Payment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

	private double balance;

    private Boolean paid;

    public Payment(Long id, User user, Ticket ticket, double balance, Boolean paid) {
        this.id = id;
        this.user = user;
        this.ticket = ticket;
        this.balance = balance;
        this.paid = paid;
    }

    public Payment(User user, Ticket ticket, double balance, Boolean paid) {
        this.user = user;
        this.ticket = ticket;
        this.balance = balance;
        this.paid = paid;
    }

}

