package eu.kerdev.testApp.mappers.entitytodto;

import eu.kerdev.testApp.mappers.Mapper;
import eu.kerdev.testApp.model.dto.Option;
import eu.kerdev.testApp.model.entities.app.ItSystem;
import org.springframework.stereotype.Component;

/**
 * Object used to map ItSystem object from domain object to
 * Option object used by JTable
 * @see eu.kerdev.testApp.model.dto.ItSystemDto
 * @see eu.kerdev.testApp.model.entities.app.ItSystem
 * @see eu.kerdev.testApp.mappers.Mapper
 * @author Michal Jendrzejek
 */
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
