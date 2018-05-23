package eu.kerdev.testApp.mappers;

/**
 * Interface used for mapper objects
 * @param <FROM> type of object mapped from
 * @param <TO> type of object mapped to
 * @author Michal Jendrzejek
 */
public interface Mapper<FROM, TO> {
    TO convert(FROM from) throws Throwable;
}
