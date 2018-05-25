package eu.kerdev.testApp.controller.web;

import eu.kerdev.testApp.exceptions.ImportFailed;
import eu.kerdev.testApp.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

/**
 * Web controller returning paths to view pages
 * @author Michal Jendrzejek
 */
@Controller
@RequestMapping("/")
public class AppController {

    private final ContractService contractService;

    public AppController(ContractService contractService) {
        this.contractService = contractService;
    }

    @RequestMapping(value = { "/"}, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String homePage(ModelMap model) {
        return "home";
    }

    @RequestMapping(value = { "/systems"}, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String systems(ModelMap model) {
        return "systems";
    }

    @RequestMapping(value = { "/activeContracts"}, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String activeContracts(ModelMap model) {
        return "activeContracts";
    }

    @RequestMapping(value = { "/allContracts"}, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String allContracts(ModelMap model) {
        return "allContracts";
    }

    @RequestMapping(value = { "/about"}, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String about(ModelMap model) {
        return "about";
    }

    @RequestMapping(value = { "/importContracts"}, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String importContracts(MultipartFile file) throws ParseException, IOException, ImportFailed {
        contractService.importContracts(file);
        return "allContracts";
    }
}
