package com.example.shorturl.user.misc;

import com.example.shorturl.util.misc.DataCenter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@SuppressWarnings("all")
public class DataCenterFactory implements DisposableBean {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private DataCenter dataCenter;

    public DataCenter getInstance() {
        if (dataCenter == null) {
            SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
            ListOperations<String, String> listOps = stringRedisTemplate.opsForList();

            synchronized (this) {
                if (dataCenter == null) {
                    String dataCenterIdString = setOps.pop("user_data_center_id_set");
                    if (dataCenterIdString == null)
                        throw new RuntimeException("Data center ID set for users is empty");
                    long dataCenterId = Long.parseLong(dataCenterIdString);

                    List<String> sequenceStrings = listOps.range(
                            "user_data_center_id_to_sequences:" + dataCenterId, 0, -1);
                    List<Long> sequences = new ArrayList<>();
                    for (String sequenceString : sequenceStrings)
                        sequences.add(Long.parseLong(sequenceString));

                    dataCenter = new DataCenter(dataCenterId, sequences);
                }
            }
        }

        return dataCenter;
    }

    @Override
    public void destroy() throws Exception {
        if (dataCenter != null) {
            SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
            ListOperations<String, String> listOps = stringRedisTemplate.opsForList();

            long dataCenterId = dataCenter.getDataCenterId();
            List<Long> sequences = dataCenter.getSequences();

            for (int i = 0; i < DataCenter.TOTAL_ID_WORKERS; ++i)
                listOps.set("user_data_center_id_to_sequences:" + dataCenterId,
                        i, sequences.get(i).toString());

            setOps.add("user_data_center_id_set", Long.toString(dataCenterId));
        }
    }
}