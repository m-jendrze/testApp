package eu.kerdev.testApp.mappers.entitytodto;

import eu.kerdev.testApp.model.entities.app.ItSystem;
import eu.kerdev.testApp.model.dto.ItSystemDto;
import eu.kerdev.testApp.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ItSystemEntityToDto implements Mapper<ItSystem, ItSystemDto> {

    @Override
    public ItSystemDto convert(final ItSystem from) {
        if (from == null) {
            return null;
        }
        final ItSystemDto to = new ItSystemDto();
        to.setDescription(from.getDescription());
        to.setId(from.getId());
        to.setName(from.getName());
        to.setTechs(from.getTechs());
        return to;
    }
}
