package eu.kerdev.testApp.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO used to wrap response messages for JTable
 * @author Michal Jendrzejek
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JTableResponse<T> {
    private final ResultStatus result;
    private final List<T> records;
    private final T record;
    private final String message;
    private final List<Option> options;

    public JTableResponse(
            ResultStatus result,
            List<T> records,
            T record,
            String message,
            List<Option> options
    ) {
        this.result = result;
        this.records = records;
        this.record = record;
        this.message = message;
        this.options = options;
    }

    @JsonProperty("Result")
    public ResultStatus getResult() {
        return result;
    }

    @JsonProperty("Records")
    public List<T> getRecords() {
        return records;
    }

    @JsonProperty("Record")
    public T getRecord() {
        return record;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("Options")
    public List<Option> getOptions() {
        return options;
    }
}
