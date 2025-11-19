package DataAccess;

import java.util.List;

public interface IDataAccess<T> {
    T findById(Integer id);
    List<T> findAll();
    void save(T item);
    void delete(Integer id);
}
