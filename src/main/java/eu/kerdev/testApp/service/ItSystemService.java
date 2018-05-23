package eu.kerdev.testApp.service;

import eu.kerdev.testApp.dao.app.ItSystemDao;
import eu.kerdev.testApp.mappers.entitytodto.ItSystemEntityToOptionDto;
import eu.kerdev.testApp.model.dto.Option;
import eu.kerdev.testApp.model.entities.app.ItSystem;
import eu.kerdev.testApp.model.dto.ItSystemDto;
import eu.kerdev.testApp.model.dto.JTableResponse;
import eu.kerdev.testApp.model.dto.JTableResponseBuilder;
import eu.kerdev.testApp.mappers.dtotoentity.ItSystemDtoToEntity;
import eu.kerdev.testApp.mappers.entitytodto.ItSystemEntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for processing systems data
 * @author Michal Jendrzejek
 */
@Service
@Transactional
public class ItSystemService {

    private final ItSystemDao itSystemDao;
    private final JTableResponseBuilder<ItSystemDto> responseBuilder;
    private final ItSystemDtoToEntity itSystemDtoToEntity;
    private final ItSystemEntityToDto itSystemEntityToDto;
    private final ItSystemEntityToOptionDto itSystemEntityToOptionDto;

    @Autowired
    public ItSystemService(
            ItSystemDao itSystemDao,
            JTableResponseBuilder<ItSystemDto> responseBuilder,
            ItSystemDtoToEntity itSystemDtoToEntity,
            ItSystemEntityToDto itSystemEntityToDto,
            ItSystemEntityToOptionDto itSystemEntityToOptionDto
    ) {
        this.itSystemDao = itSystemDao;
        this.responseBuilder = responseBuilder;
        this.itSystemDtoToEntity = itSystemDtoToEntity;
        this.itSystemEntityToDto = itSystemEntityToDto;
        this.itSystemEntityToOptionDto = itSystemEntityToOptionDto;
    }

    /**
     * Lists all systems
     * @return Response with list of systems
     */
    public JTableResponse<ItSystemDto> listAllSystems() {
        List<ItSystem> entities = itSystemDao.readAll();
        List<ItSystemDto> dtos = new ArrayList<>();
        for (ItSystem entity : entities) {
            dtos.add(itSystemEntityToDto.convert(entity));
        }
        return responseBuilder.prepareReadResponse(dtos);
    }

    /**
     * Adds system to database
     * @param dto system to save
     * @return Response the added element
     */
    public JTableResponse<ItSystemDto> addSystem(final ItSystemDto dto) {
        final ItSystem entity = itSystemDtoToEntity.convert(dto);
        entity.setId(null);
        final Long id = itSystemDao.create(entity);
        dto.setId(id);
        return responseBuilder.prepareCreateResponse(dto);
    }

    /**
     * Updates selected system
     * @param dto system to update
     * @return Response with confirmation
     */
    public JTableResponse<ItSystemDto> updateSystem(final ItSystemDto dto) {
        final ItSystem entity = itSystemDtoToEntity.convert(dto);
        itSystemDao.update(entity);
        return responseBuilder.prepareUpdateResponse();
    }

    /**
     * Returns all systems as options
     * @return Response with list of options
     */
    public JTableResponse listOptions() {
        List<ItSystem> entities = itSystemDao.readAll();
        List<Option> dtos = new ArrayList<>();
        for (ItSystem entity : entities) {
            dtos.add(itSystemEntityToOptionDto.convert(entity));
        }
        return responseBuilder.prepareOptionsResponse(dtos);
    }
}
