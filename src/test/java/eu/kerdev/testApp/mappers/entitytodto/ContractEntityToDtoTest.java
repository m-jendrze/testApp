package eu.kerdev.testApp.mappers.entitytodto;

import eu.kerdev.testApp.model.AmountPeriod;
import eu.kerdev.testApp.model.AmountType;
import eu.kerdev.testApp.model.dto.ContractDto;
import eu.kerdev.testApp.model.entities.app.Contract;
import eu.kerdev.testApp.model.entities.app.ItSystem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class ContractEntityToDtoTest {

    private final ContractEntityToDto objectUnderTest =
            new ContractEntityToDto();

    @Test
    void convert() throws Exception{
        final Contract contract = new Contract();
        final ItSystem system = new ItSystem();
        final SimpleDateFormat sdf =
                new SimpleDateFormat(objectUnderTest.DATE_FORMAT);
        final String DATE_FROM = "2017-03-04";
        final String DATE_TO = "2017-08-04";

        system.setId(1L);
        contract.setActive(true);
        contract.setAuthorizationPercent(2);
        contract.setAmountPeriod(AmountPeriod.MONTH);
        contract.setAmountType(AmountType.NET);
        contract.setOrderNumber("123");
        contract.setFromDate(sdf.parse(DATE_FROM));
        contract.setToDate(sdf.parse(DATE_TO));
        contract.setRequest("321");
        contract.setId(123L);
        contract.setAmount(new BigDecimal("123.21"));
        contract.setSystem(system);
        final ContractDto contractDto = objectUnderTest.convert(contract);

        assertEquals(contractDto.getToDate(), DATE_TO);
        assertEquals(contractDto.getFromDate(),DATE_FROM);
        assertEquals(contractDto.getActive(), contract.getActive());
        assertEquals(contractDto.getId(), contract.getId());
        assertEquals(contractDto.getAmount(), contract.getAmount());
        assertEquals(contractDto.getAmountPeriod(), contract.getAmountPeriod());
        assertEquals(contractDto.getAmountType(), contract.getAmountType());
        assertEquals(contractDto.getActive(), contract.getActive());
        assertEquals(contractDto.getAuthorizationPercent(), contract.getAuthorizationPercent());
        assertEquals(contractDto.getOrderNumber(), contract.getOrderNumber());
        assertEquals(contractDto.getRequest(), contract.getRequest());
        assertEquals(contractDto.getSystemId(), system.getId());
    }
}