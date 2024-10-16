package app.daos;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class TypeDao implements IDAO
{
    private final EntityManagerFactory emf;

    public TypeDao(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public List getAll()
    {
        return List.of();
    }

    @Override
    public Object getById(long id)
    {
        return null;
    }

    @Override
    public void create(Object o)
    {

    }

    @Override
    public Object update(Object o, Object o2)
    {
        return null;
    }

    @Override
    public void delete(long id)
    {

    }
}
