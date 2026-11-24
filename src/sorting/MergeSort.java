package sorting;

/**
 * Merge Sort Implementation
 *
 * <p>A divide-and-conquer algorithm that recursively divides the input array
 * into two halves, calls itself for the two halves, and then merges the two
 * sorted halves.
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Best Case Time: O(n log n)</li>
 *   <li>Average Case Time: O(n log n)</li>
 *   <li>Worst Case Time: O(n log n)</li>
 *   <li>Auxiliary Space: O(n) (requires auxiliary array for merging)</li>
 *   <li>Stability: Stable</li>
 * </ul>
 */
public class MergeSort {

    /**
     * Sorts an array of integers in ascending order using top-down recursive Merge Sort.
     *
     * @param arr the array to sort
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] aux = new int[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int[] aux, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;
        sort(arr, aux, low, mid);
        sort(arr, aux, mid + 1, high);
        merge(arr, aux, low, mid, high);
    }

    private static void merge(int[] arr, int[] aux, int low, int mid, int high) {
        // Copy elements to auxiliary array
        for (int k = low; k <= high; k++) {
            aux[k] = arr[k];
        }

        int i = low;
        int j = mid + 1;
        int k = low;

        // Merge back to original array
        while (i <= mid && j <= high) {
            if (aux[i] <= aux[j]) { // '<=' ensures stability
                arr[k++] = aux[i++];
            } else {
                arr[k++] = aux[j++];
            }
        }

        // Copy remaining elements of left sub-array (if any)
        while (i <= mid) {
            arr[k++] = aux[i++];
        }
        // No need to copy remaining right sub-array as they are already in place
    }

    /**
     * Bottom-up iterative Merge Sort. Does not use recursion stack.
     *
     * @param arr the array to sort
     */
    public static void sortIterative(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        int[] aux = new int[n];

        // Merge sub-arrays of size sz = 1, 2, 4, 8...
        for (int sz = 1; sz < n; sz *= 2) {
            for (int low = 0; low < n - sz; low += 2 * sz) {
                int mid = low + sz - 1;
                int high = Math.min(low + 2 * sz - 1, n - 1);
                merge(arr, aux, low, mid, high);
            }
        }
    }

    /**
     * Generic Merge Sort for Comparable objects in ascending order.
     *
     * @param arr the array of comparable objects to sort
     * @param <T> the type of elements
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        T[] aux = (T[]) new Comparable[arr.length];
        sortGeneric(arr, aux, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void sortGeneric(T[] arr, T[] aux, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;
        sortGeneric(arr, aux, low, mid);
        sortGeneric(arr, aux, mid + 1, high);
        mergeGeneric(arr, aux, low, mid, high);
    }

    private static <T extends Comparable<T>> void mergeGeneric(T[] arr, T[] aux, int low, int mid, int high) {
        for (int k = low; k <= high; k++) {
            aux[k] = arr[k];
        }

        int i = low;
        int j = mid + 1;
        int k = low;

        while (i <= mid && j <= high) {
            if (aux[i].compareTo(aux[j]) <= 0) {
                arr[k++] = aux[i++];
            } else {
                arr[k++] = aux[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = aux[i++];
        }
    }
}
