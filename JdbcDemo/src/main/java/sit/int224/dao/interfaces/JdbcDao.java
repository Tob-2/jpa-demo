package sit.int224.dao.interfaces;
import java.util.List;
import java.util.Optional;
public interface JdbcDao <T, I> {
    Optional<T> find(I id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(I id);
}
