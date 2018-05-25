package eu.kerdev.testApp.service;

public enum ContractColumnHeader {
    SYSTEM("system"),
    REQUEST("request"),
    ORDER_NUMBER("order_number"),
    FROM_DATE("from_date"),
    TO_DATE("to_date"),
    AMOUNT("amount"),
    AMOUNT_TYPE("amount_type"),
    AMOUNT_PERIOD("amount_period"),
    AUTHORIZATION_PERCENT("authorization_percent"),
    ACTIVE("active"),
    ;

    private final String header;

    ContractColumnHeader(String header) {
        this.header = header;
    }

    public static ContractColumnHeader getFromHeader(String header) {
        for (ContractColumnHeader column : ContractColumnHeader.values()) {
            if (column.header.equals(header))
                return column;
        }
        return null;
    }
}
