package ru.bolgov.soulbeer.model.dto.shift;

import ru.bolgov.soulbeer.model.entity.Shift;
import ru.bolgov.soulbeer.model.dto.util.ShowDate;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ShiftTemplate {
    private Shift shift;
    private ShowDate dateBegin;
    private ShowDate dateEnd;
    private String shopName;
    private Long shopPhone;
    private String sellerName;

    public ShiftTemplate(){  //Set current monday to shift begins and current sunday to shift ends
        LocalDate today = LocalDate.now();
        for(int i = 0; i < 7; i++){
            if(today.getDayOfWeek().equals(DayOfWeek.MONDAY)){
                break;
            }
            today = today.minusDays(1);
        }
        this.dateBegin = new ShowDate(today.getDayOfMonth(), today.getMonth().getValue(), today.getYear());
        today = today.plusDays(6);
        this.dateEnd = new ShowDate(today.getDayOfMonth(), today.getMonth().getValue(), today.getYear());

    }

    public ShiftTemplate(Shift shift){
        this.shift = shift;
        this.dateBegin = new ShowDate(shift.getShiftBegin());
        this.dateEnd = new ShowDate(shift.getShiftEnds());
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public ShowDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(ShowDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public ShowDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(ShowDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Shift createShift(){
        this.shift.setShiftBegin(this.dateBegin.createDate());
        this.shift.setShiftEnds(this.dateEnd.createDate());
        return this.shift;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Long getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(Long shopPhone) {
        this.shopPhone = shopPhone;
    }
}
