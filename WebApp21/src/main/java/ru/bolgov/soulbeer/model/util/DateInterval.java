package ru.bolgov.soulbeer.model.util;

import java.sql.Date;

public class DateInterval {
    private ShowDate from;
    private ShowDate to;

    public ShowDate getFrom() {
        return from;
    }

    public void setFrom(ShowDate from) {
        this.from = from;
    }

    public ShowDate getTo() {
        return to;
    }

    public void setTo(ShowDate to) {
        this.to = to;
    }
}
