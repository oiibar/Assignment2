import org.example.Metrics;
import org.example.SelectionSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class SelectionSortTest {
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
        assertThrows(IllegalArgumentException.class, () -> {
            SelectionSort.sort(null);
        });
    }
}
