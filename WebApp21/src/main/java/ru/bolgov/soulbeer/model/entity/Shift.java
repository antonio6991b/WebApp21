package ru.bolgov.soulbeer.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "shift")
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id")
    private Long shiftId;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "shift_begin")
    private Date shiftBegin;

    @Column(name = "shift_ends")
    private Date shiftEnds;

    @Column(name = "cash_begin")
    private BigDecimal cashBegin;

    @Column(name = "cash_end")
    private BigDecimal cashEnd;

    public Shift(){}

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getShiftBegin() {
        return shiftBegin;
    }

    public void setShiftBegin(Date shiftBegin) {
        this.shiftBegin = shiftBegin;
    }

    public Date getShiftEnds() {
        return shiftEnds;
    }

    public void setShiftEnds(Date shiftEnds) {
        this.shiftEnds = shiftEnds;
    }

    public BigDecimal getCashBegin() {
        return cashBegin;
    }

    public void setCashBegin(BigDecimal cashBegin) {
        this.cashBegin = cashBegin;
    }

    public BigDecimal getCashEnd() {
        return cashEnd;
    }

    public void setCashEnd(BigDecimal cashEnd) {
        this.cashEnd = cashEnd;
    }
}
