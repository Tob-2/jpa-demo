package int242.jpadao.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "film", schema = "sakila")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer id;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "release_year")
    private Integer releaseYear;

    @ColumnDefault("'G'")
    @Lob
    @Column(name = "rating")
    private String rating;

    @Column(name = "language_id")
    private Integer languageId;

}