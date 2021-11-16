package ru.bolgov.soulbeer.model.dto.report.rest;

import java.math.BigDecimal;

public class ReportValue {
    private BigDecimal priceBuy;
    private BigDecimal priceSell;
    private BigDecimal remainsLast;

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
}
