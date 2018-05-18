package eu.kerdev.testApp.controller.api;

import eu.kerdev.testApp.model.dto.ContractDto;
import eu.kerdev.testApp.model.dto.JTableResponse;
import eu.kerdev.testApp.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("api/contract/")
public class ContractRestController {

    private final ContractService contractService;

    @Autowired
    public ContractRestController(ContractService contractService) {
        this.contractService = contractService;
    }

    @RequestMapping(
            value = "list/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JTableResponse<ContractDto> getContracts() {
        return contractService.listAllContracts();
    }

    @RequestMapping(
            value = "list/active",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JTableResponse<ContractDto> getActiveContracts() {
        return contractService.listActiveContracts();
    }

    @RequestMapping(
            value = "add",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JTableResponse<ContractDto> addContract(
            ContractDto dto
    ) throws ParseException {
        return contractService.addContract(dto);
    }

    @RequestMapping(
            value = "update",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JTableResponse<ContractDto> updateContract(
            ContractDto dto
    ) throws ParseException {
        return contractService.updateContract(dto);
    }

    @RequestMapping(
            value = "disable",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JTableResponse<ContractDto> disableContract(
            ContractDto dto
    ) throws ParseException {
        return contractService.disableContract(dto);
    }
}
