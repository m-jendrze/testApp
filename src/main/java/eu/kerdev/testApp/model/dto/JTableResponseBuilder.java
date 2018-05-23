package eu.kerdev.testApp.model.dto;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Builder preparing response messages used by JTable
 * @see eu.kerdev.testApp.model.dto.JTableResponse
 * @author Michal Jendrzejek
 */
@Component
public class JTableResponseBuilder<T> {

    public JTableResponse<T> prepareCreateResponse(final T record) {
        return new JTableResponse<>(
                ResultStatus.OK,
                null,
                record,
                null,
                null
        );
    }

    public JTableResponse<T> prepareReadResponse(final List<T> records) {
        return new JTableResponse<T>(
                ResultStatus.OK,
                records != null ? records : Collections.EMPTY_LIST,
                null,
                null,
                null
        );
    }

    public JTableResponse<T> prepareUpdateResponse() {
        return new JTableResponse<>(
                ResultStatus.OK,
                null,
                null,
                null,
                null
        );
    }

    public JTableResponse<T> prepareDeleteResponse() {
        return new JTableResponse<>(
                ResultStatus.OK,
                null,
                null,
                null,
                null
        );
    }

    public JTableResponse<T> prepareErrorResponse(String message) {
        return new JTableResponse<>(
                ResultStatus.ERROR,
                null,
                null,
                message,
                null
        );
    }

    public JTableResponse prepareOptionsResponse(final List<Option> options) {
        return new JTableResponse<>(
                ResultStatus.OK,
                null,
                null,
                null,
                options
        );
    }
}
