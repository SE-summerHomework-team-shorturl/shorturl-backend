package com.example.sharedentity.dao;

import com.example.sharedentity.entity.UrlStatistic;

public interface UrlStatisticDao {
    UrlStatistic findById(Long id);
    UrlStatistic save(UrlStatistic urlStatistic );
}
