package sorting;

/**
 * Counting Sort Implementation
 *
 * <p>A non-comparison based sorting algorithm that operates by counting the number
 * of objects having each distinct key value, then calculating the positions of each
 * key in the output sequence.
 *
 * <p>This implementation handles both positive and negative integers by offsetting
 * with the minimum value in the array.
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Best / Average / Worst Case Time: O(n + k), where k is the range (max - min + 1)</li>
 *   <li>Auxiliary Space: O(n + k)</li>
 *   <li>Stability: Stable</li>
 * </ul>
 */
public class CountingSort {

    /**
     * Sorts an array of integers (including negatives) in ascending order using Counting Sort.
     *
     * @param arr the array to sort
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }

        // Check for potential overflow of range
        long rangeLong = (long) max - min + 1;
        if (rangeLong > 10_000_000) {
            throw new IllegalArgumentException("Range too large for CountingSort: " + rangeLong);
        }

        int range = (int) rangeLong;
        int[] count = new int[range];
        int[] output = new int[arr.length];

        // Store frequency of each element
        for (int value : arr) {
            count[value - min]++;
        }

        // Change count[i] so that it contains the actual position of this element in output[]
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array from right to left to preserve stability
        for (int i = arr.length - 1; i >= 0; i--) {
            int val = arr[i];
            output[count[val - min] - 1] = val;
            count[val - min]--;
        }

        // Copy the output array to arr
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
}
