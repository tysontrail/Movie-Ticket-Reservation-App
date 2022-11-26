package course.ensf607.assignment6.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "showtime")
public class Showtime implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

    private Date startTime;

    @ManyToMany
    @JoinTable(
        name = "showtime_movies",
        joinColumns = @JoinColumn(name = "showtime_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Movie movie;

    //TODO
    @ManyToOne
	private Theatre theatre;

    //TODO
    @OneToMany
    private Set<Seat> seats;

}

