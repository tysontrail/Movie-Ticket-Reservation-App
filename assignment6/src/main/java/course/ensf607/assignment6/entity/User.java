package course.ensf607.assignment6.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {

  protected static User onlyInstance;

  public static User getInstance() {
    return onlyInstance;
  }

  public static void setInstance(User user) {
    onlyInstance = user;
  }

  public static void setInstanceNull() {
    onlyInstance = null;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @NotEmpty(message = "Username cannot be empty.")
  private String userName;

  @NotEmpty(message = "First name cannot be empty.")
  protected String firstName;

  @NotEmpty(message = "Last name cannot be empty.")
  protected String lastName;

  @NotEmpty(message = "Email cannot be empty.")
  protected String email;

  @NotEmpty(message = "Password cannot be empty.")
  protected String password;

  // @NotNull(message = "Credit Card cannot be empty.")
  // @Range(min = 16, max = 16, message = "Credit Card should be 16 digits.")
  protected long creditCard;

  // @NotNull(message = "CVC cannot be empty.")
  // @Range(min = 3, max = 3, message = "CVC should be 3 digits.")
  protected int cvcNumber;

  // @NotNull(message = "Expiry Date cannot be empty.")
  // @Range(min = 4, max = 4, message = "Expiry Date should be 4 digits (MMYY)")
  protected int expiryDate;

  private LocalDate annualRenewalDate;

  private boolean paidAnnualFee;

  @OneToMany(mappedBy = "user")
  private Set<Ticket> tickets = new HashSet<>();

  @OneToMany(mappedBy = "user")
  private Set<Payment> payments = new HashSet<>();

  public User(
      Long id,
      String firstName,
      String lastName,
      String userName,
      String email,
      String password,
      long creditCard,
      int cvcNumber,
      int expiryDate) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.creditCard = creditCard;
    this.cvcNumber = cvcNumber;
    this.expiryDate = expiryDate;
    this.annualRenewalDate = LocalDate.now();
    this.paidAnnualFee = false;
  }

  public User(
      String firstName,
      String lastName,
      String userName,
      String email,
      String password,
      long creditCard,
      int cvcNumber,
      int expiryDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.creditCard = creditCard;
    this.cvcNumber = cvcNumber;
    this.expiryDate = expiryDate;
    this.annualRenewalDate = LocalDate.now();
    this.paidAnnualFee = false;
  }

  public void addTicket(Ticket ticket){
    ticket.setUser(this);
    tickets.add(ticket);
  }
}

