package eu.kerdev.testApp.dao.app;

import eu.kerdev.testApp.dao.GenericCrudDao;
import eu.kerdev.testApp.model.entities.app.ItSystem;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * DAO for ItSystem object implementing CRUD methods
 * @see eu.kerdev.testApp.dao.CrudDao
 * @see eu.kerdev.testApp.dao.GenericCrudDao
 * @see eu.kerdev.testApp.model.entities.app.ItSystem
 * @author Michal Jendrzejek
 */
@Repository("itDao")
public class ItSystemDao extends GenericCrudDao<ItSystem, Long> {

    @Autowired
    protected ItSystemDao(SessionFactory sessionFactory) {
        super(ItSystem.class, sessionFactory);
    }

    public ItSystem getSystemByName(String name) {
        final CriteriaBuilder cb = getCriteriaBuilder();
        final CriteriaQuery<ItSystem> criteria = cb.createQuery(type);
        Root<ItSystem> root = criteria.from(type);
        criteria.select(root);
        criteria.where(cb.equal(root.get("name"), name));
        Query<ItSystem> query = getSession().createQuery(criteria);
        ItSystem system;
        try {
            system = query.getSingleResult();
        } catch (NoResultException e) {
            system = null;
        }
        return system;
    }
}
