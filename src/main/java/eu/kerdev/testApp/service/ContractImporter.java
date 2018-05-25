package eu.kerdev.testApp.service;

import eu.kerdev.testApp.dao.app.ItSystemDao;
import eu.kerdev.testApp.exceptions.ImportFailed;
import eu.kerdev.testApp.exceptions.InvalidRow;
import eu.kerdev.testApp.model.AmountPeriod;
import eu.kerdev.testApp.model.AmountType;
import eu.kerdev.testApp.model.entities.app.Contract;
import eu.kerdev.testApp.model.entities.app.ItSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ContractImporter {

    private final ItSystemDao itSystemDao;

    @Autowired
    public ContractImporter(ItSystemDao itSystemDao) {
        this.itSystemDao = itSystemDao;
    }

    public List<Contract> importContracts(InputStream file) throws IOException, ImportFailed {
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        int invalidRows = 0;
        if (sheet == null)
            throw new ImportFailed();
        Iterator<Row> it = sheet.iterator();
        List<ContractColumnHeader> header = Collections.emptyList();
        List<Contract> contractsToImport = new ArrayList<>();
        if (it.hasNext()) {
            header = getHeaderFromRow(it.next());
        }
        while(it.hasNext()) {
            try {
                contractsToImport.add(rowToContract(it.next(), header));
            } catch (InvalidRow invalidRow) {
                invalidRows++;
            }
        }
        return contractsToImport;
    }

    private Contract rowToContract(Row row, List<ContractColumnHeader> headerRow) throws InvalidRow {
        final Iterator<Cell> cellIt = row.iterator();
        final Iterator<ContractColumnHeader> headerIt = headerRow.iterator();
        final Contract contract = new Contract();
        while(cellIt.hasNext() && headerIt.hasNext()) {
            Cell cell = cellIt.next();
            ContractColumnHeader header = headerIt.next();
            switch (header) {
                case ACTIVE:
                    contract.setActive(getBoolean(cell));
                    break;
                case SYSTEM:
                    contract.setSystem(getSystem(cell));
                    break;
                case AMOUNT:
                    contract.setAmount(getDecimal(cell));
                    break;
                case REQUEST:
                    contract.setRequest(getString(cell));
                    break;
                case AMOUNT_PERIOD:
                    contract.setAmountPeriod(getAmountPeriod(cell));
                    break;
                case AMOUNT_TYPE:
                    contract.setAmountType(getAmountType(cell));
                    break;
                case AUTHORIZATION_PERCENT:
                    contract.setAuthorizationPercent(getInteger(cell));
                    break;
                case FROM_DATE:
                    contract.setFromDate(getDate(cell));
                    break;
                case TO_DATE:
                    contract.setToDate(getDate(cell));
                    break;
                case ORDER_NUMBER:
                    contract.setOrderNumber(getString(cell));
                    break;
            }
        }
        if (cellIt.hasNext() || headerIt.hasNext()) {
            throw new InvalidRow();
        }
        return contract;
    }

    private Boolean getBoolean(Cell cell) throws InvalidRow {
        if (cell.getCellTypeEnum().equals(CellType.BOOLEAN)) {
            return cell.getBooleanCellValue();
        }
        if (cell.getCellTypeEnum().equals(CellType.STRING)) {
            final String cellValue = cell.getStringCellValue();
            if ("true".equals(cellValue))
                return true;
            if ("false".equals(cellValue))
                return false;
        }
        throw new InvalidRow();
    }

    private ItSystem getSystem(Cell cell) throws InvalidRow {
        switch(cell.getCellTypeEnum()) {
            case STRING:
                final String name = cell.getStringCellValue();
                final ItSystem system = itSystemDao.getSystemByName(name);
                return system;
            case BLANK:
                return null;
            default:
                throw new InvalidRow();
        }
    }

    private BigDecimal getDecimal(Cell cell) throws InvalidRow {
        switch(cell.getCellTypeEnum()) {
            case NUMERIC:
                return new BigDecimal(cell.getNumericCellValue());
            case STRING:
                return new BigDecimal(cell.getStringCellValue()
                        .replaceAll("[^\\d\\.]", "")
                );
            case BLANK:
                return null;
            default:
                throw new InvalidRow();
        }
    }

    private String getString(Cell cell) throws InvalidRow {
        switch(cell.getCellTypeEnum()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BLANK:
                return null;
            default:
                throw new InvalidRow();
        }
    }

    private AmountType getAmountType(Cell cell) throws InvalidRow {
        switch(cell.getCellTypeEnum()) {
            case STRING:
                final String value = cell.getStringCellValue();
                for (AmountType type : AmountType.values()) {
                    if (type.toString().equals(value))
                        return type;
                }
            case BLANK:
                return null;
            default:
                throw new InvalidRow();
        }
    }

    private AmountPeriod getAmountPeriod(Cell cell) throws InvalidRow {
        switch(cell.getCellTypeEnum()) {
            case STRING:
                final String value = cell.getStringCellValue();
                for (AmountPeriod type : AmountPeriod.values()) {
                    if (type.toString().equals(value))
                        return type;
                }
            case BLANK:
                return null;
            default:
                throw new InvalidRow();
        }
    }

    private Integer getInteger(Cell cell) throws InvalidRow {
        switch(cell.getCellTypeEnum()) {
            case STRING:
                return Integer.parseInt(cell.getStringCellValue());
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case BLANK:
                return 0;
            default:
                throw new InvalidRow();
        }
    }

    private Date getDate(Cell cell) throws InvalidRow {
        switch(cell.getCellTypeEnum()) {
            case NUMERIC:
                return cell.getDateCellValue();
            case STRING:
                final String value = cell.getStringCellValue();
                if (value.matches("\\d\\d\\d\\d-[0-1]\\d-[0-3]\\d")) {
                    try {
                        return new SimpleDateFormat("yyyy-MM-dd").parse(value);
                    } catch (ParseException e) {
                        throw new InvalidRow();
                    }
                }
            case BLANK:
                return null;
            default:
                throw new InvalidRow();
        }
    }

    /**
     * Gets the list with order of columns
     * @param row header row from xlsx file
     * @return list of column types for contract
     * @throws ImportFailed if file had too much or too little rows
     */
    private List<ContractColumnHeader> getHeaderFromRow(Row row) throws ImportFailed {
        Iterator<Cell> it = row.iterator();
        List<ContractColumnHeader> headers = new ArrayList<>();
        int columnsAmount = 0;
        while(it.hasNext()) {
            Cell cell = it.next();
            columnsAmount++;
            ContractColumnHeader headerCell = ContractColumnHeader.getFromHeader(
                    cell.getCellTypeEnum().equals(CellType.STRING) ?
                            cell.getStringCellValue() : ""
            );
            if (headerCell != null && !headers.contains(headerCell)) {
                headers.add(headerCell);
            }
        }
        if (
                columnsAmount != ContractColumnHeader.values().length ||
                columnsAmount != headers.size()
        ) {
            throw new ImportFailed();
        }
        return headers;
    }
}
