package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Bucket Sort Implementation
 *
 * <p>Distributes elements into a number of buckets, sorts each bucket individually
 * (using insertion sort or collections sort), and then concatenates the buckets.
 * Highly effective when input is uniformly distributed over a range.
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Best Case Time: O(n + k) (uniform distribution)</li>
 *   <li>Average Case Time: O(n + k)</li>
 *   <li>Worst Case Time: O(n^2) (all elements placed into a single bucket)</li>
 *   <li>Auxiliary Space: O(n + k)</li>
 *   <li>Stability: Stable (with stable bucket sorting)</li>
 * </ul>
 */
public class BucketSort {

    /**
     * Sorts an array of doubles in ascending order using Bucket Sort.
     * Handles arbitrary range [min, max].
     *
     * @param arr the array to sort
     */
    public static void sort(double[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        double min = arr[0];
        double max = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }

        if (min == max) {
            return; // All elements are identical
        }

        // Create n buckets
        int bucketCount = n;
        List<List<Double>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribute elements into buckets
        double range = max - min;
        for (double val : arr) {
            int bucketIdx = (int) ((val - min) / range * (bucketCount - 1));
            buckets.get(bucketIdx).add(val);
        }

        // Sort individual buckets and concatenate
        int idx = 0;
        for (List<Double> bucket : buckets) {
            Collections.sort(bucket);
            for (double val : bucket) {
                arr[idx++] = val;
            }
        }
    }

    /**
     * Sorts an array of integers using Bucket Sort.
     *
     * @param arr the array to sort
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }

        if (min == max) {
            return;
        }

        int bucketCount = Math.max(1, (int) Math.sqrt(n));
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        double range = (double) max - min;
        for (int val : arr) {
            int bucketIdx = (int) (((long) val - min) / range * (bucketCount - 1));
            buckets.get(bucketIdx).add(val);
        }

        int idx = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int val : bucket) {
                arr[idx++] = val;
            }
        }
    }
}
