package com.example.shorturl.util.snowflake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataCenter {
    static private final long DEFAULT_TOTAL_ID_WORKERS = 32L;

    private final List<IdWorker> idWorkers = new ArrayList<>();
    private final Random random = new Random();

    public DataCenter(long dataCenterId) {
        this(dataCenterId, DEFAULT_TOTAL_ID_WORKERS);
    }

    public DataCenter(long dataCenterId, long totalIdWorkers) {
        if (totalIdWorkers <= 0)
            throw new IllegalArgumentException("Total ID workers should be positive");

        for (long i = 0; i < totalIdWorkers; ++i)
            idWorkers.add(new IdWorker(i, dataCenterId));
    }

    public IdWorker pickAnIdWorker() {
        return idWorkers.get(random.nextInt(idWorkers.size()));
    }
}
