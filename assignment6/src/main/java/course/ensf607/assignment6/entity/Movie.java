package course.ensf607.assignment6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "movie")
public class Movie implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  private String name;

  private String description;

  private LocalDate privateStart;

  private LocalDate publicStart;

  @JsonIgnore
  @ManyToMany(mappedBy = "movies")
  private Set<Theatre> theatres = new HashSet<Theatre>();

  @JsonIgnore
  @OneToMany(mappedBy = "movie")
  private Set<Showtime> showtimes = new HashSet<Showtime>();

  public Movie(
      Long id, String name, String description, LocalDate privateStart, LocalDate publicStart) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.privateStart = privateStart;
    this.publicStart = publicStart;
  }

  public Movie(String name, String description, LocalDate privateStart, LocalDate publicStart) {
    this.name = name;
    this.description = description;
    this.privateStart = privateStart;
    this.publicStart = publicStart;
  }
}
