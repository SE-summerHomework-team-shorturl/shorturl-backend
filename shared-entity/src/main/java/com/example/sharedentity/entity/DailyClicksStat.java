package com.example.sharedentity.entity;

import java.util.Date;
import java.util.Objects;

public class DailyClicksStat {
    private Date date;
    private Long clicks;

    public DailyClicksStat(Date date) {
        this.date = date;
        clicks = 0L;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DailyClicksStat that = (DailyClicksStat) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(clicks, that.clicks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, clicks);
    }
}
