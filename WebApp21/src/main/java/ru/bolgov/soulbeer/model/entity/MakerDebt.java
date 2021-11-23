package ru.bolgov.soulbeer.model.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "maker_debt")
public class MakerDebt {

    @Id
    @Column(name = "maker_debt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long makerDebtId;

    @Column(name = "maker_id")
    private Long makerId;

    @Column(name = "total_sum_coming")
    private BigDecimal totalSumComing;

    @Column(name = "total_sum_pay")
    private BigDecimal totalSumPay;

    @Column(name = "balance")
    private BigDecimal balance;

    public Long getMakerDebtId() {
        return makerDebtId;
    }

    public void setMakerDebtId(Long makerDebtId) {
        this.makerDebtId = makerDebtId;
    }

    public Long getMakerId() {
        return makerId;
    }

    public void setMakerId(Long makerId) {
        this.makerId = makerId;
    }

    public BigDecimal getTotalSumComing() {
        return totalSumComing;
    }

    public void setTotalSumComing(BigDecimal totalSumComing) {
        this.totalSumComing = totalSumComing;
    }

    public BigDecimal getTotalSumPay() {
        return totalSumPay;
    }

    public void setTotalSumPay(BigDecimal totalSumPay) {
        this.totalSumPay = totalSumPay;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}