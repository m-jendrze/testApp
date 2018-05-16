package eu.kerdev.testApp.domain.dao;

public interface CrudDao<MODEL, PK> {

    void create(MODEL entity);
    MODEL read(PK key);
    void update(MODEL entity);
    void delete(MODEL entity);
}
