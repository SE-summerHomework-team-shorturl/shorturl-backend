package com.example.sharedentity.util;

import java.time.LocalDate;

public class RedirectRecord {
    private long shortUrlId;
    private LocalDate localDate;

    public RedirectRecord(long shortUrlId, LocalDate localDate) {
        this.shortUrlId = shortUrlId;
        this.localDate = localDate;
    }

    public long getShortUrlId() {
        return shortUrlId;
    }

    public void setShortUrlId(long shortUrlId) {
        this.shortUrlId = shortUrlId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
