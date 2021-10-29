package ru.bolgov.soulbeer.model.dto.report;

import ru.bolgov.soulbeer.model.entity.ProductReport;
import ru.bolgov.soulbeer.model.dto.shift.ShiftTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class WeekReport {


    private ShiftTemplate shiftTemplate;

    private List<ProductReportDto> products;
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

}
