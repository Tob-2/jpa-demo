package sit.int224.entities;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class Film {
    private int id;
    private String title;
    private String description;
    private String releaseYear;
    private String rating;
}
