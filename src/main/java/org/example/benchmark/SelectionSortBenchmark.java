package org.example.benchmark;
import org.example.metrics.Metrics;
import org.example.algorithm.SelectionSort;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.Arrays;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SelectionSortBenchmark {

    @Param({"100", "1000", "10000"})
    private int n;

    @Param({"random", "sorted", "reversed"})
    private String type;

    private int[] arr;

    private int[] generateArray(int n, String type) {
        int[] a = new int[n];
        Random rand = new Random();
        switch (type) {
            case "sorted":
                for (int i = 0; i < n; i++) a[i] = i;
                break;
            case "reversed":
                for (int i = 0; i < n; i++) a[i] = n - i;
                break;
            case "random":
            default:
                for (int i = 0; i < n; i++) a[i] = rand.nextInt(n * 10);
                break;
        }
        return a;
    }

    @Setup(Level.Invocation)
    public void setup() {
        arr = generateArray(n, type);
    }

    @Benchmark
    public Metrics benchmarkSelectionSort() {
        int[] copy = Arrays.copyOf(arr, arr.length);
        return SelectionSort.sort(copy);
    }
}
