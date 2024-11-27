package dao;

import java.util.List;

public interface BaseDAO<T> {
    List<T> getAll();
    T getByID(int id);
    void add(T entity);
    void update(int id, T updatedEntity);
    void delete(int id);
}