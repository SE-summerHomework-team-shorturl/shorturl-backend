package com.example.sharedentity.dao;

import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.UrlStatistic;

import java.util.List;

public interface UrlStatisticDao {
    UrlStatistic findById(Long id);
    UrlStatistic save(UrlStatistic urlStatistic );
    List<UrlStatistic> saveAll(List<UrlStatistic> urlStatistics);
}
