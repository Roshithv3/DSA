package sorting;

/**
 * Shell Sort Implementation
 *
 * <p>An optimization of Insertion Sort that allows the exchange of far items.
 * The method starts by sorting pairs of elements far apart from each other,
 * then progressively reducing the gap between elements to be compared.
 * This implementation uses Knuth's gap sequence: h = 3*h + 1 (1, 4, 13, 40, 121...).
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Best Case Time: O(n log n)</li>
 *   <li>Average Case Time: Depends on gap sequence; Knuth sequence ~ O(n^(4/3)) or O(n^(3/2))</li>
 *   <li>Worst Case Time: O(n^2) with worst gaps, O(n^(3/2)) with Knuth sequence</li>
 *   <li>Auxiliary Space: O(1) (In-place)</li>
 *   <li>Stability: Not Stable</li>
 * </ul>
 */
public class ShellSort {

    /**
     * Sorts an array of integers in ascending order using Shell Sort with Knuth's gap sequence.
     *
     * @param arr the array to sort
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;

        // Calculate largest gap using Knuth's formula: h = 3*h + 1
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        // Perform gapped insertion sort
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= h && arr[j - h] > temp) {
                    arr[j] = arr[j - h];
                    j -= h;
                }
                arr[j] = temp;
            }
            h /= 3;
        }
    }

    /**
     * Generic Shell Sort for Comparable objects in ascending order.
     *
     * @param arr the array of comparable objects to sort
     * @param <T> the type of elements
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                T temp = arr[i];
                int j = i;
                while (j >= h && arr[j - h].compareTo(temp) > 0) {
                    arr[j] = arr[j - h];
                    j -= h;
                }
                arr[j] = temp;
            }
            h /= 3;
        }
    }
}
