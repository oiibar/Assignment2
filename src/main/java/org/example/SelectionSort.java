package org.example;

public final class SelectionSort {
    public static Metrics sort(int[] a) {
        if (a == null) throw new IllegalArgumentException("Input array is null");
        Metrics m = new Metrics();
        long start = System.nanoTime();

        int n = a.length;
        if (n == 0 || n == 1) {
            m.timeNano = System.nanoTime() - start;
            return m;
        }

        boolean sorted = false;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            m.arrayAccesses++;

            // Find min index
            for (int j = i + 1; j < n; j++) {
                m.arrayAccesses += 2;
                m.comparisons++;
                if (a[j] < a[minIdx]) {
                    minIdx = j;
                }
            }

            // Early termination if already sorted
            if (minIdx == i) {
                sorted = true;
                for (int k = i; k < n - 1; k++) {
                    m.arrayAccesses += 2;
                    m.comparisons++;
                    if (a[k] > a[k + 1]) {
                        sorted = false;
                        break;
                    }
                }
                if (sorted) break;
            }

            // Swap only if needed
            if (minIdx != i) {
                int tmp = a[i];
                m.arrayAccesses++;

                a[i] = a[minIdx];
                m.arrayAccesses++;

                a[minIdx] = tmp;
                m.arrayAccesses++;
                m.swaps++;
            }
        }

        m.timeNano = System.nanoTime() - start;
        return m;
    }
}
