package ru.bolgov.soulbeer.model.dto.util;

import java.sql.Date;

public class ShowDate {
    private int day;
    private int month;
    private int year;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ShowDate(){}

    public ShowDate(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public ShowDate(Date date){
        String stringDate = date.toString();
        this.day = Integer.valueOf(stringDate.substring(8, 10));
        this.month = Integer.valueOf(stringDate.substring(5, 7));
        this.year = Integer.valueOf(stringDate.substring(0, 4));
    }


    public Date createDate(){
        String tmp = year + "-"
                + month + "-"
                + day;
        return Date.valueOf(tmp);
    }

    public String show(){
        return this.day + "." + this.month + "." + this.year;
    }


}
