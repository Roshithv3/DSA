# Elementary Sorting Algorithms

This guide explains the inner workings, loop mechanics, and complexity analysis for the three elementary comparison-based sorting algorithms: **Bubble Sort**, **Selection Sort**, and **Insertion Sort**.

Each algorithm is broken down by its **exact loop bounds**, explaining **why each loop starts and ends where it does**, followed by an index trace and evaluation against the **5 Criteria for Analysis**:
1. **Number of Comparisons**
2. **Number of Swaps / Shifts**
3. **Adaptive**
4. **Stable**
5. **Extra Memory**

---

## 1. Bubble Sort

### ⚙️ Core Loop Logic
```java
for (int i = 0; i < n - 1; i++) {
    boolean swapped = false;
    for (int j = 0; j < n - 1 - i; j++) {
        if (arr[j] > arr[j + 1]) {
            int temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
            swapped = true;
        }
    }
    if (!swapped) break;
}
```

### 🧠 Loop Bound Intuition (Why these exact indices?)

1. **Why Outer Loop is `i < n - 1` (Why stop before the last element?):**
   * In each pass `i`, one element is guaranteed to reach its final sorted position at the end.
   * After placing `n - 1` elements in their correct positions, there is only **1 element left** at index `0`.
   * An element by itself has nothing left to be compared with and is already in its correct spot. Running the loop for `i = n - 1` would be completely redundant.

2. **Why Inner Loop is `j < n - 1 - i` (Why `n - 1 - i`?):**
   * **The `- 1` part:** We compare `arr[j]` with `arr[j + 1]`. If `j` were allowed to reach `n - 1`, then `j + 1` would access index `n`, throwing an `ArrayIndexOutOfBoundsException`.
   * **The `- i` part:** After pass `0`, the 1 largest element is locked at index `n - 1`. After pass `1`, the 2 largest elements are locked at indices `n - 2` and `n - 1`. After `i` passes, the last `i` elements are already fully sorted. Subtracting `i` skips those already-sorted positions so we never do useless comparisons on them.

3. **Why the `swapped` flag?**
   * If an entire inner loop pass finishes with `swapped == false`, it means for every single adjacent pair, `arr[j] <= arr[j + 1]`.
   * This proves the array is already sorted, allowing us to `break` immediately in **O(n)** time rather than wasting O(n²) iterations.

---

### 📝 Step-by-Step Index Trace
Array: `arr = [5, 1, 4, 2, 8]`, `n = 5`

* **Pass `i = 0` (Inner loop runs for `j = 0` to `j < 5 - 1 - 0 = 4`):**
  * `j = 0`: Compare `arr[0]` (5) and `arr[1]` (1) → 5 > 1 → Swap → `[1, 5, 4, 2, 8]`
  * `j = 1`: Compare `arr[1]` (5) and `arr[2]` (4) → 5 > 4 → Swap → `[1, 4, 5, 2, 8]`
  * `j = 2`: Compare `arr[2]` (5) and `arr[3]` (2) → 5 > 2 → Swap → `[1, 4, 2, 5, 8]`
  * `j = 3`: Compare `arr[3]` (5) and `arr[4]` (8) → 5 < 8 → No swap → `[1, 4, 2, 5, 8]`  
    *(Largest element `8` is now locked at index 4)*

* **Pass `i = 1` (Inner loop runs for `j = 0` to `j < 5 - 1 - 1 = 3`):**
  * `j = 0`: Compare `arr[0]` (1) and `arr[1]` (4) → 1 < 4 → No swap
  * `j = 1`: Compare `arr[1]` (4) and `arr[2]` (2) → 4 > 2 → Swap → `[1, 2, 4, 5, 8]`
  * `j = 2`: Compare `arr[2]` (4) and `arr[3]` (5) → 4 < 5 → No swap  
    *(Second largest element `5` is now locked at index 3)*

* **Pass `i = 2` (Inner loop runs for `j = 0` to `j < 5 - 1 - 2 = 2`):**
  * `j = 0`: Compare `arr[0]` (1) and `arr[1]` (2) → 1 < 2 → No swap
  * `j = 1`: Compare `arr[1]` (2) and `arr[2]` (4) → 2 < 4 → No swap  
    *(Zero swaps occurred → `swapped` is false → `break`)*

---

### 🔍 Criteria for Analysis

1. **Number of Comparisons:**
   * **Best Case:** `n - 1` = **O(n)** (array already sorted; first pass makes `n - 1` comparisons and breaks).
   * **Worst / Average Case:** `(n - 1) + (n - 2) + ... + 1 = n(n - 1)/2` = **O(n²)** comparisons.
2. **Number of Swaps:**
   * **Best Case:** `0` = **O(1)** (when already sorted).
   * **Worst Case:** `n(n - 1)/2` = **O(n²)** (when reverse sorted, every comparison requires a swap).
   * **Average Case:** `≈ n(n - 1)/4` = **O(n²)**.
3. **Adaptive:**
   * **Yes** (via `swapped` flag). It adapts to pre-existing order and finishes in **O(n)** time on sorted input.
4. **Stable:**
   * **Yes**. It only swaps when `arr[j] > arr[j + 1]`. When elements are equal (`arr[j] == arr[j + 1]`), no swap occurs, preserving their original order.
5. **Extra Memory:**
   * **O(1) (In-place)**. Only uses a couple of variables (`temp`, `swapped`).

#### ⏱ Time Complexity Summary:
* **Best Case:** **O(n)**
* **Average Case:** **O(n²)**
* **Worst Case:** **O(n²)**

---

## 2. Selection Sort

### ⚙️ Core Loop Logic
```java
for (int i = 0; i < n - 1; i++) {
    int minIdx = i;
    for (int j = i + 1; j < n; j++) {
        if (arr[j] < arr[minIdx]) {
            minIdx = j;
        }
    }
    if (minIdx != i) {
        int temp = arr[i];
        arr[i] = arr[minIdx];
        arr[minIdx] = temp;
    }
}
```

### 🧠 Loop Bound Intuition (Why these exact indices?)

1. **Why Outer Loop is `i < n - 1` (Why not `n`?):**
   * The outer loop index `i` marks the current target boundary. Everything before index `i` (`arr[0 ... i - 1]`) is already sorted and in its final place.
   * Once we place elements into indices `0, 1, 2, ..., n - 2`, there is only **1 element remaining** at index `n - 1`.
   * That last element has to be the largest element in the entire array. There are no other elements left to compare it with, so running the loop for index `n - 1` is unnecessary.

2. **Why Inner Loop starts at `j = i + 1` and runs to `n`:**
   * `arr[0 ... i - 1]` is already completely sorted. We must not touch those elements.
   * We initialize `minIdx = i`, assuming the current element at index `i` is the minimum.
   * Therefore, we only need to compare `arr[minIdx]` with the remaining unsorted elements starting from `i + 1` to `n - 1` to find if a smaller element exists.

3. **Why Swap is Outside the Inner Loop:**
   * Selection Sort does **not** swap adjacent elements while scanning.
   * The inner loop only updates an integer index (`minIdx`).
   * The actual swap happens at most **once per outer loop iteration**, directly placing the minimum element into index `i`.

---

### 📝 Step-by-Step Index Trace
Array: `arr = [64, 25, 12, 22, 11]`, `n = 5`

* **Pass `i = 0`:**
  * Assume `minIdx = 0` (value 64).
  * Scan `j = 1` to `4`: finds smallest element is `11` at index `minIdx = 4`.
  * Swap `arr[0]` (64) with `arr[4]` (11) → `[11, 25, 12, 22, 64]` *(index 0 finalized)*

* **Pass `i = 1`:**
  * Assume `minIdx = 1` (value 25).
  * Scan `j = 2` to `4`: finds smallest element is `12` at index `minIdx = 2`.
  * Swap `arr[1]` (25) with `arr[2]` (12) → `[11, 12, 25, 22, 64]` *(indices 0, 1 finalized)*

* **Pass `i = 2`:**
  * Assume `minIdx = 2` (value 25).
  * Scan `j = 3` to `4`: finds smallest element is `22` at index `minIdx = 3`.
  * Swap `arr[2]` (25) with `arr[3]` (22) → `[11, 12, 22, 25, 64]` *(indices 0, 1, 2 finalized)*

* **Pass `i = 3`:**
  * Assume `minIdx = 3` (value 25).
  * Scan `j = 4`: compare 25 with 64 → `minIdx` remains 3.
  * `minIdx == i`, so no swap needed.
  * Final Sorted Array: `[11, 12, 22, 25, 64]`

---

### 🔍 Criteria for Analysis

1. **Number of Comparisons:**
   * **Best, Average, and Worst Case:** Always `(n - 1) + (n - 2) + ... + 1 = n(n - 1)/2` = **O(n²)** comparisons.
   * It always executes every comparison because it has no mechanism to know if the remaining subarray is already sorted.
2. **Number of Swaps:**
   * **Best Case:** `0` = **O(1)** (when already sorted).
   * **Worst / Average Case:** At most `n - 1` = **O(n)** swaps.
   * **Key Strength:** It makes at most `n - 1` swaps. When writing to memory is extremely slow or wears out hardware (e.g. EEPROM / flash memory), Selection Sort minimizes memory writes better than Bubble or Insertion Sort.
3. **Adaptive:**
   * **No**. It does not adapt to pre-sorted input; it always takes O(n²) time.
4. **Stable:**
   * **No (Unstable)**. Swapping across long distances jumps an element over other equal elements.
   * *Example:* In `[4a, 4b, 2]`, swapping `4a` with `2` produces `[2, 4b, 4a]`. The relative order of `4a` and `4b` is reversed.
5. **Extra Memory:**
   * **O(1) (In-place)**. Requires only a few variables (`minIdx`, `temp`).

#### ⏱ Time Complexity Summary:
* **Best Case:** **O(n²)**
* **Average Case:** **O(n²)**
* **Worst Case:** **O(n²)**

---

## 3. Insertion Sort

### ⚙️ Core Loop Logic
```java
for (int i = 1; i < n; i++) {
    int key = arr[i];
    int j = i - 1;

    while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j--;
    }
    arr[j + 1] = key;
}
```

### 🧠 Loop Bound Intuition (Why these exact indices?)

1. **Why Outer Loop starts at `i = 1` (Why not `0`?):**
   * Insertion sort works by growing a sorted portion from left to right.
   * A subarray of length 1 (`arr[0]`) has no other elements to compare against, so **`arr[0]` is already sorted by definition**.
   * Therefore, the first element that actually needs to be inserted into a sorted portion is the element at index `1`.

2. **Why Inner Loop starts at `j = i - 1` and moves backward (`j--`):**
   * Everything to the left of index `i` (`arr[0 ... i - 1]`) is already in sorted order.
   * We want to find where `key = arr[i]` belongs inside that sorted range.
   * We start checking from the largest element in the sorted range (`arr[i - 1]`) and move backwards:
     * If `arr[j] > key`, we shift `arr[j]` one slot to the right (`arr[j + 1] = arr[j]`) to make space.
     * The moment we find `arr[j] <= key`, we stop immediately because all earlier elements are guaranteed to be smaller!

3. **Why `arr[j + 1] = key` (Why `j + 1`?):**
   * The `while` loop stops when:
     1. `j == -1`: `key` was smaller than every element in `arr[0 ... i - 1]`. Its correct position is index `0`, which is `j + 1` (`-1 + 1 = 0`).
     2. `arr[j] <= key`: we found an element smaller than or equal to `key`. Therefore, `key` belongs immediately to its right, at index `j + 1`.
   * In both cases, `j + 1` is the exact vacant slot created by shifting.

---

### 📝 Step-by-Step Index Trace
Array: `arr = [8, 3, 5, 2, 4]`, `n = 5`

* **Start:** Subarray `[8]` at index 0 is already sorted.

* **Pass `i = 1` (`key = arr[1] = 3`, `j = 0`):**
  * Check `j = 0`: `arr[0]` (8) > 3 → Shift 8 right: `arr[1] = 8` → `j` becomes `-1`.
  * Loop exits (`j >= 0` is false).
  * Insert at `arr[j + 1]` (`arr[0]`): `arr[0] = 3`.
  * Array: `[3, 8, 5, 2, 4]` *(Sorted portion: `[3, 8]`)*

* **Pass `i = 2` (`key = arr[2] = 5`, `j = 1`):**
  * Check `j = 1`: `arr[1]` (8) > 5 → Shift 8 right: `arr[2] = 8` → `j` becomes `0`.
  * Check `j = 0`: `arr[0]` (3) < 5 → Condition `arr[j] > key` fails → Loop exits.
  * Insert at `arr[j + 1]` (`arr[1]`): `arr[1] = 5`.
  * Array: `[3, 5, 8, 2, 4]` *(Sorted portion: `[3, 5, 8]`)*

* **Pass `i = 3` (`key = arr[3] = 2`, `j = 2`):**
  * Check `j = 2`: 8 > 2 → shift 8 → `j = 1`.
  * Check `j = 1`: 5 > 2 → shift 5 → `j = 0`.
  * Check `j = 0`: 3 > 2 → shift 3 → `j = -1`.
  * Loop exits. Insert at `arr[0] = 2`.
  * Array: `[2, 3, 5, 8, 4]` *(Sorted portion: `[2, 3, 5, 8]`)*

* **Pass `i = 4` (`key = arr[4] = 4`, `j = 3`):**
  * Check `j = 3`: 8 > 4 → shift 8 → `j = 2`.
  * Check `j = 2`: 5 > 4 → shift 5 → `j = 1`.
  * Check `j = 1`: 3 < 4 → Stop!
  * Insert at `arr[j + 1]` (`arr[2]`): `arr[2] = 4`.
  * Final Sorted Array: `[2, 3, 4, 5, 8]`

---

### 🔍 Criteria for Analysis

1. **Number of Comparisons:**
   * **Best Case:** `n - 1` = **O(n)** (when already sorted; each element compares once with `arr[i - 1]` and immediately exits the `while` loop).
   * **Worst Case:** `n(n - 1)/2` = **O(n²)** (when reverse sorted; each element compares against every preceding element).
   * **Average Case:** `≈ n(n - 1)/4` = **O(n²)**.
2. **Number of Shifts:**
   * **Best Case:** `0` shifts = **O(1)** (when already sorted).
   * **Worst Case:** `n(n - 1)/2` shifts = **O(n²)**.
   * **Average Case:** `≈ n(n - 1)/4` shifts = **O(n²)**.
3. **Adaptive:**
   * **Yes (Naturally Adaptive)**. If the array is already sorted, the `while` condition `arr[j] > key` evaluates to false on the very first comparison for every `i`. It runs in linear time **O(n)** without needing any extra boolean flags.
4. **Stable:**
   * **Yes**. The shifting condition is strictly `arr[j] > key`. If an element is equal to `key`, the shift stops and `key` is placed after it, preserving their original order.
5. **Extra Memory:**
   * **O(1) (In-place)**. Only uses a single variable `key`.

#### ⏱ Time Complexity Summary:
* **Best Case:** **O(n)** (Array is already sorted or nearly sorted)
* **Average Case:** **O(n²)**
* **Worst Case:** **O(n²)** (Array is reverse sorted)

---

## 📊 Summary Comparison: The 5 Criteria for Analysis

| Criteria for Analysis | Bubble Sort | Selection Sort | Insertion Sort |
| :--- | :--- | :--- | :--- |
| **1. Number of Comparisons** | **Best:** O(n)<br>**Worst/Avg:** O(n²) | **Best/Worst/Avg:** Always O(n²) | **Best:** O(n)<br>**Worst/Avg:** O(n²) |
| **2. Number of Swaps / Shifts** | **Best:** 0<br>**Worst/Avg:** O(n²) swaps | **Best:** 0<br>**Worst/Avg:** At most n - 1 swaps (O(n)) | **Best:** 0<br>**Worst/Avg:** O(n²) shifts |
| **3. Adaptive?** | **Yes** *(with `swapped` flag)* | **No** *(always O(n²))* | **Yes** *(naturally O(n) on sorted)* |
| **4. Stable?** | **Yes** | **No** *(long-distance swaps break order)* | **Yes** |
| **5. Extra Memory** | O(1) *(In-place)* | O(1) *(In-place)* | O(1) *(In-place)* |
