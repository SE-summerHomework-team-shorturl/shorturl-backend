package com.example.shorturl.util.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataCenter {
    static public final int TOTAL_ID_WORKERS = 32;

    private final List<IdWorker> idWorkers = new ArrayList<>();
    private final Random random = new Random();

    private final long dataCenterId;

    public DataCenter(long dataCenterId, List<Long> sequences) {
        if (sequences.size() != TOTAL_ID_WORKERS)
            throw new IllegalArgumentException(String.format("Total id workers should be %d", TOTAL_ID_WORKERS));

        this.dataCenterId = dataCenterId;

        for (int i = 0; i < TOTAL_ID_WORKERS; ++i)
            idWorkers.add(new IdWorker(dataCenterId, i, sequences.get(i)));
    }

    public IdWorker pickAnIdWorker() {
        return idWorkers.get(random.nextInt(TOTAL_ID_WORKERS));
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public List<Long> getSequences() {
        List<Long> sequences = new ArrayList<>();
        for (IdWorker idWorker : idWorkers)
            sequences.add(idWorker.getSequence());
        return sequences;
    }
}
