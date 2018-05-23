package eu.kerdev.testApp.dao;

/**
 * Interface for all CRUD DAOs
 * @param <MODEL> domain object extending BaseEntity
 * @param <PK> primary key used by domain object
 * @author Michal Jendrzejek
 */
public interface CrudDao<MODEL, PK> {

    PK create(MODEL entity);
    MODEL read(PK key);
    void update(MODEL entity);
    void delete(MODEL entity);
}
