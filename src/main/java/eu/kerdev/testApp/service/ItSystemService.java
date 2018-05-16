package eu.kerdev.testApp.service;

import eu.kerdev.testApp.domain.dao.app.ItSystemDao;
import eu.kerdev.testApp.domain.model.app.ItSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItSystemService {

    private final ItSystemDao itSystemDao;

    @Autowired
    public ItSystemService(ItSystemDao itSystemDao) {
        this.itSystemDao = itSystemDao;
    }

    public List<ItSystem> listAllSystems() {
        List<ItSystem> r = new ArrayList<>();
        r.add(itSystemDao.read(1L));
        return r;
    }
}
