package ru.bolgov.soulbeer.model.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.dao.ProductReportRepository;
import ru.bolgov.soulbeer.model.Product;
import ru.bolgov.soulbeer.model.shift.Shift;
import ru.bolgov.soulbeer.model.shift.ShiftTemplate;
import ru.bolgov.soulbeer.service.ShiftService;

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
        for (ProductReport p : this.products){
            id.add(p.getProduct().getProductId());
        }
        return id;
    }
}
