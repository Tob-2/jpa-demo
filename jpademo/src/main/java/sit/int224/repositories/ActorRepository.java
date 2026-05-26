package sit.int224.repositories;

import sit.int224.models.Actor;

import java.util.List;
import java.util.Optional;

public class ActorRepository implements DataRepository<Actor, Integer>{

    @Override
    public Actor save(Actor entity) {
        return null;
    }

    @Override
    public List<Actor> findAll() {
        return List.of();
    }

    @Override
    public Optional<Actor> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(Actor entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
