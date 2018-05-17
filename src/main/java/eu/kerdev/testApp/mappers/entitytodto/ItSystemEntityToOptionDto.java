package eu.kerdev.testApp.mappers.entitytodto;

import eu.kerdev.testApp.mappers.Mapper;
import eu.kerdev.testApp.model.dto.Option;
import eu.kerdev.testApp.model.entities.app.ItSystem;
import org.springframework.stereotype.Component;

@Component
public class ItSystemEntityToOptionDto implements Mapper<ItSystem, Option> {
    @Override
    public Option convert(ItSystem from) {
        if (from == null) {
            return null;
        }
        final Option to = new Option();
        to.setValue(from.getId().toString());
        to.setDisplayText(from.getName());
        return to;
    }
}
