package eu.kerdev.testApp.model.dto;

import eu.kerdev.testApp.model.AmountPeriod;
import eu.kerdev.testApp.model.AmountType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO used to expose Contract data by controllers
 * @see eu.kerdev.testApp.model.entities.app.Contract
 * @author Michal Jendrzejek
 */
public class ContractDto {

    private Long id;
    private Long systemId;
    private String request;
    private String orderNumber;
    private String fromDate;
    private String toDate;
    private BigDecimal amount;
    private AmountType amountType;
    private AmountPeriod amountPeriod;
    private Integer authorizationPercent;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public AmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }

    public AmountPeriod getAmountPeriod() {
        return amountPeriod;
    }

    public void setAmountPeriod(AmountPeriod amountPeriod) {
        this.amountPeriod = amountPeriod;
    }

    public Integer getAuthorizationPercent() {
        return authorizationPercent;
    }

    public void setAuthorizationPercent(Integer authorizationPercent) {
        this.authorizationPercent = authorizationPercent;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
