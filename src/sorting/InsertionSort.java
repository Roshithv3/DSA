package sorting;

/**
 * Insertion Sort Implementation
 *
 * <p>Builds the sorted array one element at a time by repeatedly taking
 * the next element and inserting it into its correct position within
 * the already sorted portion of the array.
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Best Case Time: O(n) (when array is already sorted or nearly sorted)</li>
 *   <li>Average Case Time: O(n^2)</li>
 *   <li>Worst Case Time: O(n^2) (reverse sorted)</li>
 *   <li>Auxiliary Space: O(1) (In-place)</li>
 *   <li>Stability: Stable</li>
 * </ul>
 */
public class InsertionSort {

    /**
     * Sorts an array of integers in ascending order using standard Insertion Sort.
     *
     * @param arr the array to sort
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Shift elements that are greater than key to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Binary Insertion Sort uses binary search to find the correct
     * location to insert the selected item, reducing comparisons to O(log n).
     * Number of shifts remains O(n) in worst/average case.
     *
     * @param arr the array to sort
     */
    public static void binaryInsertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];

            // Find insertion location using binary search in arr[0...i-1]
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (key < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1; // ensures stability for equal keys
                }
            }

            // Shift elements from i-1 down to low
            for (int j = i - 1; j >= low; j--) {
                arr[j + 1] = arr[j];
            }
            arr[low] = key;
        }
    }

    /**
     * Generic Insertion Sort for Comparable objects in ascending order.
     *
     * @param arr the array of comparable objects to sort
     * @param <T> the type of elements
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
