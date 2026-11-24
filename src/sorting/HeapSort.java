package sorting;

/**
 * Heap Sort Implementation
 *
 * <p>A comparison-based sorting technique based on a Binary Heap data structure.
 * Similar to selection sort where we first find the maximum element and place it
 * at the end. We repeat the same process for the remaining elements.
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Best Case Time: O(n log n)</li>
 *   <li>Average Case Time: O(n log n)</li>
 *   <li>Worst Case Time: O(n log n)</li>
 *   <li>Auxiliary Space: O(1) (In-place)</li>
 *   <li>Stability: Not Stable</li>
 * </ul>
 */
public class HeapSort {

    /**
     * Sorts an array of integers in ascending order using Heap Sort.
     *
     * @param arr the array to sort
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;

        // Build max heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;       // Initialize largest as root
        int left = 2 * i + 1;  // left child = 2*i + 1
        int right = 2 * i + 2; // right child = 2*i + 2

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    /**
     * Generic Heap Sort for Comparable objects in ascending order.
     *
     * @param arr the array of comparable objects to sort
     * @param <T> the type of elements
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyGeneric(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            T temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapifyGeneric(arr, i, 0);
        }
    }

    private static <T extends Comparable<T>> void heapifyGeneric(T[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }

        if (right < n && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            T swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapifyGeneric(arr, n, largest);
        }
    }
}
