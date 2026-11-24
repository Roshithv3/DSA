package sorting;

/**
 * Selection Sort Implementation
 *
 * <p>Repeatedly finds the minimum element from the unsorted sub-array
 * and places it at the beginning.
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Best Case Time: O(n^2)</li>
 *   <li>Average Case Time: O(n^2)</li>
 *   <li>Worst Case Time: O(n^2)</li>
 *   <li>Auxiliary Space: O(1) (In-place)</li>
 *   <li>Stability: Not Stable (standard array implementation makes non-adjacent swaps)</li>
 * </ul>
 */
public class SelectionSort {

    /**
     * Sorts an array of integers in ascending order using Selection Sort.
     *
     * @param arr the array to sort
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }

    /**
     * Generic Selection Sort for Comparable objects in ascending order.
     *
     * @param arr the array of comparable objects to sort
     * @param <T> the type of elements
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                T temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }
}
