package ru.bolgov.soulbeer.model.report;

import ru.bolgov.soulbeer.model.Product;
import ru.bolgov.soulbeer.model.Shop;
import ru.bolgov.soulbeer.model.shift.Shift;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productReport")
public class ProductReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productReportId")
    private Long productReportId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shiftId")
    private Shift shift;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "productId")
    private Product product;

    @Column(name = "priceBuy")
    private BigDecimal priceBuy;

    @Column(name = "priceSell")
    private BigDecimal priceSell;

    @Column(name = "remainsLast")
    private BigDecimal remainsLast;

    @Column(name = "coming")
    private int coming;

    @Column(name = "remainsCurrent")
    private BigDecimal remainsCurrent;

    @Column(name = "sumCurrent")
    private BigDecimal sumCurrent;

    @Column(name = "grossProfit")
    private BigDecimal grossProfit;

    @Column(name = "notebookValue")
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

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public int getComing() {
        return coming;
    }

    public void setComing(int coming) {
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
