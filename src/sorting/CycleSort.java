package sorting;

/**
 * Cycle Sort Implementation
 *
 * <p>An in-place, unstable sorting algorithm based on the idea that the permutation
 * to be sorted can be factored into cycles, which can individually be rotated to
 * give a sorted result.
 *
 * <p>Cycle sort is notable for being theoretically optimal in terms of minimizing the
 * number of memory writes: each element is either written zero times (if already in place)
 * or written at most once to its final position, yielding at most O(n) writes.
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Best Case Time: O(n^2)</li>
 *   <li>Average Case Time: O(n^2)</li>
 *   <li>Worst Case Time: O(n^2)</li>
 *   <li>Auxiliary Space: O(1) (In-place)</li>
 *   <li>Number of writes: O(n) (Theoretical minimum)</li>
 *   <li>Stability: Not Stable</li>
 * </ul>
 */
public class CycleSort {

    /**
     * Sorts an array of integers in ascending order using Cycle Sort.
     *
     * @param arr the array to sort
     * @return the total number of memory writes performed
     */
    public static int sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }

        int writes = 0;
        int n = arr.length;

        // Traverse array elements and put them to the right place
        for (int cycleStart = 0; cycleStart <= n - 2; cycleStart++) {
            int item = arr[cycleStart];

            // Find where to put the item by counting all smaller elements
            int pos = cycleStart;
            for (int i = cycleStart + 1; i < n; i++) {
                if (arr[i] < item) {
                    pos++;
                }
            }

            // If item is already in correct position, skip
            if (pos == cycleStart) {
                continue;
            }

            // Ignore all duplicate elements
            while (item == arr[pos]) {
                pos++;
            }

            // Put the item to its right position
            if (pos != cycleStart) {
                int temp = item;
                item = arr[pos];
                arr[pos] = temp;
                writes++;
            }

            // Rotate rest of the cycle
            while (pos != cycleStart) {
                pos = cycleStart;
                for (int i = cycleStart + 1; i < n; i++) {
                    if (arr[i] < item) {
                        pos++;
                    }
                }

                while (item == arr[pos]) {
                    pos++;
                }

                if (item != arr[pos]) {
                    int temp = item;
                    item = arr[pos];
                    arr[pos] = temp;
                    writes++;
                }
            }
        }
        return writes;
    }
}
