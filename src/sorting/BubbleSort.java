package sorting;

/**
 * Bubble Sort Implementation
 *
 * <p>Repeatedly steps through the array, compares adjacent elements,
 * and swaps them if they are in the wrong order. The pass through the
 * array is repeated until the array is sorted.
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Best Case Time: O(n) (when already sorted, with swapped flag optimization)</li>
 *   <li>Average Case Time: O(n^2)</li>
 *   <li>Worst Case Time: O(n^2)</li>
 *   <li>Auxiliary Space: O(1) (In-place)</li>
 *   <li>Stability: Stable</li>
 * </ul>
 */
public class BubbleSort {

    /**
     * Sorts an array of integers in ascending order using Bubble Sort.
     *
     * @param arr the array to sort
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            // Last i elements are already in place
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped in inner loop, array is sorted
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Generic Bubble Sort for Comparable objects in ascending order.
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
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
