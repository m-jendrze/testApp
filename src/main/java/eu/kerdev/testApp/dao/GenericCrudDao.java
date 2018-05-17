package eu.kerdev.testApp.dao;

import eu.kerdev.testApp.model.entities.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public abstract class GenericCrudDao<MODEL extends BaseEntity<PK>, PK extends Serializable>
        implements CrudDao<MODEL, PK>{

    protected final Class<MODEL> type;
    private final SessionFactory sessionFactory;

    protected GenericCrudDao(Class<MODEL> type, SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        this.type = type;
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return getSession().getCriteriaBuilder();
    }
    public PK create(MODEL entity) {
        return (PK) getSession().save(entity);
    }

    public MODEL read(PK key) {
        return getSession().get(type, key);
    }

    public List<MODEL> readAll() {
        return readAll(null);
    }

    public List<MODEL> readAll(Integer limit) {
        final CriteriaQuery<MODEL> criteria = getCriteriaBuilder()
                .createQuery(type);
        criteria.select(criteria.from(type));
        Query<MODEL> query = getSession().createQuery(criteria);
        if (limit != null) {
            query.setMaxResults(limit);
        }
        return query.getResultList();
    }

    public MODEL load(PK key) {
        return getSession().load(type, key);
    }

    public void update(MODEL entity) {
        getSession().update(entity);
    }

    public void delete(MODEL entity) {
        getSession().delete(entity);
    }

}
