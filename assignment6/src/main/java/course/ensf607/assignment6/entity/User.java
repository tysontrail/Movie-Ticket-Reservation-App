package course.ensf607.assignment6.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String firstName;

    private String lastName;

	private String email;

	private String password;

    private int creditCard;

    private int cvcNumber;

    private int expiryDate;

    private Date annualRenewalDate;

    //TODO
    @OneToMany
    private Set<Ticket> tickets;

    public User(Long id, String firstName, String lastName, String email, String password, int creditCard,
            int cvcNumber, int expiryDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
        this.cvcNumber = cvcNumber;
        this.expiryDate = expiryDate;
    }

    public User(String firstName, String lastName, String email, String password, int creditCard, int cvcNumber,
            int expiryDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
        this.cvcNumber = cvcNumber;
        this.expiryDate = expiryDate;
    }
}

