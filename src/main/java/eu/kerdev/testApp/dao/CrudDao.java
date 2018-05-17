package eu.kerdev.testApp.dao;

public interface CrudDao<MODEL, PK> {

    PK create(MODEL entity);
    MODEL read(PK key);
    void update(MODEL entity);
    void delete(MODEL entity);
}
