package eu.kerdev.testApp.controller.api;

import eu.kerdev.testApp.model.dto.ItSystemDto;
import eu.kerdev.testApp.model.dto.JTableResponse;
import eu.kerdev.testApp.service.ItSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/system/")
public class ItSystemRestController {

    private final ItSystemService itSystemService;

    @Autowired
    public ItSystemRestController(ItSystemService itSystemService) {
        this.itSystemService = itSystemService;
    }

    @RequestMapping(
            value = "list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JTableResponse<ItSystemDto> getSystems() {
        return itSystemService.listAllSystems();
    }

    @RequestMapping(
            value = "add",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JTableResponse<ItSystemDto> addSystem(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "techs", required = false) String techs
    ) {
        final ItSystemDto dto = new ItSystemDto();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        dto.setTechs(techs);
        return itSystemService.addSystem(dto);
    }

    @RequestMapping(
            value = "update",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JTableResponse<ItSystemDto> updateSystem(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "techs", required = false) String techs
    ) {
        final ItSystemDto dto = new ItSystemDto();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        dto.setTechs(techs);
        return itSystemService.updateSystem(dto);
    }

    @RequestMapping(
            value = "options",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JTableResponse getSystemsAsOptions() {
        return itSystemService.listOptions();
    }

}
