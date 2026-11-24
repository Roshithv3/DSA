package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Interactive demonstration and performance benchmark of sorting algorithms in the sorting package.
 */
public class SortingDemo {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("          DSA Java Sorting Algorithms Demonstration           ");
        System.out.println("===============================================================\n");

        int[] sample = {64, -34, 25, 12, -22, 11, 90, 0, 25, -5};
        System.out.println("Original Sample Array: " + Arrays.toString(sample));
        System.out.println("---------------------------------------------------------------");

        demonstrateAlgorithm("Bubble Sort", sample.clone(), BubbleSort::sort);
        demonstrateAlgorithm("Selection Sort", sample.clone(), SelectionSort::sort);
        demonstrateAlgorithm("Insertion Sort", sample.clone(), InsertionSort::sort);
        demonstrateAlgorithm("Binary Insertion Sort", sample.clone(), InsertionSort::binaryInsertionSort);
        demonstrateAlgorithm("Shell Sort", sample.clone(), ShellSort::sort);
        demonstrateAlgorithm("Merge Sort (Recursive)", sample.clone(), MergeSort::sort);
        demonstrateAlgorithm("Merge Sort (Iterative)", sample.clone(), MergeSort::sortIterative);
        demonstrateAlgorithm("Quick Sort (Randomized)", sample.clone(), QuickSort::sort);
        demonstrateAlgorithm("Quick Sort (3-Way Partition)", sample.clone(), QuickSort::sort3Way);
        demonstrateAlgorithm("Heap Sort", sample.clone(), HeapSort::sort);
        demonstrateAlgorithm("Counting Sort", sample.clone(), CountingSort::sort);
        demonstrateAlgorithm("Radix Sort", sample.clone(), RadixSort::sort);
        demonstrateAlgorithm("Bucket Sort (int)", sample.clone(), BucketSort::sort);
        demonstrateAlgorithm("Cycle Sort", sample.clone(), CycleSort::sort);

        // Floating point bucket sort demonstration
        double[] doubleArr = {0.897, 0.565, 0.656, 0.1234, 0.665, 0.3434};
        System.out.printf("%-28s : Before: %s%n", "Bucket Sort (double)", Arrays.toString(doubleArr));
        BucketSort.sort(doubleArr);
        System.out.printf("%-28s : After : %s%n%n", "Bucket Sort (double)", Arrays.toString(doubleArr));

        // Generic sorting demonstration
        String[] words = {"banana", "apple", "orange", "mango", "grape", "cherry"};
        System.out.printf("%-28s : Before: %s%n", "MergeSort (Generic String)", Arrays.toString(words));
        MergeSort.sort(words);
        System.out.printf("%-28s : After : %s%n%n", "MergeSort (Generic String)", Arrays.toString(words));

        benchmarkLargeArrays();
    }

    private static void demonstrateAlgorithm(String name, int[] arr, Algorithm algorithm) {
        algorithm.apply(arr);
        System.out.printf("%-28s : %s%n", name, Arrays.toString(arr));
    }

    private static void benchmarkLargeArrays() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("    Performance Benchmark on 20,000 Random Integers (in ms)    ");
        System.out.println("---------------------------------------------------------------");

        int size = 20_000;
        Random rand = new Random(42);
        int[] original = new int[size];
        for (int i = 0; i < size; i++) {
            original[i] = rand.nextInt(100_000) - 50_000;
        }

        runBenchmark("Bubble Sort", original.clone(), BubbleSort::sort);
        runBenchmark("Selection Sort", original.clone(), SelectionSort::sort);
        runBenchmark("Insertion Sort", original.clone(), InsertionSort::sort);
        runBenchmark("Shell Sort", original.clone(), ShellSort::sort);
        runBenchmark("Merge Sort (Recursive)", original.clone(), MergeSort::sort);
        runBenchmark("Merge Sort (Iterative)", original.clone(), MergeSort::sortIterative);
        runBenchmark("Quick Sort (Randomized)", original.clone(), QuickSort::sort);
        runBenchmark("Quick Sort (3-Way)", original.clone(), QuickSort::sort3Way);
        runBenchmark("Heap Sort", original.clone(), HeapSort::sort);
        runBenchmark("Counting Sort", original.clone(), CountingSort::sort);
        runBenchmark("Radix Sort", original.clone(), RadixSort::sort);
        runBenchmark("Bucket Sort", original.clone(), BucketSort::sort);
        runBenchmark("Cycle Sort", original.clone(), CycleSort::sort);
        System.out.println("===============================================================\n");
    }

    private static void runBenchmark(String name, int[] arr, Algorithm algorithm) {
        long start = System.nanoTime();
        algorithm.apply(arr);
        long elapsedMs = (System.nanoTime() - start) / 1_000_000;

        // Verify sortedness
        boolean sorted = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                sorted = false;
                break;
            }
        }
        System.out.printf("%-28s : %6d ms   [Sorted: %s]%n", name, elapsedMs, sorted ? "PASSED" : "FAILED");
    }

    @FunctionalInterface
    private interface Algorithm {
        void apply(int[] arr);
    }
}
