package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Rigorous self-contained verification and testing suite for all sorting algorithms.
 * Tests edge cases including:
 * <ul>
 *   <li>Empty arrays</li>
 *   <li>Single element arrays</li>
 *   <li>Two element arrays (sorted and inverted)</li>
 *   <li>Already sorted arrays</li>
 *   <li>Reverse sorted arrays</li>
 *   <li>Arrays with all identical elements</li>
 *   <li>Arrays with large negative and positive numbers</li>
 *   <li>Randomly shuffled arrays</li>
 * </ul>
 */
public class SortingTest {

    @FunctionalInterface
    interface IntSorter {
        void sort(int[] arr);
    }

    public static void main(String[] args) {
        System.out.println("Running Sorting Test Suite...\n");

        NamedSorter[] sorters = {
            new NamedSorter("BubbleSort", BubbleSort::sort),
            new NamedSorter("SelectionSort", SelectionSort::sort),
            new NamedSorter("InsertionSort", InsertionSort::sort),
            new NamedSorter("BinaryInsertionSort", InsertionSort::binaryInsertionSort),
            new NamedSorter("ShellSort", ShellSort::sort),
            new NamedSorter("MergeSort (Recursive)", MergeSort::sort),
            new NamedSorter("MergeSort (Iterative)", MergeSort::sortIterative),
            new NamedSorter("QuickSort (Randomized)", QuickSort::sort),
            new NamedSorter("QuickSort (3-Way)", QuickSort::sort3Way),
            new NamedSorter("HeapSort", HeapSort::sort),
            new NamedSorter("CountingSort", CountingSort::sort),
            new NamedSorter("RadixSort", RadixSort::sort),
            new NamedSorter("BucketSort (int)", BucketSort::sort),
            new NamedSorter("CycleSort", CycleSort::sort)
        };

        int totalTests = 0;
        int passedTests = 0;

        for (NamedSorter ns : sorters) {
            boolean allPassed = testSorter(ns);
            totalTests++;
            if (allPassed) {
                passedTests++;
                System.out.printf("  [PASS] %s%n", ns.name);
            } else {
                System.out.printf("  [FAIL] %s%n", ns.name);
            }
        }

        // Test BucketSort double
        totalTests++;
        if (testBucketSortDouble()) {
            passedTests++;
            System.out.println("  [PASS] BucketSort (double)");
        } else {
            System.out.println("  [FAIL] BucketSort (double)");
        }

        // Test Generics
        totalTests++;
        if (testGenerics()) {
            passedTests++;
            System.out.println("  [PASS] Generic Sorters (Comparable<T>)");
        } else {
            System.out.println("  [FAIL] Generic Sorters (Comparable<T>)");
        }

        System.out.printf("%nTest Results: %d / %d sorters passed.%n", passedTests, totalTests);

        if (passedTests != totalTests) {
            System.err.println("Some tests failed!");
            System.exit(1);
        } else {
            System.out.println("All sorting algorithms verified successfully!");
        }
    }

    private static boolean testSorter(NamedSorter ns) {
        int[][] testCases = {
            {},
            {42},
            {1, 2},
            {2, 1},
            {1, 2, 3, 4, 5, 6, 7},
            {7, 6, 5, 4, 3, 2, 1},
            {5, 5, 5, 5, 5},
            {-10, -5, 0, 5, 10},
            {10, -5, 20, -100, 50, 0, -1},
            {3, -1, 4, 1, 5, 9, 2, 6, 5, 3, 5}
        };

        for (int[] original : testCases) {
            int[] test = original.clone();
            int[] expected = original.clone();
            Arrays.sort(expected);

            try {
                ns.sorter.sort(test);
            } catch (Exception e) {
                System.err.printf("Exception in %s with input %s: %s%n", ns.name, Arrays.toString(original), e.getMessage());
                return false;
            }

            if (!Arrays.equals(test, expected)) {
                System.err.printf("Mismatch in %s! Expected: %s, Got: %s%n", ns.name, Arrays.toString(expected), Arrays.toString(test));
                return false;
            }
        }

        // Random large test
        Random rand = new Random(12345);
        for (int t = 0; t < 10; t++) {
            int size = 500;
            int[] original = new int[size];
            for (int i = 0; i < size; i++) {
                original[i] = rand.nextInt(2000) - 1000;
            }

            int[] test = original.clone();
            int[] expected = original.clone();
            Arrays.sort(expected);

            ns.sorter.sort(test);
            if (!Arrays.equals(test, expected)) {
                System.err.printf("Mismatch in %s on random test!%n", ns.name);
                return false;
            }
        }

        return true;
    }

    private static boolean testBucketSortDouble() {
        double[][] testCases = {
            {},
            {0.5},
            {0.9, 0.1},
            {0.1, 0.9},
            {0.3, 0.3, 0.3},
            {-2.5, 3.4, -0.1, 10.2, 0.0, -10.5}
        };

        for (double[] original : testCases) {
            double[] test = original.clone();
            double[] expected = original.clone();
            Arrays.sort(expected);

            BucketSort.sort(test);
            if (!Arrays.equals(test, expected)) {
                return false;
            }
        }
        return true;
    }

    private static boolean testGenerics() {
        String[] original = {"pear", "apple", "orange", "banana", "grape", "avocado"};
        String[] expected = original.clone();
        Arrays.sort(expected);

        // Test BubbleSort generic
        String[] t1 = original.clone();
        BubbleSort.sort(t1);
        if (!Arrays.equals(t1, expected)) return false;

        // Test SelectionSort generic
        String[] t2 = original.clone();
        SelectionSort.sort(t2);
        if (!Arrays.equals(t2, expected)) return false;

        // Test InsertionSort generic
        String[] t3 = original.clone();
        InsertionSort.sort(t3);
        if (!Arrays.equals(t3, expected)) return false;

        // Test ShellSort generic
        String[] t4 = original.clone();
        ShellSort.sort(t4);
        if (!Arrays.equals(t4, expected)) return false;

        // Test MergeSort generic
        String[] t5 = original.clone();
        MergeSort.sort(t5);
        if (!Arrays.equals(t5, expected)) return false;

        // Test QuickSort generic
        String[] t6 = original.clone();
        QuickSort.sort(t6);
        if (!Arrays.equals(t6, expected)) return false;

        // Test HeapSort generic
        String[] t7 = original.clone();
        HeapSort.sort(t7);
        if (!Arrays.equals(t7, expected)) return false;

        return true;
    }

    private static class NamedSorter {
        String name;
        IntSorter sorter;

        NamedSorter(String name, IntSorter sorter) {
            this.name = name;
            this.sorter = sorter;
        }
    }
}
