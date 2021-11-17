package ru.bolgov.soulbeer.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product_report")
public class ProductReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_report_id")
    private Long productReportId;


    @Column(name = "shift_id")
    private Long shiftId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "price_buy")
    private BigDecimal priceBuy;

    @Column(name = "price_sell")
    private BigDecimal priceSell;

    @Column(name = "remains_last")
    private BigDecimal remainsLast;

    @Column(name = "coming")
    private BigDecimal coming;

    @Column(name = "remains_current")
    private BigDecimal remainsCurrent;

    @Column(name = "sum_current")
    private BigDecimal sumCurrent;

    @Column(name = "gross_profit")
    private BigDecimal grossProfit;

    @Column(name = "notebook_value")
    private BigDecimal notebookValue;

    @Column(name = "balance")
    private BigDecimal balance;

    public ProductReport(){}

    public Long getProductReportId() {
        return productReportId;
    }

    public void setProductReportId(Long productReportId) {
        this.productReportId = productReportId;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(BigDecimal priceBuy) {
        this.priceBuy = priceBuy;
    }

    public BigDecimal getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(BigDecimal priceSell) {
        this.priceSell = priceSell;
    }

    public BigDecimal getRemainsLast() {
        return remainsLast;
    }

    public void setRemainsLast(BigDecimal remainsLast) {
        this.remainsLast = remainsLast;
    }

    public BigDecimal getComing() {
        return coming;
    }

    public void setComing(BigDecimal coming) {
        this.coming = coming;
    }

    public BigDecimal getRemainsCurrent() {
        return remainsCurrent;
    }

    public void setRemainsCurrent(BigDecimal remainsCurrent) {
        this.remainsCurrent = remainsCurrent;
    }

    public BigDecimal getSumCurrent() {
        return sumCurrent;
    }

    public void setSumCurrent(BigDecimal sumCurrent) {
        this.sumCurrent = sumCurrent;
    }

    public BigDecimal getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(BigDecimal grossProfit) {
        this.grossProfit = grossProfit;
    }

    public BigDecimal getNotebookValue() {
        return notebookValue;
    }

    public void setNotebookValue(BigDecimal notebookValue) {
        this.notebookValue = notebookValue;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
