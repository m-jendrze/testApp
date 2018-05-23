package eu.kerdev.testApp.dao.app;

import eu.kerdev.testApp.dao.GenericCrudDao;
import eu.kerdev.testApp.model.entities.app.ItSystem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
}
