package eu.kerdev.testApp.mappers.entitytodto;

import eu.kerdev.testApp.mappers.Mapper;
import eu.kerdev.testApp.model.dto.ContractDto;
import eu.kerdev.testApp.model.entities.app.Contract;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Object used to map Contract object from domain object to DTO
 *
 * @author Michal Jendrzejek
 * @see eu.kerdev.testApp.model.dto.ContractDto
 * @see eu.kerdev.testApp.model.entities.app.Contract
 * @see eu.kerdev.testApp.mappers.Mapper
 */
@Component
public class ContractEntityToDto implements Mapper<Contract, ContractDto> {

    public final static String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public ContractDto convert(Contract from) {
        if (from == null) {
            return null;
        }
        final ContractDto to = new ContractDto();
        to.setActive(from.getActive());
        to.setAmount(from.getAmount());
        to.setAmountPeriod(from.getAmountPeriod());
        to.setAmountType(from.getAmountType());
        to.setAuthorizationPercent(from.getAuthorizationPercent());
        if (from.getFromDate() != null) {
            to.setFromDate(new SimpleDateFormat(DATE_FORMAT).format(from.getFromDate()));
        }
        if (from.getToDate() != null) {
            to.setToDate(new SimpleDateFormat(DATE_FORMAT).format(from.getToDate()));
        }
        to.setId(from.getId());
        to.setOrderNumber(from.getOrderNumber());
        to.setRequest(from.getRequest());
        to.setSystemId(
                from.getSystem() != null ?
                        from.getSystem().getId() :
                        null
        );
        return to;
    }
}
