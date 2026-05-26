package int242.jpadao.repositories;

import int242.jpadao.dao.DataRepository;
import int242.jpadao.entities.Actor;

public class ActorRepository implements DataRepository<Actor, Integer> {
    @Override
    public Class<Actor> getEntityClass() {
        return Actor.class;
    }
}
