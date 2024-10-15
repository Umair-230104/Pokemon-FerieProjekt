package app.daos;

import java.util.List;

public interface IDAO<T, I>
{
    List<T> getAll();

    T getById(long id);

    void create(T t);

    T update(I i, T t);

    void delete(long id);
}