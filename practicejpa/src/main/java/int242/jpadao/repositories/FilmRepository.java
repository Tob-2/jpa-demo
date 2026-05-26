package int242.jpadao.repositories;

import int242.jpadao.dao.DataRepository;
import int242.jpadao.entities.Film;

public class FilmRepository implements DataRepository<Film, Integer> {
    @Override
    public Class<Film> getEntityClass() {
        return Film.class;
    }
}
