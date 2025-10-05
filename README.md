# Assignment 1 – Selection Sort

## Algorithm Details

* **Selection Sort**:
    * Iteratively selects the minimum element and places it at the correct position.
    * Includes **early termination**: stops if the array is already sorted.
* **Memory Optimization**:
    * Runs **in-place** with only `O(1)` extra space.
    * No recursion, avoids stack growth.

---

## Recurrence Analysis

### Selection Sort

* The recurrence is:
  `T(n) = T(n-1) + Θ(n)`
  from scanning the unsorted portion to find the minimum.

* Simplifies to:
  `T(n) = Θ(n²)`

# Asymptotic Complexity Analysis

* **Best Case (Ω(n))**: Already sorted array triggers early termination.

* **Average Case (Θ(n²))**: Random input requires ~n²/2 comparisons.

* **Worst Case (O(n²))**: Reversed array requires maximum comparisons and swaps.

### Space Complexity

* **Auxiliary Space**: `O(1)` (only a temp variable for swapping).
* **In-place optimization**: No additional data structures required.

---

## Summary

The experimental results confirm theoretical expectations:

* **Random & reversed inputs** → quadratic growth (Θ(n²)).
* **Sorted inputs** → near-linear time due to **early termination**.
* **Space usage** is constant.
* Optimization reduces sorted-case runtime dramatically (e.g., 10,000 elements sorted: **0.018 ms vs 43.9 ms** for random).

## ✅ Final Complexity Results

| Case         | Time Complexity | Space Complexity |
| ------------ | --------------- | ---------------- |
| Best Case    | Θ(n)            | Θ(1)             |
| Average Case | Θ(n²)           | Θ(1)             |
| Worst Case   | Θ(n²)           | Θ(1)             |

## Benchmark Results (JMH)

| n      | Random (ms) | Sorted (ms) | Reversed (ms) |
| ------ | ----------- | ----------- | ------------- |
| 100    | 0.0069      | 0.00016     | 0.0070        |
| 1,000  | 0.3881      | 0.00122     | 0.6723        |
| 10,000 | 34.9100     | 0.0147      | 26.4389       |

---

## 🔹 Running the Selection Sort CLI

The project includes an **interactive CLI** (`SelectionSortCLI`) that allows you to test the algorithm with different input sizes and array distributions.

### ▶ How to Run

1. **Compile the project** (if using Maven):

```bash
mvn clean package
```

2. **Run the CLI**:

```bash
java -cp target/your-jar-name.jar org.example.cli.SelectionSortCLI
```

(Replace `your-jar-name.jar` with the actual JAR file generated in `target/`.)

---

### 📋 CLI Input Parameters

The program will prompt you for two inputs:

1. **Array Size** – number of elements to sort.
   Example: `10`, `1000`, `10000`.

2. **Array Type** – initial distribution of elements:

    * `random` → array filled with random integers.
    * `sorted` → already sorted in ascending order.
    * `reversed` → sorted in descending order.

---

### 🖥 Example Run

```
=== Selection Sort CLI ===
Enter array size (e.g., 10, 1000, 10000): 10
Enter array type (random / sorted / reversed): random

Input array (first 20 elements): [37, 12, 95, 4, 58, 73, 21, 49, 88, 6]
Heap memory used (approx): 2048 bytes

Sorted array (first 20 elements): [4, 6, 12, 21, 37, 49, 58, 73, 88, 95]

--- Metrics ---
comparisons=45, swaps=7, arrayAccesses=102, memoryAllocations=3, timeMillis=0.012
```

---

### 📊 Metrics Reported

The CLI prints **performance metrics** collected by the `Metrics` class:

* **comparisons** → number of element comparisons
* **swaps** → number of swaps performed
* **arrayAccesses** → count of element reads/writes
* **memoryAllocations** → temporary allocations (e.g., for swaps)
* **timeMillis** → total runtime in milliseconds

This allows you to validate both **correctness** and **efficiency** interactively.
