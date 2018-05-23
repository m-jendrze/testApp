package eu.kerdev.testApp.dao;

import eu.kerdev.testApp.model.entities.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Abstract class implementing basic CRUD methods.
 * Application DAO objects that implement CRUD should extend
 * this class.
 * @param <MODEL> domain object extending BaseEntity
 * @param <PK> primary key used by domain object
 * @see eu.kerdev.testApp.model.entities.BaseEntity
 * @see eu.kerdev.testApp.dao.CrudDao
 * @author Michal Jendrzejek
 */
public abstract class GenericCrudDao<MODEL extends BaseEntity<PK>, PK extends Serializable>
        implements CrudDao<MODEL, PK>{

    protected final Class<MODEL> type;
    private final SessionFactory sessionFactory;

    /**
     * Constructor requiring to pass domain object type used by DAO
     * @param type class of domain object
     * @param sessionFactory session factory for transactions
     */
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

    @SuppressWarnings("unchecked")
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
        getSession().merge(entity);
    }

    public void delete(MODEL entity) {
        getSession().delete(entity);
    }

}
