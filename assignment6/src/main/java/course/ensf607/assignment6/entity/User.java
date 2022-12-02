package course.ensf607.assignment6.entity;

import java.io.Serializable;
import java.time.LocalDate;
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

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @NotEmpty(message = "Username cannot be empty.")
  private String userName;

  @NotEmpty(message = "First name cannot be empty.")
  private String firstName;

  @NotEmpty(message = "Last name cannot be empty.")
  private String lastName;

  @NotEmpty(message = "Email cannot be empty.")
  private String email;

  @NotEmpty(message = "Password cannot be empty.")
  private String password;

  @NotEmpty(message = "Credit Card cannot be empty.")
  private int creditCard;

  @NotEmpty(message = "CVC Number cannot be empty.")
  private int cvcNumber;

  @NotEmpty(message = "Expiry Date cannot be empty.")
  private int expiryDate;

  private LocalDate annualRenewalDate;

  @OneToMany(mappedBy = "user")
  private Set<Ticket> tickets;

  @OneToMany(mappedBy = "user")
  private Set<Payment> payments;

  public User(
      Long id,
      String firstName,
      String lastName,
      String userName,
      String email,
      String password,
      int creditCard,
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
  }

  public User(
      String firstName,
      String lastName,
      String userName,
      String email,
      String password,
      int creditCard,
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
  }
}
