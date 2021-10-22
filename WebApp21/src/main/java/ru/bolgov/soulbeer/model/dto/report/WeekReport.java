package ru.bolgov.soulbeer.model.dto.report;

import ru.bolgov.soulbeer.model.entity.ProductReport;
import ru.bolgov.soulbeer.model.dto.shift.ShiftTemplate;

import java.util.ArrayList;
import java.util.List;


public class WeekReport {


    public void setShiftTemplate(ShiftTemplate shiftTemplate) {
        this.shiftTemplate = shiftTemplate;
    }

    private ShiftTemplate shiftTemplate;
    private List<ProductReport> products;

    public WeekReport(){

    }


    public List<ProductReport> getProducts() {
        return products;
    }

    public void setProducts(List<ProductReport> products) {
        this.products = products;
    }

    public ShiftTemplate getShiftTemplate() {
        return shiftTemplate;
    }

    public void fillData(ShiftTemplate shiftTemplate) {
        this.shiftTemplate = shiftTemplate;

    }

    public List<Long> getProductIds(){
        List<Long> id = new ArrayList<>();
//        for (ProductReport p : this.products){
//            id.add(p.getProduct().getProductId());
//        }
        return id;
    }
}
