import java.util.Arrays;
import java.util.Random;

public class Sorts {
    /*bubble sort two nested for-loops
    means time complexity is O(n^2), number of comparisons and swaps O(n^2)
    if the list is already sorted time complexity is O(n)
    Bubble Sort by nature is not adaptive, however by adding
    the swappedSomething, we have made bubble sort adaptive
    Bubble sort is both stable and adaptive
     */
    void BubbleSort(int A[]){
        boolean swappedSomething = true;
        while (swappedSomething){
            swappedSomething = false;
            for(int i = 0; i < A.length-1; i++){
                //minus i also because in every pass one comparison should reduce
                if(A[i] > A[i+1]){
                    swappedSomething = true;
                    //swap(A[i], A[i+1]);
                    int temp = A[i];
                    A[i] = A[i+1];
                    A[i+1] = temp;
                }
            }
        }
    }

    /*Selection sort
    Selection sort is a comparison-based sorting algorithm that works by repeatedly
    finding the smallest element from the unsorted part of an array and moving it to the beginning.
    Time Complexity: O(n^2)
    Stability: It is generally not stable, meaning it may change the relative
    order of elements with equal values.
    Best Use Case: Useful for small datasets or in memory-constrained environments
    where minimal swaps are preferred.
     */
    public void SelectionSort(int A[]){
        int n = A.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++){
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++){
                if(A[j] < A[min_idx]){
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = A[min_idx];
            A[min_idx] = A[i];
            A[i] = temp;
        }
    }

    /*insertion sort
    time complexity is O(n^2) number of comparisons and swaps O(n^2)
    Insertion sort is designed for linked lists
    Insertion sort is both stable and adaptive
     */
    void InsertionSort(int A[]) {
        // Outer loop starts from the second element (index 1)
        for (int i = 1; i < A.length; i++) {
            int key = A[i]; //The element to be inserted
            int j = i - 1;
            /* Move elements of arr[0...i-1] that are greater than key
            to one position ahead of their current position */
            while (j > -1 && A[j] > key) {
                A[j + 1] = A[j];
                j--;
            }
            // Place the key in its correct position
            A[j + 1] = key;
        }
    }

    /*Shell Sort
    The Shell Sort algorithm is an optimization of Insertion Sort that allows for the exchange
    of elements far apart by using a "gap" sequence.
    Gap Sequence: The performance of Shell Sort depends heavily on the increment sequence used.
    While n/2 is simple, sequences like Knuth's(1, 4, 13) often provide better performance.
    In-Place Sort: This algorithm is an in-place sort meaning it sorts the array without requiring extra storage space
    Time Complexity: The worst-case time complexity is generally O(n^2) for Shell's original sequence,
    but can be improved to O(nlog^2n) or better with optimized gap sequences.
    Stability: Shell Sort is not a stable sorting algorithm, meaning it may change the relative order of elements with equal values
     */
    void ShellSort(int A[]) {
        int n = A.length;

        // Start with a large gap, then reduce the gap
        // Using Shell's original sequence: n/2, n/4, ... 1
        for (int gap = n / 2; gap > 0; gap /= 2) {

            // Perform a gapped insertion sort for this gap size.
            // The first gap elements arr[0..gap-1] are already in gapped order
            // keep adding one more element until the entire array is gap sorted
            for (int i = gap; i < n; i++) {
                // Add arr[i] to the elements that have been gap sorted
                // save arr[i] in temp and make a hole at position i
                int temp = A[i];
                int j;

                // shift earlier gap-sorted elements up until the correct
                // location for arr[i] is found
                for (j = i; j >= gap && A[j - gap] > temp; j -= gap) {
                    A[j] = A[j - gap];
                }

                // put temp (the original arr[i]) in its correct location
                A[j] = temp;
            }
        }
    }

    /*Merge Sort
    Merging: The process of combining two sorted lists into a single sorted list. These lists can be
    in an array or a linked list. Make a pointer for each list like i and j. Compare i with j in their respective
    lists and depending on which is order, input them into the third list(the final single list) and have a pointer k
    for the third list. By doing so the elements from the first two lists will be ordered correctly in the last list.
    If one list has finished before the other one, simply look at the other list and see which elements are left. Because
    the lists are already sorted, simply add the remaining elements from that list into the third list and everything will work.
    The time complexity of merging is O(m+n) m and n being the number of elements in both lists.
    Merge Sort takes time complexity O(nlogn)
    Pros of MergeSort:
    1. Large size List
    2. Linked List: Two linked lists can be merged without creating a third linked list simply by attaching the nodes
    behind each other making a singly linked list.
    3. External Sorting
    4. Stable
    Cons of MergeSort:
    1. Extra space (not in place sort like Quicksort, heapsort, insertion sort, selection sort, shell sort, bubble sort)
    2. No small problem (for small sized lists it is much slower)
    3. Recursive
     */
    void MergeSort(int A[]){
        int inputLength = A.length;
        if(inputLength < 2){
            return;
        }
        int midIndex = inputLength / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];

        for (int i = 0; i < midIndex; i++){
            leftHalf[i] = A[i];
        }
        for (int i = midIndex; i < inputLength; i++){
            rightHalf[i - midIndex] = A[i];
        }
        MergeSort(leftHalf);
        MergeSort(rightHalf);
        //Merge
        Merge(A, leftHalf, rightHalf);
    }
    void Merge(int A[], int[] leftHalf, int[] rightHalf){
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize){
            if (leftHalf[i] <= rightHalf[j]){
                A[k] = leftHalf[i];
                i++;
            }
            else{
                A[k] = rightHalf[j];
                j++;
            }
            k++;
        }
        while (i < leftSize){
            A[k] = leftHalf[i];
            i++;
            k++;
        }
        while (j < rightSize){
            A[k] = rightHalf[j];
            j++;
            k++;
        }
    }

    /*Radix Sort
    It typically uses Counting Sort as a stable subroutine to sort digits from
    the Least Significant Digit (LSD) to the Most Significant Digit (MSD).
    Time Complexity: O(d * (n+b)), where n is the number of elements, d is the number
    of digits in the maximum value, and b is the base(usually 10).
    Stability: It is a stable sort
     */
    public void RadixSort(int A[]) {
        if (A == null || A.length == 0){
            return;
        }

        // 1. Find the maximum value to determine number of digits
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > max){
                max = A[i];
            }
        }

        // 2. Iterate through each digit place (exp = 1, 10, 100...)
        // The loop continues until the largest number's highest digit is processed
        for (int exp = 1; max / exp > 0; exp *= 10) {

            // Integrated Stable Counting Sort Logic:
            int n = A.length;
            int[] output = new int[n];
            int[] count = new int[10];

            // Store occurrences of each digit in count[]
            for (int i = 0; i < n; i++) {
                int digit = (A[i] / exp) % 10;
                count[digit]++;
            }

            // Change count[i] so it contains actual position of this digit in output[]
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            // Build the output array (traverse backwards to maintain stability)
            for (int i = n - 1; i >= 0; i--) {
                int digit = (A[i] / exp) % 10;
                output[count[digit] - 1] = A[i];
                count[digit]--;
            }

            // Copy the sorted values for this digit back into the original array
            for (int i = 0; i < n; i++) {
                A[i] = output[i];
            }
        }
    }

    /*Things to know for heap sort:
    1.Array Representation of Binary Tree, 2.Complete Binary Tree, 3.Heap, 4.Insert & Delete, 5.Heap Sort, 6.Heapify, 7.Priority Queue
    Inserting in a heap takes minimum O(1) maximum O(log n). Insert from the leaf and compare with the parent, time is height
    Deleting in a heap requires removing from the root(max), then bringing up the very last element in the heap to become the new root
    Compare the children of the root, the bigger child is then compared with the parent and if bigger, takes the place of the new root, now
    compare the children of the bigger child, if the bigger child is not larger than the parent, leave it as is. Deletion takes log(n)
    Max for max heap, min for min heap. The element removed from the heap still remains in the array but the heap simply does not contain the element.
    This is the idea behind the heap sort. The elements "removed" are actually left in the array in a increasing order, in max heap.
    For heap sort there are two steps: Insert all elements into the heap, then delete all the elements from the heap and store them
    in the free space in the array that we obtain by deleting the element. The end result is a sorted array.
    Height of binary tree is: log n. There are n elements. Therefore, time complexity of heap sort is n(log n).
    Creating and Deleting heap in heap sort takes time complexity is O(nlogn).
    Heapify: Is it possible to change the direction of the adjusting in a heap sort? Rather than comparing bottom to top, let's compare top to bottom.
    By looking downwards, check if it is a max heap. If not, check if the parent has children and if the parent is less than the child,
    then child goes up, parent goes down. Look downwards again and repeat until you reach root. By then you should have a max heap.
    This procedure is known as heapify. The only difference is the direction. Time complexity of heapify is O(n). Heapify is faster than Creating heap.
    Priority Queue: Not strictly FIFO, elements have priority and are inserted and deleted based on priority.
    When we want to delete from queue we want to always delete the highest priority element from the queue, that should be deleted first.
    Elements are inserted with priority, and deleted by higher priority. In a min heap, the smaller number has higher priority.
    In a max heap, the larger number has higher priority. Heap is the best data structure for implementing priority queue(runs in O(logn) > O(n)).

    */
    void HeapSort(int A[]){
        //Step1: Build a max heap (rearrange array)
        //Start from the last non-leaf node and heapify each node
        for (int i = A.length/2 - 1; i >= 0; i--){
            heapify(A, A.length, i);
        }
        //Step 2: Extract elements from the heap one by one
        for (int i = A.length - 1; i > 0; i--){
            //Move current root to the end of the array
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            //Re-heapify the reduced heap (excluding sorted elements)
            heapify(A, i, 0);
        }
    }
    //Function to maintain the max-heap property
    void heapify(int A[], int n, int i){
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child index
        int right = 2 * i + 2; // right child index

        // If left child is larger than root
        if (left < n && A[left] > A[largest]){
            largest = left;
        }

        // If right child is larger than the current largest
        if (right < n && A[right] > A[largest]){
            largest = right;
        }

        // If largest is not the root, swap and continue heapifying
        if (largest != i){
            int swap = A[i];
            A[i] = A[largest];
            A[largest] = swap;

            // Recursively heapify the affected subtree
            heapify(A, n, largest);
        }
    }

    /*QuickSort
    The idea behind quicksort for elements is that an element is in its sorted position if all the
    elements on the left hand side are smaller than the element and all the elements on the right hand side
    are larger than the element. Rest of the elements may or may not be sorted.
    Quicksort is a divide and conquer algorithm. This means it is a problem-solving paradigm that breaks a
    complex problem into smaller, manageable sub-problems, solves them recursively, and combines their solutions
    to solve the original problem. It follows three main steps: Divide, Conquer, and Combine. Efficiently tackling sorting, searching, etc.
    The Pivot: The pivot is used in the quicksort as the element used as an indicator for all the other elements. The other elements
    will rearrange themselves around the pivot, the greater elements being on the right and the lesser elements being on the left.
    Worst Case time of quicksort is O(n^2). Average time is O(nlogn). The worst case for quicksort is if the list is already sorted.
    Two suggestions for removing the worst case for quicksort:
    1. Select middle element as pivot
    2. Select random element as pivot
    Stack size for quicksort can range from logn to n
     */
    void QuickSort(int A[], int low, int high){ // Main function to sort the array
        if (low < high){
            // pi is the partitioning index, A[pi] is now at the correct place
            int pi = partition(A, low, high);

            //Recursively sort elements before and after partition
            QuickSort(A, low, pi - 1);
            QuickSort(A, pi + 1, high);
        }
    }

    /* This function takes the last element as pivot, places the pivot element at its
    correct position in sorted array, and places all smaller elements to the left
     */
    int partition(int A[], int low, int high){
        int pivot = A[high];
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++){
            // If current element is smaller than the pivot
            if(A[j] < pivot){
                i++;
                // Swap A[i] and A[j]
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }

        // Swap the pivot element with the element at i + 1
        int temp = A[i + 1];
        A[i + 1] = A[high];
        A[high] = temp;
        return i + 1;
    }

    public void main(String[] args){
        int[] numbers = new int[1000000];
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++){
            numbers[i] = random.nextInt(1000000);
        }
        // before sort
        System.out.println(Arrays.toString(numbers));
        long startTime = System.currentTimeMillis();

        // All times from test with 1000000 elements
        // 8. BubbleSort(numbers); // Took 1694208 ms
        // 7. SelectionSort(numbers); // Took 345779 ms
        // 6. InsertionSort(numbers); // Took 70174 ms
        // 5. ShellSort(numbers); // Took 189 ms
        // 4. MergeSort(numbers); // Took 143 ms
        // 3. HeapSort(numbers); // Took 125 ms
        // 2. QuickSort(numbers, 0, numbers.length - 1); // Took 98 ms
        // 1. RadixSort(numbers); // Took 45 ms

        //after sort
        long endTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(numbers));
        System.out.println("Took " + (endTime - startTime) + " ms");
    }
}