package course.ensf607.assignment6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket implements Serializable {

  private static Ticket ticketInstance;

  public static Ticket getInstance() {
    if (ticketInstance == null) {
      ticketInstance = new Ticket();
    }
    return ticketInstance;
  }

  public static void setInstance(Ticket ticket) {
    ticketInstance = ticket;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = true)
  private User user;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "theatre_id", nullable = true)
  private Theatre theatre;

  /**
   * Movie name string for info purposes and for cancellation/voucher checking. Don't need to have
   * database relationship because it's nullable.
   */
  private String movieName;

  /**
   * Movie name string for info purposes and for cancellation/voucher checking. Don't need to have
   * database relationship because it's nullable
   */
  private LocalDateTime showtime;

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

  public Ticket(Theatre theatre, Seat seat) {
    this.theatre = theatre;
    this.seat = seat;
  }
}
