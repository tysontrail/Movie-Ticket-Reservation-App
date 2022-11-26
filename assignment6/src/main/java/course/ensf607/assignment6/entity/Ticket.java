package course.ensf607.assignment6.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    //TODO
    @ManyToOne
    private User user;

    //TODO
    @ManyToOne
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
}

