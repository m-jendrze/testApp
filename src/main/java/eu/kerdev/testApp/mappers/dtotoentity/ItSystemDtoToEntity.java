package eu.kerdev.testApp.mappers.dtotoentity;

import eu.kerdev.testApp.model.entities.app.ItSystem;
import eu.kerdev.testApp.model.dto.ItSystemDto;
import eu.kerdev.testApp.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ItSystemDtoToEntity implements Mapper<ItSystemDto, ItSystem> {

    @Override
    public ItSystem convert(final ItSystemDto from) {
        if (from == null) {
            return null;
        }
        final ItSystem to = new ItSystem();
        to.setDescription(from.getDescription());
        to.setId(from.getId());
        to.setName(from.getName());
        to.setTechs(from.getTechs());
        return to;
    }
}
