package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public final class SelectionSortCLI {

    private static int[] generateArray(int n, String type) {
        int[] arr = new int[n];
        Random rand = new Random();

        switch (type.toLowerCase()) {
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "reversed":
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case "random":
            default:
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(n * 10);
                break;
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Selection Sort CLI ===");
        System.out.print("Enter array size (e.g., 10, 1000, 10000): ");
        int n = scanner.nextInt();

        System.out.print("Enter array type (random / sorted / reversed): ");
        String type = scanner.next();

        int[] arr = generateArray(n, type);

        System.out.println("\nInput array (first 20 elements): "
                + Arrays.toString(Arrays.copyOf(arr, Math.min(20, arr.length))));

        Metrics metrics = SelectionSort.sort(arr);

        System.out.println("\nSorted array (first 20 elements): "
                + Arrays.toString(Arrays.copyOf(arr, Math.min(20, arr.length))));

        System.out.println("\n--- Metrics ---");
        System.out.println(metrics);

        scanner.close();
    }
}

