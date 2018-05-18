package eu.kerdev.testApp.service;

import eu.kerdev.testApp.dao.app.ContractDao;
import eu.kerdev.testApp.dao.app.ItSystemDao;
import eu.kerdev.testApp.mappers.dtotoentity.ContractDtoToEntity;
import eu.kerdev.testApp.mappers.entitytodto.ContractEntityToDto;
import eu.kerdev.testApp.model.dto.ContractDto;
import eu.kerdev.testApp.model.entities.app.Contract;
import eu.kerdev.testApp.model.dto.JTableResponse;
import eu.kerdev.testApp.model.dto.JTableResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ContractService {

    private final ContractDao contractDao;
    private final ItSystemDao itSystemDao;
    private final JTableResponseBuilder<ContractDto> responseBuilder;
    private final ContractDtoToEntity contractDtoToEntity;
    private final ContractEntityToDto contractEntityToDto;

    @Autowired
    public ContractService(
            ContractDao contractDao,
            ItSystemDao itSystemDao,
            JTableResponseBuilder<ContractDto> responseBuilder,
            ContractDtoToEntity itSystemDtoToEntity,
            ContractEntityToDto itSystemEntityToDto
    ) {
        this.contractDao = contractDao;
        this.responseBuilder = responseBuilder;
        this.contractDtoToEntity = itSystemDtoToEntity;
        this.contractEntityToDto = itSystemEntityToDto;
        this.itSystemDao = itSystemDao;
    }

    public JTableResponse<ContractDto> listAllContracts() {
        List<Contract> entities = contractDao.readAll();
        List<ContractDto> dtos = new ArrayList<>();
        for (Contract entity : entities) {
            dtos.add(contractEntityToDto.convert(entity));
        }
        return responseBuilder.prepareReadResponse(dtos);
    }

    public JTableResponse<ContractDto> listActiveContracts() {
        List<Contract> entities = contractDao.readActive();
        List<ContractDto> dtos = new ArrayList<>();
        for (Contract entity : entities) {
            dtos.add(contractEntityToDto.convert(entity));
        }
        return responseBuilder.prepareReadResponse(dtos);
    }

    public JTableResponse<ContractDto> addContract(final ContractDto dto) throws ParseException {
        final Contract entity = contractDtoToEntity.convert(dto);
        entity.setSystem(itSystemDao.load(dto.getSystemId()));
        entity.setId(null);
        final Long id = contractDao.create(entity);
        dto.setId(id);
        return responseBuilder.prepareCreateResponse(dto);
    }

    public JTableResponse<ContractDto> updateContract(final ContractDto dto) throws ParseException {
        final Contract entity = contractDtoToEntity.convert(dto);
        entity.setSystem(itSystemDao.load(dto.getSystemId()));
        contractDao.update(entity);
        return responseBuilder.prepareUpdateResponse();
    }

    public JTableResponse<ContractDto> disableContract(ContractDto dto) {
        final Contract entity = contractDao.load(dto.getId());
        entity.setActive(false);
        contractDao.update(entity);
        return responseBuilder.prepareDeleteResponse();
    }
}
