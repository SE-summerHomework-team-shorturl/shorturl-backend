package com.example.shorturl.util.misc;

public class IdWorker {
    static private final long WORKER_ID_BITS = 5L;
    static private final long DATA_CENTER_ID_BITS = 5L;
    static private final long SEQUENCE_BITS = 64L - WORKER_ID_BITS - DATA_CENTER_ID_BITS;

    static private final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    static private final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    static private final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    static private final long DATA_CENTER_ID_SHIFT = WORKER_ID_BITS;
    static private final long SEQUENCE_SHIFT = WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    private final long dataCenterId;
    private final long workerId;
    private long sequence;

    public IdWorker(long dataCenterId, long workerId, long sequence) {
        if (workerId > MAX_WORKER_ID || workerId < 0)
            throw new IllegalArgumentException(String.format(
                    "Worker ID can't be greater than %d or less than 0", MAX_WORKER_ID));
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0)
            throw new IllegalArgumentException(String.format(
                    "Data center ID can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        if (sequence > MAX_SEQUENCE || sequence < 0)
            throw new IllegalArgumentException(String.format(
                    "Sequence can't be greater than %d or less than 0", MAX_SEQUENCE));

        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
        this.sequence = sequence;
    }

    synchronized public long nextId() {
        if ((sequence + 1) > MAX_SEQUENCE)
            throw new RuntimeException("Overflow");

        return ((sequence++) << SEQUENCE_SHIFT) |
                (dataCenterId << DATA_CENTER_ID_SHIFT) |
                workerId;
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public long getSequence() {
        return sequence;
    }
}
