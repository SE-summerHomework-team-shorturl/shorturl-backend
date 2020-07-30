package com.example.sharedentity.dao;

import com.example.sharedentity.entity.UrlStatistic;
import com.example.sharedentity.repository.UrlStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames={"statistics"})
public class UrlStatisticDaoImpl implements UrlStatisticDao{
    @Autowired private UrlStatisticRepository statisticRepo;
    @Override
    @Cacheable(key="#id")
    public UrlStatistic findById(Long id) {
        return statisticRepo.findById(id).orElse(null);
    }

    @Override
    public UrlStatistic save(UrlStatistic urlStatistic) {
        return statisticRepo.save(urlStatistic);
    }
}
