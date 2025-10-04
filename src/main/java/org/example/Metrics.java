package org.example;

public final class Metrics {
    public long comparisons = 0;
    public long swaps = 0;
    public long arrayAccesses = 0;
    public long memoryAllocations = 0;
    public long timeNano = 0;

    public String toString() {
        return String.format("comparisons=%d, swaps=%d, arrayAccesses=%d, memoryAllocations=%d, timeMillis=%.3f",
                comparisons, swaps, arrayAccesses, memoryAllocations, timeNano / 1_000_000.0);
    }
}

