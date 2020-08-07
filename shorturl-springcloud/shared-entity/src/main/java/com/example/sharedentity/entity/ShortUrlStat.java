package com.example.sharedentity.entity;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Document(value = "short_url_stats")
@AccessType(AccessType.Type.FIELD)
public class ShortUrlStat {
    @Id
    private Long id;
    private Long totalClicks;
    private List<DailyClicksStat> dailyClicksStats;

    public ShortUrlStat(Long id) {
        this.id = id;
        totalClicks = 0L;
        dailyClicksStats = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalClicks() {
        return totalClicks;
    }

    public void setTotalClicks(Long totalClicks) {
        this.totalClicks = totalClicks;
    }

    public List<DailyClicksStat> getDailyClicksStats() {
        return dailyClicksStats;
    }

    public void setDailyClicksStats(List<DailyClicksStat> dailyClicksStats) {
        this.dailyClicksStats = dailyClicksStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ShortUrlStat that = (ShortUrlStat) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
