package eu.kerdev.testApp.mappers.entitytodto;

import eu.kerdev.testApp.model.dto.ItSystemDto;
import eu.kerdev.testApp.model.entities.app.ItSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItSystemEntityToDtoTest {

    private final ItSystemEntityToDto objectUnderTest =
            new ItSystemEntityToDto();

    @Test
    void convert() {
        final ItSystem system = new ItSystem();
        system.setId(1L);
        system.setDescription("desc");
        system.setName("name");
        system.setTechs("techs");

        final ItSystemDto systemDto = objectUnderTest.convert(system);

        assertEquals(systemDto.getId(), system.getId());
        assertEquals(systemDto.getName(), system.getName());
        assertEquals(systemDto.getDescription(), system.getDescription());
        assertEquals(systemDto.getTechs(), system.getTechs());
    }
}