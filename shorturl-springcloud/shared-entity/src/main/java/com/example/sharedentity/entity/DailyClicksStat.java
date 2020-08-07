package com.example.sharedentity.entity;

import java.util.Date;

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
}
