package eu.kerdev.testApp.mappers.dtotoentity;

import eu.kerdev.testApp.mappers.Mapper;
import eu.kerdev.testApp.model.dto.ContractDto;
import eu.kerdev.testApp.model.entities.app.Contract;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class ContractDtoToEntity implements Mapper<ContractDto, Contract>{

    private final static String DATE_FORMAT = "dd-MM-yyyy";

    @Override
    public Contract convert(ContractDto from) throws ParseException {
        if (from == null) {
            return null;
        }
        final Contract to = new Contract();
        to.setActive(from.getActive());
        to.setAmount(from.getAmount());
        to.setAmountPeriod(from.getAmountPeriod());
        to.setAmountType(from.getAmountType());
        to.setAuthorizationPercent(from.getAuthorizationPercent());
        if (from.getFromDate() != null) {
            to.setFromDate(new SimpleDateFormat(DATE_FORMAT).parse(from.getFromDate()));
        }
        if (from.getToDate() != null) {
            to.setToDate(new SimpleDateFormat(DATE_FORMAT).parse(from.getToDate()));
        }
        to.setId(from.getId());
        to.setOrderNumber(from.getOrderNumber());
        to.setRequest(from.getRequest());
        return to;
    }
}
