# DSA Java Sorting Package (`package sorting`)

A comprehensive, production-grade, and educational Java library implementing essential and advanced sorting algorithms for **Data Structures and Algorithms (DSA)**.

---

## 📂 Project Directory Structure

```text
DSA/
├── src/
│   └── sorting/
│       ├── package-info.java          # Package documentation
│       ├── BubbleSort.java            # Bubble Sort (with early-exit optimization & Generics)
│       ├── SelectionSort.java         # Selection Sort (in-place & Generics)
│       ├── InsertionSort.java         # Standard & Binary Insertion Sort (& Generics)
│       ├── ShellSort.java             # Shell Sort with Knuth's gap sequence (& Generics)
│       ├── MergeSort.java             # Top-down recursive & Bottom-up iterative Merge Sort (& Generics)
│       ├── QuickSort.java             # Randomized QuickSort & 3-Way Partition QuickSort (& Generics)
│       ├── HeapSort.java              # In-place Binary Max-Heap Sort (& Generics)
│       ├── CountingSort.java          # Stable Counting Sort (supports negative & positive integers)
│       ├── RadixSort.java             # LSD Radix Sort (supports negative & positive integers)
│       ├── BucketSort.java            # Bucket Sort for floating-point and integer distributions
│       ├── CycleSort.java             # Cycle Sort (optimal O(n) memory writes)
│       ├── SortingDemo.java           # Demo runner and benchmark
│       └── SortingTest.java           # Rigorous edge-case test suite (16/16 verified)
└── README.md
```

---

## ⚡ Complexity & Properties Summary Table

| Algorithm | Best Time | Average Time | Worst Time | Space Complexity | Stable? | In-Place? | Key Use Case / Notes |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Bubble Sort** | $\mathcal{O}(n)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(1)$ | **Yes** | **Yes** | Detects already sorted data in $\mathcal{O}(n)$. |
| **Selection Sort** | $\mathcal{O}(n^2)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(1)$ | **No** | **Yes** | Minimizes number of swaps ($\le n$). |
| **Insertion Sort** | $\mathcal{O}(n)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(1)$ | **Yes** | **Yes** | Extremely fast for small arrays ($n \le 32$) and nearly-sorted data. |
| **Shell Sort** | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n^{4/3})$ | $\mathcal{O}(n^{3/2})$ | $\mathcal{O}(1)$ | **No** | **Yes** | Knuth gap sequence ($3h+1$), low overhead without recursion. |
| **Merge Sort** | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n)$ | **Yes** | **No** | Guaranteed $\mathcal{O}(n \log n)$ performance and stable. |
| **Quick Sort** | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(\log n)$ | **No** | **Yes** | Cache-friendly; randomized pivot avoids $\mathcal{O}(n^2)$; 3-way partition handles duplicates. |
| **Heap Sort** | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(1)$ | **No** | **Yes** | Guaranteed $\mathcal{O}(n \log n)$ without auxiliary array allocation. |
| **Counting Sort** | $\mathcal{O}(n + k)$ | $\mathcal{O}(n + k)$ | $\mathcal{O}(n + k)$ | $\mathcal{O}(n + k)$ | **Yes** | **No** | Non-comparison sort for discrete integer ranges; handles negative integers. |
| **Radix Sort** | $\mathcal{O}(d(n + b))$ | $\mathcal{O}(d(n + b))$ | $\mathcal{O}(d(n + b))$ | $\mathcal{O}(n + b)$ | **Yes** | **No** | LSD digit-by-digit sorting; handles negative and positive integers. |
| **Bucket Sort** | $\mathcal{O}(n + k)$ | $\mathcal{O}(n + k)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(n + k)$ | **Yes** | **No** | Ideal for uniformly distributed floating-point or integer datasets. |
| **Cycle Sort** | $\mathcal{O}(n^2)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(1)$ | **No** | **Yes** | Theoretically optimal for write cycles (at most $n$ writes). |

---

## 🚀 Quick Start: Compilation and Execution

No external build tools required. OpenJDK 11+ is supported.

### 1. Compile All Classes
```bash
mkdir -p bin
javac -d bin src/sorting/*.java
```

### 2. Run the Test Suite
Runs all edge-case tests (empty, single, duplicates, sorted, reversed, negative numbers, floating points, generics):
```bash
java -cp bin sorting.SortingTest
```

### 3. Run the Demo & Performance Benchmark
```bash
java -cp bin sorting.SortingDemo
```

---

## 💡 Code Examples

### 1. Primitive Integer Array Sorting
```java
import sorting.QuickSort;
import sorting.MergeSort;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] data = {45, -12, 89, 0, 23, -5, 77, 23};

        // Randomized Quick Sort
        QuickSort.sort(data);
        System.out.println(Arrays.toString(data));
        // Output: [-12, -5, 0, 23, 23, 45, 77, 89]

        // 3-Way Quick Sort (Optimal for arrays with heavy duplicate values)
        int[] duplicates = {2, 1, 2, 0, 2, 1, 0, 2, 0};
        QuickSort.sort3Way(duplicates);
        System.out.println(Arrays.toString(duplicates));
        // Output: [0, 0, 0, 1, 1, 2, 2, 2, 2]
    }
}
```

### 2. Generic Object Sorting
Comparison-based sorts support any `Comparable<T>`:
```java
import sorting.MergeSort;
import java.util.Arrays;

public class GenericDemo {
    public static void main(String[] args) {
        String[] fruits = {"orange", "apple", "mango", "banana", "kiwi"};
        MergeSort.sort(fruits);
        System.out.println(Arrays.toString(fruits));
        // Output: [apple, banana, kiwi, mango, orange]
    }
}
```

### 3. Floating-Point Sorting with BucketSort
```java
import sorting.BucketSort;
import java.util.Arrays;

public class FloatDemo {
    public static void main(String[] args) {
        double[] vals = {0.897, 0.565, 0.656, 0.1234, 0.665, 0.3434};
        BucketSort.sort(vals);
        System.out.println(Arrays.toString(vals));
    }
}
```

### 4. Non-Comparison Sorting with CountingSort & RadixSort
```java
import sorting.CountingSort;
import sorting.RadixSort;
import java.util.Arrays;

public class NonComparisonDemo {
    public static void main(String[] args) {
        // Handles both negative and positive integers
        int[] numbers = {-105, 42, -3, 888, 0, -50, 12};

        CountingSort.sort(numbers);
        System.out.println(Arrays.toString(numbers));
        // Output: [-105, -50, -3, 0, 12, 42, 888]

        int[] largeInts = {-9999, 1234, -45, 87654, 0, 342};
        RadixSort.sort(largeInts);
        System.out.println(Arrays.toString(largeInts));
        // Output: [-9999, -45, 0, 342, 1234, 87654]
    }
}
```
