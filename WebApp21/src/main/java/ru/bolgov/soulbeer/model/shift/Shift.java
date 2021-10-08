package ru.bolgov.soulbeer.model.shift;

import ru.bolgov.soulbeer.model.Seller;
import ru.bolgov.soulbeer.model.report.ProductReport;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "shift")
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shiftId")
    private Long shiftId;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sellerId")
    private Seller shiftSeller;

    @Column(name = "shiftBegin")
    private Date shiftBegin;

    @Column(name = "shiftEnds")
    private Date shiftEnds;

    @Column(name = "cashBegin")
    private BigDecimal cashBegin;

    @Column(name = "cashEnd")
    private BigDecimal cashEnd;

    @OneToMany(mappedBy = "productReportId", fetch = FetchType.EAGER)
    private List<ProductReport> productReports;

    public List<ProductReport> getProductReports() {
        return productReports;
    }

    public void setProductReports(List<ProductReport> productReports) {
        this.productReports = productReports;
    }

    public Shift(){}

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Seller getShiftSeller() {
        return shiftSeller;
    }

    public void setShiftSeller(Seller shiftSeller) {
        this.shiftSeller = shiftSeller;
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
