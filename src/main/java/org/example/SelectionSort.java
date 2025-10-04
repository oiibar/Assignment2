package org.example;

public final class SelectionSort {
    public static void sort(int[] a) {
        if (a == null) throw new IllegalArgumentException("Input array is null");
        int n = a.length;
        boolean sorted = false;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            // Find min index
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIdx]) {
                    minIdx = j;
                }
            }

            // Early termination if already sorted
            if (minIdx == i) {
                sorted = true;
                for (int k = i; k < n - 1; k++) {
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
                a[i] = a[minIdx];
                a[minIdx] = tmp;
            }
        }
    }
}
