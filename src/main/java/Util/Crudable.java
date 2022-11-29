package Util;
import java.util.List;

public interface Crudable<T> {
    T create (T newObject);

    List<T> findAll();
    T findByUsername(String username);

    boolean update(T updatedObject);

    boolean delete(String username);
}
