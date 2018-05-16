package eu.kerdev.testApp.controller.rest;

import eu.kerdev.testApp.domain.model.app.ItSystem;
import eu.kerdev.testApp.service.ItSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/")
public class ItSystemRestController {

    private final ItSystemService itSystemService;

    @Autowired
    public ItSystemRestController(ItSystemService itSystemService) {
        this.itSystemService = itSystemService;
    }


    @RequestMapping(value = "systems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItSystem> getSystems() {
        return itSystemService.listAllSystems();
    }
}
