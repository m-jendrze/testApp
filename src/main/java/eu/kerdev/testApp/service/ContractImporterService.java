package eu.kerdev.testApp.service;

import eu.kerdev.testApp.exceptions.ImportFailed;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.tool.hbm2ddl.ImportScriptException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ContractImporterService {

    public void importContracts(FileInputStream file) throws IOException, ImportFailed {
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null)
            throw new ImportFailed();
        Iterator<Row> it = sheet.iterator();

        while(it.hasNext()) {
            iterateRow(it);
        }
    }

    private void iterateRow(Iterator<Row> it) {
        Row row = it.next();
        Iterator<Cell> cellIt = row.iterator();
        //TODO
    }
}
