package com.example.shorturl.util.snowflake;

public class IdWorker {
    static private final long WORKER_ID_BITS = 5L;
    static private final long DATA_CENTER_ID_BITS = 5L;
    static private final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    static private final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    static private final long SEQUENCE_BITS = 12L;

    static private final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    static private final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    static private final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
    static private final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    static private final long EPOCH = 1595525258000L;

    private final long workerId;
    private final long dataCenterId;
    private long lastTimestamp;
    private long sequence;

    public IdWorker(long workerId, long dataCenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0)
            throw new IllegalArgumentException(String.format(
                    "Worker ID can't be greater than %d or less than 0", MAX_WORKER_ID));
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0)
            throw new IllegalArgumentException(String.format(
                    "Data center ID can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;

        lastTimestamp = -1L;
        sequence = 0L;
    }

    synchronized public long nextId() {
        long timestamp = timeGen();

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0)
                timestamp = tilNextMillis(lastTimestamp);
        } else
            sequence = 0;

        if (timestamp < lastTimestamp)
            throw new RuntimeException("Clock moved backwards");

        lastTimestamp = timestamp;

        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT) |
                (dataCenterId << DATA_CENTER_ID_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp)
            timestamp = timeGen();
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
