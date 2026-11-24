package sorting;

import java.util.Random;

/**
 * Quick Sort Implementation
 *
 * <p>A divide-and-conquer algorithm that selects a 'pivot' element and partitions
 * the other elements into two sub-arrays according to whether they are less than
 * or greater than the pivot. The sub-arrays are then recursively sorted.
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Best Case Time: O(n log n)</li>
 *   <li>Average Case Time: O(n log n)</li>
 *   <li>Worst Case Time: O(n^2) (mitigated by randomized pivot selection)</li>
 *   <li>Auxiliary Space: O(log n) call stack</li>
 *   <li>Stability: Not Stable</li>
 * </ul>
 */
public class QuickSort {

    private static final Random RANDOM = new Random();

    /**
     * Sorts an array of integers in ascending order using Randomized QuickSort.
     *
     * @param arr the array to sort
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = randomizedPartition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int randomizedPartition(int[] arr, int low, int high) {
        int randomIndex = low + RANDOM.nextInt(high - low + 1);
        swap(arr, randomIndex, high);
        return partition(arr, low, high);
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * 3-Way QuickSort (Dutch National Flag Partitioning).
     * Highly efficient when arrays contain many duplicate keys (O(n) on identical keys).
     *
     * @param arr the array to sort
     */
    public static void sort3Way(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort3Way(arr, 0, arr.length - 1);
    }

    private static void quickSort3Way(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        // Random pivot
        int randomIndex = low + RANDOM.nextInt(high - low + 1);
        swap(arr, low, randomIndex);

        int pivot = arr[low];
        int lt = low;       // arr[low...lt-1] < pivot
        int gt = high;      // arr[gt+1...high] > pivot
        int i = low + 1;    // arr[lt...i-1] == pivot

        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }

        quickSort3Way(arr, low, lt - 1);
        quickSort3Way(arr, gt + 1, high);
    }

    /**
     * Generic QuickSort for Comparable objects in ascending order.
     *
     * @param arr the array of comparable objects to sort
     * @param <T> the type of elements
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSortGeneric(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSortGeneric(T[] arr, int low, int high) {
        if (low < high) {
            int randomIndex = low + RANDOM.nextInt(high - low + 1);
            swapGeneric(arr, randomIndex, high);

            T pivot = arr[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (arr[j].compareTo(pivot) <= 0) {
                    i++;
                    swapGeneric(arr, i, j);
                }
            }
            swapGeneric(arr, i + 1, high);
            int pivotIndex = i + 1;

            quickSortGeneric(arr, low, pivotIndex - 1);
            quickSortGeneric(arr, pivotIndex + 1, high);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static <T> void swapGeneric(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
