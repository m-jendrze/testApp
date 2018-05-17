package eu.kerdev.testApp.mappers;

public interface Mapper<FROM, TO> {
    TO convert(FROM from) throws Throwable;
}
