package eu.kerdev.testApp.mappers.dtotoentity;

import eu.kerdev.testApp.mappers.entitytodto.ContractEntityToDto;
import eu.kerdev.testApp.model.AmountPeriod;
import eu.kerdev.testApp.model.AmountType;
import eu.kerdev.testApp.model.dto.ContractDto;
import eu.kerdev.testApp.model.entities.app.Contract;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class ContractDtoToEntityTest {

    private final ContractDtoToEntity objectUnderTest =
            new ContractDtoToEntity();

    @Test
    void convert() throws Exception {
        final ContractDto contractDto = new ContractDto();
        final SimpleDateFormat sdf =
                new SimpleDateFormat(objectUnderTest.DATE_FORMAT);
        contractDto.setActive(true);
        contractDto.setAuthorizationPercent(2);
        contractDto.setAmountPeriod(AmountPeriod.MONTH);
        contractDto.setAmountType(AmountType.NET);
        contractDto.setOrderNumber("123");
        contractDto.setFromDate("2017-08-04");
        contractDto.setToDate("2017-03-04");
        contractDto.setRequest("321");
        contractDto.setId(123L);
        contractDto.setAmount(new BigDecimal("123.21"));
        final Contract contract = objectUnderTest.convert(contractDto);

        assertEquals(
                contract.getToDate(),
                sdf.parse(contractDto.getToDate())
        );
        assertEquals(
                contract.getFromDate(),
                sdf.parse(contractDto.getFromDate())
        );
        assertEquals(contract.getActive(), contractDto.getActive());
        assertEquals(contract.getId(), contractDto.getId());
        assertEquals(contract.getAmount(), contractDto.getAmount());
        assertEquals(contract.getAmountPeriod(), contractDto.getAmountPeriod());
        assertEquals(contract.getAmountType(), contractDto.getAmountType());
        assertEquals(contract.getActive(), contractDto.getActive());
        assertEquals(contract.getAuthorizationPercent(), contractDto.getAuthorizationPercent());
        assertEquals(contract.getOrderNumber(), contractDto.getOrderNumber());
        assertEquals(contract.getRequest(), contractDto.getRequest());
    }
}