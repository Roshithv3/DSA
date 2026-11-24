package sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Radix Sort Implementation
 *
 * <p>A non-comparative sorting algorithm that sorts data with integer keys by grouping
 * keys by the individual digits which share the same significant position and value.
 * This implementation uses LSD (Least Significant Digit) Radix Sort in base 10
 * and properly supports negative integers.
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Best / Average / Worst Case Time: O(d * (n + b)), where d is number of digits, b is base (10)</li>
 *   <li>Auxiliary Space: O(n + b)</li>
 *   <li>Stability: Stable</li>
 * </ul>
 */
public class RadixSort {

    private static final int BASE = 10;

    /**
     * Sorts an array of integers (including negative numbers) in ascending order using Radix Sort.
     *
     * @param arr the array to sort
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Partition elements into negatives and non-negatives
        List<Integer> negativesList = new ArrayList<>();
        List<Integer> nonNegativesList = new ArrayList<>();

        for (int num : arr) {
            if (num < 0) {
                // Store absolute values of negative numbers
                negativesList.add(-num);
            } else {
                nonNegativesList.add(num);
            }
        }

        int[] negatives = negativesList.stream().mapToInt(Integer::intValue).toArray();
        int[] nonNegatives = nonNegativesList.stream().mapToInt(Integer::intValue).toArray();

        // Sort both partitions using standard LSD Radix Sort
        sortNonNegative(negatives);
        sortNonNegative(nonNegatives);

        // Merge back into arr:
        // Negatives are reversed and negated back (larger magnitude means smaller value)
        int idx = 0;
        for (int i = negatives.length - 1; i >= 0; i--) {
            arr[idx++] = -negatives[i];
        }
        for (int nonNegative : nonNegatives) {
            arr[idx++] = nonNegative;
        }
    }

    /**
     * Sorts an array of non-negative integers using LSD Radix Sort.
     *
     * @param arr array of non-negative integers
     */
    public static void sortNonNegative(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // Apply counting sort for every digit from 1's place to most significant digit
        for (long exp = 1; max / exp > 0; exp *= BASE) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, long exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[BASE];

        for (int value : arr) {
            int digit = (int) ((value / exp) % BASE);
            count[digit]++;
        }

        for (int i = 1; i < BASE; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (int) ((arr[i] / exp) % BASE);
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }
}
