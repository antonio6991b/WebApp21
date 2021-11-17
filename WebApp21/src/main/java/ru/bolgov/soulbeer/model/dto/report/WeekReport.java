package ru.bolgov.soulbeer.model.dto.report;

import ru.bolgov.soulbeer.model.entity.Expense;
import ru.bolgov.soulbeer.model.entity.ProductReport;
import ru.bolgov.soulbeer.model.dto.shift.ShiftTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class WeekReport {


    private ShiftTemplate shiftTemplate;

    private List<ProductReportDto> products;
    private List<Expense> writeOffList;
    private List<Expense> cashDeskList;
    public WeekReport(){

    }


    public void setShiftTemplate(ShiftTemplate shiftTemplate) {
        this.shiftTemplate = shiftTemplate;
    }

    public List<ProductReportDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductReportDto> products) {
        this.products = products;
    }

    public ShiftTemplate getShiftTemplate() {
        return shiftTemplate;
    }

    public void fillData(ShiftTemplate shiftTemplate) {
        this.shiftTemplate = shiftTemplate;
    }

    public BigDecimal totalSumCurrent(){
        List<BigDecimal> currentSums = this.getProducts().stream()
                .map(x -> x.getProductReport().getSumCurrent())
                .collect(Collectors.toList());
        BigDecimal totalSum = new BigDecimal(0);
        for(BigDecimal sum:currentSums){
            totalSum = totalSum.add(sum);
        }
        return totalSum;
    }

    public BigDecimal totalGrossProfit(){
        List<BigDecimal> currentSums = this.getProducts().stream()
                .map(x -> x.getProductReport().getGrossProfit())
                .collect(Collectors.toList());
        BigDecimal totalSum = new BigDecimal(0);
        for(BigDecimal sum:currentSums){
            totalSum = totalSum.add(sum);
        }
        return totalSum;
    }

    public BigDecimal totalBalance(){
        List<BigDecimal> currentSums = this.getProducts().stream()
                .map(x -> x.getProductReport().getBalance())
                .collect(Collectors.toList());
        BigDecimal totalSum = new BigDecimal(0);
        for(BigDecimal sum:currentSums){
            totalSum = totalSum.add(sum);
        }
        return totalSum;
    }

    public List<Expense> getWriteOffList() {
        return writeOffList;
    }

    public void setWriteOffList(List<Expense> writeOffList) {
        this.writeOffList = writeOffList;
    }

    public List<Expense> getCashDeskList() {
        return cashDeskList;
    }

    public void setCashDeskList(List<Expense> cashDeskList) {
        this.cashDeskList = cashDeskList;
    }
}
