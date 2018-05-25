package eu.kerdev.testApp.service;

import eu.kerdev.testApp.dao.app.ItSystemDao;
import eu.kerdev.testApp.exceptions.ImportFailed;
import eu.kerdev.testApp.model.entities.app.ItSystem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContractImporterTest {

    private final static ItSystemDao systemDaoMock = mock(ItSystemDao.class);
    private final ContractImporter objectUnderTest =
            new ContractImporter(systemDaoMock);

    @BeforeAll
    static void prepareSystemMock() {
        when(systemDaoMock.getSystemByName(anyString()))
                .thenReturn(new ItSystem());
    }
    @Test
    void importContracts() throws Exception, ImportFailed {
        FileInputStream excelFile =
                new FileInputStream(new File(getClass()
                        .getClassLoader()
                        .getResource("umowy_2016.xlsx")
                        .getFile()
                ));
        objectUnderTest.importContracts(excelFile);
    }
}