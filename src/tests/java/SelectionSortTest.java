import org.example.metrics.Metrics;
import org.example.algorithm.SelectionSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class SelectionSortTest {

    // Helper that checks if array is sorted
    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }

    @Test
    public void testEmptyArray() {
        int[] a = {};
        Metrics m = SelectionSort.sort(a);
        assertTrue(isSorted(a), "Empty array should remain sorted");
        assertEquals(0, a.length);
        assertNotNull(m);
    }

    @Test
    public void testRandomArraysAgainstJavaSort() {
        // Property-based test: compare SelectionSort with Arrays.sort()
        Random rand = new Random();
        for (int n = 1; n <= 500; n += 50) {
            int[] arr = rand.ints(n, 0, 1000).toArray();
            int[] expected = Arrays.copyOf(arr, arr.length);
            Arrays.sort(expected);

            int[] actual = Arrays.copyOf(arr, arr.length);
            SelectionSort.sort(actual);

            assertArrayEquals(expected, actual, "SelectionSort result must match Arrays.sort()");
        }
    }

    @Test
    public void testNearlySortedArray() {
        // Array almost sorted except for one inversion
        int[] arr = {1, 2, 3, 5, 4, 6, 7, 8};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        SelectionSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testScalability() {
        // Performance check on growing input sizes
        int[] sizes = {100, 1000, 10000};
        for (int n : sizes) {
            int[] arr = new Random().ints(n, 0, n*10).toArray();
            Metrics m = SelectionSort.sort(arr);
            assertTrue(isSorted(arr), "Array should be sorted for size " + n);
            System.out.printf("n=%d -> time=%.3f ms, comparisons=%d, swaps=%d%n",
                    n, m.timeNano / 1_000_000.0, m.comparisons, m.swaps);
        }
    }

    @Test
    public void testSingleElementArray() {
        int[] a = {42};
        Metrics m = SelectionSort.sort(a);
        assertTrue(isSorted(a), "Single element array should remain sorted");
        assertEquals(0, m.swaps, "No swaps needed for single element");
    }

    @Test
    public void testTwoElementsSorted() {
        int[] a = {1, 2};
        Metrics m = SelectionSort.sort(a);
        assertArrayEquals(new int[]{1, 2}, a);
        assertTrue(m.comparisons > 0, "At least one comparison expected");
    }

    @Test
    public void testTwoElementsUnsorted() {
        int[] a = {2, 1};
        Metrics m = SelectionSort.sort(a);
        assertArrayEquals(new int[]{1, 2}, a);
        assertEquals(1, m.swaps, "One swap should occur");
    }

    @Test
    public void testAlreadySortedArray() {
        int[] a = {1, 2, 3, 4, 5};
        Metrics m = SelectionSort.sort(a);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, a);
        assertEquals(0, m.swaps, "No swaps expected for sorted array (early termination)");
    }

    @Test
    public void testReverseArray() {
        int[] a = {5, 4, 3, 2, 1};
        Metrics m = SelectionSort.sort(a);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, a);
        assertTrue(m.swaps > 0, "Swaps must occur for reverse array");
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] a = {3, 1, 2, 3, 1};
        Metrics m = SelectionSort.sort(a);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, a);
        assertTrue(isSorted(a));
    }

    @Test
    public void testLargeArray() {
        // Large reversed array to stress test algorithm
        int n = 1000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = n - i;
        }
        Metrics m = SelectionSort.sort(a);
        assertTrue(isSorted(a), "Large reversed array should be sorted");
        assertTrue(m.comparisons > 0);
        assertTrue(m.swaps > 0);
    }

    @Test
    public void testNullArrayThrowsException() {
        // Verify defensive programming (null input not allowed)
        assertThrows(IllegalArgumentException.class, () -> {
            SelectionSort.sort(null);
        });
    }
}
