package course.ensf607.assignment6.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "theatre")
public class Theatre implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String name;

    private String address;

    private int seatCols;

	private int seatRows;

    @OneToMany(mappedBy="theatre")
    private Set<Showtime> showtimes;

    @OneToMany(mappedBy="theatre")
    private Set<Ticket> tickets;

    @ManyToMany
    @JoinTable(
        name = "theatre_movies",
        joinColumns = @JoinColumn(name = "theatre_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id"))
     private Set<Movie> movies = new HashSet<Movie>();

    public Theatre(Long id, String name, String address, int seatCols, int seatRows) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.seatCols = seatCols;
        this.seatRows = seatRows;
    }

    public Theatre(String name, String address, int seatCols, int seatRows) {
        this.name = name;
        this.address = address;
        this.seatCols = seatCols;
        this.seatRows = seatRows;
    }

    public void addMovieToTheatre(Optional<Movie> movie){
        if(movie.isPresent()){
            movies.add(movie.get());
        }
    }

}

