# Elementary Sorting Algorithms

---

## 1. Bubble Sort

### 💡 Core Intuition
Scan left to right, comparing adjacent pairs. If `arr[j] > arr[j + 1]`, swap them. This pushes the **largest unsorted element** to the end after each pass. The sorted section grows from the right. If a full pass makes zero swaps, the array is already sorted — stop early.

### 📋 Algorithm
1. For `i` from `0` to `n - 1` (`i < n - 1`):
   * Set `swapped = false`.
   * For `j` from `0` to `n - 1 - i` (`j < n - 1 - i`):
     * If `arr[j] > arr[j + 1]`, swap them, set `swapped = true`.
   * If `swapped == false`, break.

### ⚙️ Code
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

### 🧠 Why These Loop Bounds?
* **`i < n - 1`:** After placing `n - 1` elements in their final positions, the last remaining element at index `0` is automatically correct. No need to run a pass for it.
* **`j < n - 1 - i`:**
  * `- 1` → We access `arr[j + 1]`. If `j` reached `n - 1`, `arr[n]` would be out of bounds.
  * `- i` → After `i` passes, the last `i` elements are already sorted. Skip them.
* **`swapped` flag:** If a full pass has zero swaps, every pair satisfies `arr[j] <= arr[j + 1]` — the array is sorted. Exit in O(n).

### 📝 Trace: `[5, 1, 4, 2, 8]`
* **i=0** (j < 4): swap(5,1), swap(5,4), swap(5,2), skip(5,8) → `[1, 4, 2, 5, 8]` — 8 locked
* **i=1** (j < 3): skip(1,4), swap(4,2), skip(4,5) → `[1, 2, 4, 5, 8]` — 5 locked
* **i=2** (j < 2): skip(1,2), skip(2,4) → no swaps → **break** ✅

### 🔍 Criteria for Analysis

| Criteria | Details |
| :--- | :--- |
| **Comparisons** | Best: `n - 1` = **O(n)**. Worst/Avg: `n(n-1)/2` = **O(n²)** |
| **Swaps** | Best: `0`. Worst: `n(n-1)/2` = **O(n²)** |
| **Adaptive** | **Yes** — `swapped` flag gives O(n) on sorted input |
| **Stable** | **Yes** — only swaps when `arr[j] > arr[j+1]`, equals are never swapped |
| **Extra Memory** | **O(1)** In-place |

---

## 2. Selection Sort

### 💡 Core Intuition
Divide the array into a **sorted part** (left, initially empty) and an **unsorted part** (right, initially the full array). Each pass scans the unsorted part to find the **minimum element**, then swaps it into position `i` — the first slot of the unsorted part. One swap per pass, at most `n - 1` swaps total.

### 📋 Algorithm
1. For `i` from `0` to `n - 1` (`i < n - 1`):
   * Set `minIdx = i`.
   * For `j` from `i + 1` to `n` (`j < n`):
     * If `arr[j] < arr[minIdx]`, update `minIdx = j`.
   * If `minIdx != i`, swap `arr[i]` with `arr[minIdx]`.

### ⚙️ Code
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

### 🧠 Why These Loop Bounds?
* **`i < n - 1`:** After filling indices `0` through `n - 2`, only 1 element remains at `n - 1`. It must be the largest — nothing left to compare or swap.
* **`j = i + 1` to `n`:** Elements before `i` are finalized. We set `minIdx = i` as the initial guess, so we only need to check `i + 1` onward for anything smaller.
* **Swap outside inner loop:** The inner loop only tracks `minIdx` (an integer). The actual swap happens once per pass — this is why Selection Sort has minimal writes.

### 📝 Trace: `[64, 25, 12, 22, 11]`
* **i=0:** min is 11 at index 4 → swap(64, 11) → `[11, 25, 12, 22, 64]`
* **i=1:** min is 12 at index 2 → swap(25, 12) → `[11, 12, 25, 22, 64]`
* **i=2:** min is 22 at index 3 → swap(25, 22) → `[11, 12, 22, 25, 64]`
* **i=3:** min is 25 at index 3 → `minIdx == i` → no swap ✅

### 🔍 Criteria for Analysis

| Criteria | Details |
| :--- | :--- |
| **Comparisons** | Best/Avg/Worst: Always `n(n-1)/2` = **O(n²)** — must scan to find min every time |
| **Swaps** | Best: `0`. Worst: `n - 1` = **O(n)** — fewest swaps of any elementary sort |
| **Adaptive** | **No** — always O(n²) even on sorted input |
| **Stable** | **No** — long-distance swap can reorder equals (e.g. `[4a, 4b, 2]` → `[2, 4b, 4a]`) |
| **Extra Memory** | **O(1)** In-place |

---

## 3. Insertion Sort

### 💡 Core Intuition
Consider `arr[0]` as already sorted (a single element is trivially sorted). For each next element `key = arr[i]`, scan the sorted portion `arr[0 ... i-1]` **backwards**. Shift every element greater than `key` one position to the right to make room. When you find an element ≤ `key` (or reach the front), drop `key` into the vacant slot. The sorted portion grows by one each pass.

### 📋 Algorithm
1. For `i` from `1` to `n - 1` (`i < n`):
   * Store `key = arr[i]`.
   * Set `j = i - 1`.
   * While `j >= 0` and `arr[j] > key`:
     * Shift: `arr[j + 1] = arr[j]`, then `j--`.
   * Place: `arr[j + 1] = key`.

### ⚙️ Code
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

### 🧠 Why These Loop Bounds?
* **`i = 1` (not `0`):** `arr[0]` alone is already sorted. The first element that needs inserting is at index `1`.
* **`j = i - 1`, moving backward (`j--`):** We compare `key` against sorted elements from right to left. If `arr[j] > key`, shift `arr[j]` right. If `arr[j] <= key`, stop — everything to the left is guaranteed smaller. This early exit gives O(n) best case.
* **`arr[j + 1] = key` (why `j + 1`?):**
  * If `j == -1`: `key` is the smallest — insert at index `0` = `j + 1`.
  * If `arr[j] <= key`: insert right after `j` = `j + 1`.
  * Either way, `j + 1` is the exact vacant slot created by shifting.

### 📝 Trace: `[8, 3, 5, 2, 4]`
* **i=1** key=3, j=0: 8>3 shift → j=-1 → insert at 0 → `[3, 8, 5, 2, 4]`
* **i=2** key=5, j=1: 8>5 shift → j=0: 3<5 stop → insert at 1 → `[3, 5, 8, 2, 4]`
* **i=3** key=2, j=2: 8>2 shift → j=1: 5>2 shift → j=0: 3>2 shift → j=-1 → insert at 0 → `[2, 3, 5, 8, 4]`
* **i=4** key=4, j=3: 8>4 shift → j=2: 5>4 shift → j=1: 3<4 stop → insert at 2 → `[2, 3, 4, 5, 8]` ✅

### 🔍 Criteria for Analysis

| Criteria | Details |
| :--- | :--- |
| **Comparisons** | Best: `n - 1` = **O(n)**. Worst/Avg: `n(n-1)/2` = **O(n²)** |
| **Shifts** | Best: `0`. Worst: `n(n-1)/2` = **O(n²)** |
| **Adaptive** | **Yes (naturally)** — `while` condition fails immediately on sorted input → O(n) without any flag |
| **Stable** | **Yes** — shifts only when `arr[j] > key` (strict), equals are never moved past each other |
| **Extra Memory** | **O(1)** In-place |

---

## 📊 Summary Comparison

| Criteria | Bubble Sort | Selection Sort | Insertion Sort |
| :--- | :--- | :--- | :--- |
| **Comparisons** | Best: O(n), Worst: O(n²) | Always O(n²) | Best: O(n), Worst: O(n²) |
| **Swaps / Shifts** | Best: 0, Worst: O(n²) | Best: 0, Worst: O(n) swaps | Best: 0, Worst: O(n²) |
| **Adaptive?** | Yes (swapped flag) | No | Yes (naturally) |
| **Stable?** | Yes | No | Yes |
| **Extra Memory** | O(1) | O(1) | O(1) |
