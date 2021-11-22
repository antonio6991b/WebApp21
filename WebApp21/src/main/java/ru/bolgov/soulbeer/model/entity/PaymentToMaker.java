package ru.bolgov.soulbeer.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "payment_to_maker")
public class PaymentToMaker {
    @Id
    @Column(name = "payment_to_maker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentToMakerId;

    @Column(name = "maker_id")
    private Long makerId;

    @Column(name = "date")
    private Date date;

    @Column(name = "payment_sum")
    private BigDecimal paymentSum;

    @Column(name = "cash")
    private boolean cash;

    @Column(name = "description")
    private String description;

    public Long getPaymentToMakerId() {
        return paymentToMakerId;
    }

    public void setPaymentToMakerId(Long paymentToMakerId) {
        this.paymentToMakerId = paymentToMakerId;
    }

    public Long getMakerId() {
        return makerId;
    }

    public void setMakerId(Long makerId) {
        this.makerId = makerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
    }

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
