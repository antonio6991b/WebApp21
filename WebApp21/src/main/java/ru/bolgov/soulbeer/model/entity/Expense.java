package ru.bolgov.soulbeer.model.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @Column(name = "expense_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @Column(name = "shift_id")
    private Long shiftId;

    @Column(name = "expense_name")
    private String expenseName;

    @Column(name = "expense_type")
    private String expenseType;

    @Column(name = "expense_sum")
    private BigDecimal expenseSum;

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public BigDecimal getExpenseSum() {
        return expenseSum;
    }

    public void setExpenseSum(BigDecimal expenseSum) {
        this.expenseSum = expenseSum;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }
}
