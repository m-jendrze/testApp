package eu.kerdev.testApp.model.entities.app;

import eu.kerdev.testApp.model.AmountPeriod;
import eu.kerdev.testApp.model.AmountType;
import eu.kerdev.testApp.model.entities.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Domain object used to represent contract in application
 * Saved in database under app.contract
 * @author Michal Jendrzejek
 */
@Entity
@Table(name = "contract", schema = "app")
public class Contract extends BaseEntity<Long> {

    private Long id;
    private ItSystem system;
    private String request;
    private String orderNumber;
    private Date fromDate;
    private Date toDate;
    private BigDecimal amount;
    private AmountType amountType;
    private AmountPeriod amountPeriod;
    private Integer authorizationPercent;
    private Boolean active = Boolean.TRUE;

    @Override
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_seq")
    @SequenceGenerator(
            name = "contract_seq",
            sequenceName = "contract_seq",
            schema = "app",
            allocationSize = 1
    )
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "it_system_id")
    public ItSystem getSystem() {
        return system;
    }

    public void setSystem(ItSystem system) {
        this.system = system;
    }

    @Column(name = "request", length = 8)
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Column(name = "order_number", length = 16)
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "amount_type")
    @Enumerated(EnumType.STRING)
    public AmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }

    @Column(name = "amount_period")
    @Enumerated(EnumType.STRING)
    public AmountPeriod getAmountPeriod() {
        return amountPeriod;
    }

    public void setAmountPeriod(AmountPeriod amountPeriod) {
        this.amountPeriod = amountPeriod;
    }

    @Column(name = "authorization_percent")
    public Integer getAuthorizationPercent() {
        return authorizationPercent;
    }

    public void setAuthorizationPercent(Integer authorizationPercent) {
        this.authorizationPercent = authorizationPercent;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return id + ", " +
                (system != null ? system.getName() : "null") + ", " +
                request + ", " +
                orderNumber + ", " +
                (fromDate != null ? fromDate.toString() : "null") + ", " +
                (toDate != null ? toDate.toString() : "null") + ", " +
                amount + ", " +
                amountType + ", " +
                amountPeriod + ", " +
                authorizationPercent + ", " +
                active + ", ";
    }
}
