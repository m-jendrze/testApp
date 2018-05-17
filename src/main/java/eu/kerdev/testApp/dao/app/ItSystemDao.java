package eu.kerdev.testApp.dao.app;

import eu.kerdev.testApp.dao.GenericCrudDao;
import eu.kerdev.testApp.model.entities.app.ItSystem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("itDao")
public class ItSystemDao extends GenericCrudDao<ItSystem, Long> {

    @Autowired
    protected ItSystemDao(SessionFactory sessionFactory) {
        super(ItSystem.class, sessionFactory);
    }
}
