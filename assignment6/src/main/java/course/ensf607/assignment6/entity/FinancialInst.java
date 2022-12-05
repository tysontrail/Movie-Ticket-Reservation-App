package course.ensf607.assignment6.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**Financial Institution class designed to mimic a financial
 * institution's interface.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "financial_institution")
public class FinancialInst implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  protected String firstName;

  protected String lastName;

  protected long creditCard;

  protected int cvcNumber;

  protected int expiryDate;

  public FinancialInst(
      Long id,
      String firstName,
      String lastName,
      long creditCard,
      int cvcNumber,
      int expiryDate) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.creditCard = creditCard;
    this.cvcNumber = cvcNumber;
    this.expiryDate = expiryDate;
  }

  public FinancialInst(
      String firstName,
      String lastName,
      long creditCard,
      int cvcNumber,
      int expiryDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.creditCard = creditCard;
    this.cvcNumber = cvcNumber;
    this.expiryDate = expiryDate;
  }

}

