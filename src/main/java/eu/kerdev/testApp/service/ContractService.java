package eu.kerdev.testApp.service;

import eu.kerdev.testApp.dao.app.ContractDao;
import eu.kerdev.testApp.dao.app.ItSystemDao;
import eu.kerdev.testApp.exceptions.ImportFailed;
import eu.kerdev.testApp.mappers.dtotoentity.ContractDtoToEntity;
import eu.kerdev.testApp.mappers.entitytodto.ContractEntityToDto;
import eu.kerdev.testApp.model.dto.ContractDto;
import eu.kerdev.testApp.model.dto.ResultStatus;
import eu.kerdev.testApp.model.entities.app.Contract;
import eu.kerdev.testApp.model.dto.JTableResponse;
import eu.kerdev.testApp.model.dto.JTableResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for processing contracts data
 * @author Michal Jendrzejek
 */
@Service
@Transactional
public class ContractService {

    private final ContractDao contractDao;
    private final ItSystemDao itSystemDao;
    private final JTableResponseBuilder<ContractDto> responseBuilder;
    private final ContractDtoToEntity contractDtoToEntity;
    private final ContractEntityToDto contractEntityToDto;
    private final ContractImporter contractImporter;

    @Autowired
    public ContractService(
            ContractDao contractDao,
            ItSystemDao itSystemDao,
            JTableResponseBuilder<ContractDto> responseBuilder,
            ContractDtoToEntity itSystemDtoToEntity,
            ContractEntityToDto itSystemEntityToDto,
            ContractImporter contractImporter
    ) {
        this.contractDao = contractDao;
        this.responseBuilder = responseBuilder;
        this.contractDtoToEntity = itSystemDtoToEntity;
        this.contractEntityToDto = itSystemEntityToDto;
        this.itSystemDao = itSystemDao;
        this.contractImporter = contractImporter;
    }

    /**
     * Lists all contracts
     * @return Response with list of contracts
     */
    public JTableResponse<ContractDto> listAllContracts() {
        List<Contract> entities = contractDao.readAll();
        List<ContractDto> dtos = new ArrayList<>();
        for (Contract entity : entities) {
            dtos.add(contractEntityToDto.convert(entity));
        }
        return responseBuilder.prepareReadResponse(dtos);
    }

    /**
     * Lists of only the active contracts
     * @return Response with list of contracts
     */
    public JTableResponse<ContractDto> listActiveContracts() {
        List<Contract> entities = contractDao.readActive();
        List<ContractDto> dtos = new ArrayList<>();
        for (Contract entity : entities) {
            dtos.add(contractEntityToDto.convert(entity));
        }
        return responseBuilder.prepareReadResponse(dtos);
    }

    /**
     * Adds contract to database
     * @param dto contract to save
     * @return Response the added element
     */
    public JTableResponse<ContractDto> addContract(final ContractDto dto) throws ParseException {
        final Contract entity = contractDtoToEntity.convert(dto);
        entity.setSystem(itSystemDao.load(dto.getSystemId()));
        entity.setId(null);
        final Long id = contractDao.create(entity);
        dto.setId(id);
        return responseBuilder.prepareCreateResponse(dto);
    }

    public void importContracts(final MultipartFile file) throws IOException, ImportFailed {
        final List<Contract> contracts =
                contractImporter.importContracts(file.getInputStream());
        for (Contract contract : contracts) {
            contractDao.create(contract);
        }
    }

    /**
     * Updates selected contract
     * @param dto contract to update
     * @return Response with confirmation
     */
    public JTableResponse<ContractDto> updateContract(final ContractDto dto) throws ParseException {
        final Contract entity = contractDtoToEntity.convert(dto);
        entity.setSystem(itSystemDao.load(dto.getSystemId()));
        entity.setActive(contractDao.load(entity.getId()).getActive());
        contractDao.update(entity);
        return responseBuilder.prepareUpdateResponse();
    }

    /**
     * Disabled selected contract
     * @param dto contract to disable
     * @return Response with confirmation
     */
    public JTableResponse<ContractDto> disableContract(ContractDto dto) {
        final Contract entity = contractDao.load(dto.getId());
        entity.setActive(false);
        contractDao.update(entity);
        return responseBuilder.prepareDeleteResponse();
    }
}
