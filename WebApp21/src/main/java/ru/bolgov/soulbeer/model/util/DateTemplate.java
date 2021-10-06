package ru.bolgov.soulbeer.model.util;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DateTemplate {
    private List<Integer> day;
    private List<Integer> month;
    private List<Integer> year;

    public DateTemplate(){
        this.day = new ArrayList<>();
        for(int i = 1; i <= 31; i++){
            day.add(i);
        }
        this.month = new ArrayList<>();
        for(int i = 1; i <= 12; i++){
            month.add(i);
        }
        this.year = new ArrayList<>();
        for(int i = 2018; i <= 2030; i++){
            year.add(i);
        }
    }

    public List<Integer> getMonth() {
        return month;
    }

    public void setMonth(List<Integer> month) {
        this.month = month;
    }

    public List<Integer> getYear() {
        return year;
    }

    public void setYear(List<Integer> year) {
        this.year = year;
    }

    public List<Integer> getDay() {
        return day;
    }

    public void setDay(List<Integer> day) {
        this.day = day;
    }
}
