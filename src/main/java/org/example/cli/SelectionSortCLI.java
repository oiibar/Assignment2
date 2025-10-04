package org.example.cli;
import org.example.algorithm.SelectionSort;
import org.example.metrics.Metrics;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public final class SelectionSortCLI {

    // Util to generate arrays of different types (sorted, reversed, random)
    private static int[] generateArray(int n, String type) {
        int[] arr = new int[n];
        Random rand = new Random();

        switch (type.toLowerCase()) {
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i; // ascending order
                break;
            case "reversed":
                for (int i = 0; i < n; i++) arr[i] = n - i; // descending order
                break;
            case "random":
            default:
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(n * 10); // random values
                break;
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Selection Sort CLI ===");
        System.out.print("Enter array size (e.g., 10, 1000, 10000): ");
        int n = scanner.nextInt(); // choose array size

        System.out.print("Enter array type (random / sorted / reversed): ");
        String type = scanner.next(); // choose distribution type

        // Generate test array
        int[] arr = generateArray(n, type);

        // Show first 20 elements
        System.out.println("\nInput array (first 20 elements): "
                + Arrays.toString(Arrays.copyOf(arr, Math.min(20, arr.length))));

        // Rough memory usage before/after sorting
        long before = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Metrics metrics = SelectionSort.sort(arr);
        long after = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Heap memory used (approx): " + (after - before) + " bytes");

        // Show sorted result
        System.out.println("\nSorted array (first 20 elements): "
                + Arrays.toString(Arrays.copyOf(arr, Math.min(20, arr.length))));

        // Show collected performance metrics
        System.out.println("\n--- Metrics ---");
        System.out.println(metrics);

        scanner.close();
    }
}
