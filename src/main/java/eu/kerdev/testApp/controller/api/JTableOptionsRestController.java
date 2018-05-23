package eu.kerdev.testApp.controller.api;

import eu.kerdev.testApp.model.AmountPeriod;
import eu.kerdev.testApp.model.AmountType;
import eu.kerdev.testApp.model.dto.JTableResponse;
import eu.kerdev.testApp.model.dto.JTableResponseBuilder;
import eu.kerdev.testApp.model.dto.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the REST api exposing data types of domain model.
 * Controller returns response objects containing list of options
 * possible for each enumerable data type in application.
 * Returns objects wrapped in a structure for JTable.
 * @author Michal Jendrzejek
 */
@RestController
@RequestMapping("api/options/")
public class JTableOptionsRestController {

    private final JTableResponseBuilder responseBuilder;

    @Autowired
    public JTableOptionsRestController(
            JTableResponseBuilder responseBuilder
    ) {
        this.responseBuilder = responseBuilder;
    }

    @RequestMapping(
            value = "amountPeriod",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JTableResponse amountPeriodOptions() {
        List<Option> optionList = new ArrayList<>();
        optionList.add(new Option(
                AmountPeriod.DAY.toString(),
                AmountPeriod.DAY.toString()
        ));
        optionList.add(new Option(
                AmountPeriod.WEEK.toString(),
                AmountPeriod.WEEK.toString()
        ));
        optionList.add(new Option(
                AmountPeriod.MONTH.toString(),
                AmountPeriod.MONTH.toString()
        ));
        optionList.add(new Option(
                AmountPeriod.YEAR.toString(),
                AmountPeriod.YEAR.toString()
        ));
        return responseBuilder.prepareOptionsResponse(optionList);
    }

    @RequestMapping(
            value = "amountType",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JTableResponse amountTypeOptions() {
        List<Option> optionList = new ArrayList<>();
        optionList.add(new Option(
                AmountType.NET.toString(),
                AmountType.NET.toString()
        ));
        optionList.add(new Option(
                AmountType.BRU.toString(),
                AmountType.BRU.toString()
        ));
        return responseBuilder.prepareOptionsResponse(optionList);
    }
}
