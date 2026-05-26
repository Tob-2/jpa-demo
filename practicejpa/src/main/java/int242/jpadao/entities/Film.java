package int242.jpadao.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "film", schema = "test242")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}