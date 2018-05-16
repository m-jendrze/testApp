package eu.kerdev.testApp.domain.dao;

import eu.kerdev.testApp.domain.model.BaseModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public abstract class GenericCrudDao<MODEL extends BaseModel<PK>, PK extends Serializable>
        implements CrudDao<MODEL, PK>{

    private final Class<MODEL> type;
    private final SessionFactory sessionFactory;

    protected GenericCrudDao(Class<MODEL> type, SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        this.type = type;
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void create(MODEL entity) {
        getSession().persist(entity);
    }

    public MODEL read(PK key) {
        return getSession().get(type, key);
    }

    public void update(MODEL entity) {
        getSession().update(entity);
    }

    public void delete(MODEL entity) {
        getSession().delete(entity);
    }

}
