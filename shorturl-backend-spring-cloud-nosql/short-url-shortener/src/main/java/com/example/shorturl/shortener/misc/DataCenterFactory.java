package com.example.shorturl.shortener.misc;

import com.example.shorturl.util.snowflake.DataCenter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataCenterFactory implements DisposableBean {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private DataCenter dataCenter;
    private String dataCenterIdString;

    public DataCenter getInstance() {
        if (dataCenter == null) {
            SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();

            dataCenterIdString = setOps.pop("data_center_id_set:short_url");
            if (dataCenterIdString == null)
                throw new RuntimeException("Data center ID set for short URLs is empty");

            long dataCenterId = Long.parseLong(dataCenterIdString);
            dataCenter = new DataCenter(dataCenterId);
        }

        return dataCenter;
    }

    @Override
    public void destroy() {
        if (dataCenterIdString != null) {
            SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();

            setOps.add("data_center_id_set:short_url", dataCenterIdString);
        }
    }
}
