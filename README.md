# Elementary Sorting Algorithms

This guide explains the three fundamental elementary comparison-based sorting algorithms: **Bubble Sort**, **Selection Sort**, and **Insertion Sort**.

Each algorithm is explained in simple and intuitive terms with a step-by-step trace and an evaluation based on the **5 Criteria for Analysis**:
1. **Number of Comparisons**
2. **Number of Swaps**
3. **Adaptive**
4. **Stable**
5. **Extra Memory**

---

## 1. Bubble Sort

### 💡 Core Intuition
Think of air bubbles rising to the surface of water because they are lighter, while heavy stones sink to the bottom.

* In an array, we compare two adjacent neighbors side-by-side (`arr[j]` and `arr[j + 1]`).
* If the left element is greater than the right element, we **swap** them.
* By the end of the first pass, the largest element will have "bubbled up" to its final position at the very end of the array.
* We repeat this for the remaining unsorted portion.
* **Optimization (`swapped` flag):** If we go through an entire pass without making a single swap, we know the array is already sorted and we can stop immediately!

---

### 📝 Step-by-Step Example
Let the array be: `[5, 1, 4, 2, 8]`

* **Pass 1:**
  * Compare `(5, 1)` $\rightarrow$ $5 > 1$ $\rightarrow$ **Swap** $\rightarrow$ `[1, 5, 4, 2, 8]`
  * Compare `(5, 4)` $\rightarrow$ $5 > 4$ $\rightarrow$ **Swap** $\rightarrow$ `[1, 4, 5, 2, 8]`
  * Compare `(5, 2)` $\rightarrow$ $5 > 2$ $\rightarrow$ **Swap** $\rightarrow$ `[1, 4, 2, 5, 8]`
  * Compare `(5, 8)` $\rightarrow$ $5 < 8$ $\rightarrow$ No swap $\rightarrow$ `[1, 4, 2, 5, 8]`  
    *(**8** is now in its correct final position at the end)*

* **Pass 2:**
  * Compare `(1, 4)` $\rightarrow$ No swap $\rightarrow$ `[1, 4, 2, 5, 8]`
  * Compare `(4, 2)` $\rightarrow$ $4 > 2$ $\rightarrow$ **Swap** $\rightarrow$ `[1, 2, 4, 5, 8]`
  * Compare `(4, 5)` $\rightarrow$ No swap $\rightarrow$ `[1, 2, 4, 5, 8]`  
    *(**5** is now in its correct position)*

* **Pass 3:**
  * Compare `(1, 2)` $\rightarrow$ No swap $\rightarrow$ `[1, 2, 4, 5, 8]`
  * Compare `(2, 4)` $\rightarrow$ No swap $\rightarrow$ `[1, 2, 4, 5, 8]`  
    *(Zero swaps occurred in this pass $\rightarrow$ **Early stop triggers!** Array is fully sorted)*

---

### 🔍 Criteria for Analysis

1. **Number of Comparisons:**
   * **Best Case:** $n - 1 \implies \mathcal{O}(n)$ comparisons (when already sorted; one pass confirms no swaps).
   * **Worst / Average Case:** $(n - 1) + (n - 2) + \dots + 1 = \frac{n(n - 1)}{2} \implies \mathcal{O}(n^2)$ comparisons.
2. **Number of Swaps:**
   * **Best Case:** $0 \implies \mathcal{O}(1)$ swaps (when already sorted).
   * **Worst Case:** $\frac{n(n - 1)}{2} \implies \mathcal{O}(n^2)$ swaps (when reverse sorted).
   * **Average Case:** $\approx \frac{n(n - 1)}{4} \implies \mathcal{O}(n^2)$ swaps.
3. **Adaptive:**
   * **Yes** (when using the `swapped` flag optimization). If the array is already sorted or nearly sorted, the algorithm adapts and finishes in $\mathcal{O}(n)$ time.
4. **Stable:**
   * **Yes**. We only swap if $arr[j] > arr[j + 1]$. If two adjacent elements are equal ($arr[j] == arr[j + 1]$), we do not swap them, preserving their original relative order.
5. **Extra Memory:**
   * **$\mathcal{O}(1)$ (In-place)**. Requires only a constant amount of memory for variables (`temp`, `swapped`).

#### ⏱ Time Complexity Summary:
* **Best Case:** $\mathcal{O}(n)$ (Array is already sorted)
* **Average Case:** $\mathcal{O}(n^2)$
* **Worst Case:** $\mathcal{O}(n^2)$ (Array is reverse sorted)

---

## 2. Selection Sort

### 💡 Core Intuition
Imagine you have a group of people standing in a line and you want to order them by height from shortest to tallest.

* You scan the entire group from left to right to find the **shortest** person.
* You then **swap** that shortest person with whoever is standing at the 1st position.
* Next, you scan the remaining unsorted group, find the shortest among them, and swap them with whoever is standing at the 2nd position.
* You repeat this process, each time **selecting** the minimum element from the unsorted portion and placing it at the front of the unsorted section.

---

### 📝 Step-by-Step Example
Let the array be: `[64, 25, 12, 22, 11]`

* **Pass 1:**
  * Find the minimum element in `[64, 25, 12, 22, 11]` $\rightarrow$ minimum is **11** (at index 4).
  * Swap `11` with the element at index 0 (`64`).
  * Array becomes: `[11, 25, 12, 22, 64]` *(11 is sorted)*

* **Pass 2:**
  * Find the minimum element in remaining `[25, 12, 22, 64]` $\rightarrow$ minimum is **12** (at index 2).
  * Swap `12` with the element at index 1 (`25`).
  * Array becomes: `[11, 12, 25, 22, 64]` *(11, 12 are sorted)*

* **Pass 3:**
  * Find the minimum element in remaining `[25, 22, 64]` $\rightarrow$ minimum is **22** (at index 3).
  * Swap `22` with the element at index 2 (`25`).
  * Array becomes: `[11, 12, 22, 25, 64]` *(11, 12, 22 are sorted)*

* **Pass 4:**
  * Find the minimum element in remaining `[25, 64]` $\rightarrow$ minimum is **25** (at index 3).
  * Swap with itself (already in place).
  * Final Sorted Array: `[11, 12, 22, 25, 64]`

---

### 🔍 Criteria for Analysis

1. **Number of Comparisons:**
   * **Best, Average, and Worst Case:** Always $\frac{n(n - 1)}{2} \implies \mathcal{O}(n^2)$ comparisons.
   * Selection sort must always scan the entire unsorted subarray to find the minimum, even if the array is already sorted.
2. **Number of Swaps:**
   * **Best Case:** $0 \implies \mathcal{O}(1)$ swaps (when already sorted).
   * **Worst / Average Case:** At most $n - 1 \implies \mathcal{O}(n)$ swaps.
   * **Key Advantage:** Selection Sort minimizes the total number of swaps ($\le n - 1$). This makes it useful when memory write operations are significantly more expensive than reads (e.g., flash memory or EEPROM).
3. **Adaptive:**
   * **No**. Selection Sort is not adaptive. It takes the same $\mathcal{O}(n^2)$ time even if the array is already completely sorted because it blindly scans for the minimum element in every pass.
4. **Stable:**
   * **No (Unstable)**. Because it swaps elements over long distances, it can change the relative order of identical elements.
   * *Counter-example showing instability:* Consider `[4a, 4b, 2]`.
     * The minimum element is `2`. Selection sort swaps `4a` with `2`.
     * The array becomes `[2, 4b, 4a]`.
     * Notice `4b` is now before `4a`! The relative order of duplicate values was not preserved.
5. **Extra Memory:**
   * **$\mathcal{O}(1)$ (In-place)**. Requires only a few variables (`minIdx`, `temp`).

#### ⏱ Time Complexity Summary:
* **Best Case:** $\mathcal{O}(n^2)$
* **Average Case:** $\mathcal{O}(n^2)$
* **Worst Case:** $\mathcal{O}(n^2)$

---

## 3. Insertion Sort

### 💡 Core Intuition
Think of sorting a hand of playing cards as you are dealt cards one by one.

* When you start, your hand has just **1 card** (the very first element). A single card is always sorted by definition!
* When you pick up the next card (the **key**), you want to **insert** it into its correct position among the cards already sorted in your hand.
* You compare the new card backwards with the cards in your sorted hand:
  * Any card that is **greater** than your new card is shifted one position to the right to make room.
  * As soon as you find a card that is **smaller or equal**, you stop and insert the new card into the vacated spot.
* You repeat this until every element in the array has been inserted into the sorted portion.

---

### 📝 Step-by-Step Example
Let the array be: `[8, 3, 5, 2, 4]`

* Initial state: `[8]` is considered the sorted sub-array.

* **Step 1 (Insert 3):**
  * Key = `3`. Compare with `8`.
  * $8 > 3$ $\rightarrow$ Shift `8` right $\rightarrow$ Insert `3` at index 0.
  * Array becomes: `[3, 8, 5, 2, 4]` *(Sorted portion: `[3, 8]`)*

* **Step 2 (Insert 5):**
  * Key = `5`. Compare with `8`.
  * $8 > 5$ $\rightarrow$ Shift `8` right.
  * Compare with `3` $\rightarrow$ $3 < 5$ $\rightarrow$ Stop! Insert `5` at index 1.
  * Array becomes: `[3, 5, 8, 2, 4]` *(Sorted portion: `[3, 5, 8]`)*

* **Step 3 (Insert 2):**
  * Key = `2`. Compare with `8` (shift), `5` (shift), `3` (shift).
  * Insert `2` at index 0.
  * Array becomes: `[2, 3, 5, 8, 4]` *(Sorted portion: `[2, 3, 5, 8]`)*

* **Step 4 (Insert 4):**
  * Key = `4`. Compare with `8` (shift), `5` (shift).
  * Compare with `3` $\rightarrow$ $3 < 4$ $\rightarrow$ Stop! Insert `4` at index 2.
  * Final Sorted Array: `[2, 3, 4, 5, 8]`

---

### 🔍 Criteria for Analysis

1. **Number of Comparisons:**
   * **Best Case:** $n - 1 \implies \mathcal{O}(n)$ comparisons (when already sorted; each element is compared once with the element to its left and immediately stops).
   * **Worst Case:** $\frac{n(n - 1)}{2} \implies \mathcal{O}(n^2)$ comparisons (when reverse sorted; each element must compare with every preceding element).
   * **Average Case:** $\approx \frac{n(n - 1)}{4} \implies \mathcal{O}(n^2)$ comparisons.
2. **Number of Swaps / Shifts:**
   * **Best Case:** $0$ shifts $\implies \mathcal{O}(1)$ (when already sorted).
   * **Worst Case:** $\frac{n(n - 1)}{2}$ shifts $\implies \mathcal{O}(n^2)$ (when reverse sorted).
   * **Average Case:** $\approx \frac{n(n - 1)}{4}$ shifts $\implies \mathcal{O}(n^2)$.
3. **Adaptive:**
   * **Yes (Naturally Adaptive)**. Insertion Sort is naturally adaptive without needing extra flags. If the array is already sorted or nearly sorted, it runs in linear time $\mathcal{O}(n)$. In general, its time is proportional to $\mathcal{O}(n + d)$, where $d$ is the number of inversions.
4. **Stable:**
   * **Yes**. When sliding elements to the right, we only slide elements that are strictly greater than the `key` (`arr[j] > key`). Elements that are equal to the key are never shifted past it, preserving their original order.
5. **Extra Memory:**
   * **$\mathcal{O}(1)$ (In-place)**. Requires only a single temporary variable `key`.

#### ⏱ Time Complexity Summary:
* **Best Case:** $\mathcal{O}(n)$ (Array is already sorted or nearly sorted)
* **Average Case:** $\mathcal{O}(n^2)$
* **Worst Case:** $\mathcal{O}(n^2)$ (Array is reverse sorted)

---

## 📊 Summary Comparison: The 5 Criteria for Analysis

| Criteria for Analysis | Bubble Sort | Selection Sort | Insertion Sort |
| :--- | :--- | :--- | :--- |
| **1. Number of Comparisons** | **Best:** $\mathcal{O}(n)$<br>**Worst/Avg:** $\mathcal{O}(n^2)$ | **Best/Worst/Avg:** Always $\mathcal{O}(n^2)$ | **Best:** $\mathcal{O}(n)$<br>**Worst/Avg:** $\mathcal{O}(n^2)$ |
| **2. Number of Swaps / Shifts** | **Best:** $0$<br>**Worst/Avg:** $\mathcal{O}(n^2)$ swaps | **Best:** $0$<br>**Worst/Avg:** At most $n - 1$ swaps ($\mathcal{O}(n)$) | **Best:** $0$<br>**Worst/Avg:** $\mathcal{O}(n^2)$ shifts |
| **3. Adaptive?** | **Yes** *(with `swapped` flag)* | **No** *(always $\mathcal{O}(n^2)$)* | **Yes** *(naturally $\mathcal{O}(n)$ on sorted)* |
| **4. Stable?** | **Yes** | **No** *(long-distance swaps break order)* | **Yes** |
| **5. Extra Memory** | $\mathcal{O}(1)$ *(In-place)* | $\mathcal{O}(1)$ *(In-place)* | $\mathcal{O}(1)$ *(In-place)* |
