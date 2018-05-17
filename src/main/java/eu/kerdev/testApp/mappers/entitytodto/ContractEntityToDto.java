package eu.kerdev.testApp.mappers.entitytodto;

import eu.kerdev.testApp.mappers.Mapper;
import eu.kerdev.testApp.model.dto.ContractDto;
import eu.kerdev.testApp.model.entities.app.Contract;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ContractEntityToDto implements Mapper<Contract, ContractDto> {

    private final static String DATE_FORMAT = "yyyy-MM-dd";

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
        to.setFromDate(new SimpleDateFormat(DATE_FORMAT).format(from.getFromDate()));
        to.setToDate(new SimpleDateFormat(DATE_FORMAT).format(from.getToDate()));
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
