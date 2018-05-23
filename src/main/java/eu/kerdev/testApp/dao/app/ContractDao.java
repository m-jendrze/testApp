package eu.kerdev.testApp.dao.app;

import eu.kerdev.testApp.dao.GenericCrudDao;
import eu.kerdev.testApp.model.entities.app.Contract;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * DAO for Contract object implementing CRUD methods
 * @see eu.kerdev.testApp.dao.CrudDao
 * @see eu.kerdev.testApp.dao.GenericCrudDao
 * @see eu.kerdev.testApp.model.entities.app.Contract
 * @author Michal Jendrzejek
 */
@Component
public class ContractDao extends GenericCrudDao<Contract, Long> {

    @Autowired
    protected ContractDao(SessionFactory sessionFactory) {
        super(Contract.class, sessionFactory);
    }

    public List<Contract> readActive() {
        return readActive(null);
    }

    public List<Contract> readActive(Integer limit) {
        final CriteriaBuilder cb = getCriteriaBuilder();
        final CriteriaQuery<Contract> criteria = cb.createQuery(type);
        Root<Contract> root = criteria.from(type);
        criteria.select(root);
        criteria.where(cb.equal(root.get("active"), true));
        Query<Contract> query = getSession().createQuery(criteria);
        if (limit != null) {
            query.setMaxResults(limit);
        }
        return query.getResultList();
    }
}
