package eu.kerdev.testApp.mappers.dtotoentity;

import eu.kerdev.testApp.model.dto.ItSystemDto;
import eu.kerdev.testApp.model.entities.app.ItSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItSystemDtoToEntityTest {

    private final ItSystemDtoToEntity objectUnderTest =
            new ItSystemDtoToEntity();
    @Test
    void convert() {
        final ItSystemDto systemDto = new ItSystemDto();
        systemDto.setId(1L);
        systemDto.setDescription("desc");
        systemDto.setName("name");
        systemDto.setTechs("techs");

        final ItSystem system = objectUnderTest.convert(systemDto);

        assertEquals(system.getId(), systemDto.getId());
        assertEquals(system.getName(), systemDto.getName());
        assertEquals(system.getDescription(), systemDto.getDescription());
        assertEquals(system.getTechs(), systemDto.getTechs());
    }
}