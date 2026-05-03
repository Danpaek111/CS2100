import java.util.ArrayList;
/* Author: Briana Morrison, Hunter Platt */

public class SortingAlgorithms {

	/*
	 * Swaps the elements and indices i and j in list
	 * */
	private static <T> void swap(ArrayList<T> list, int i, int j) {
		if (i < 0 || i >= list.size())
			return;
		if (j < 0 || j >= list.size())
			return;
		T temp = list.get(i);
		list.set(i,list.get(j));
		list.set(j, temp);
	}
	
	// ####################
	// ## INSERTION SORT ## ----------------------------------------------------------------------
	// ####################
	// ## IMPORTANT: the code we've given you below has a small bug!
	// ##   You will need to look at this code and/or test, and fix the bug.
	// ####################
	/**
	 * Usually a slow sorting algorithm. Insertion sort. 
	 * @param list - An array of items
	 */
	public static <T extends Comparable<T>> void insertionSort(ArrayList<T> list) {
		for (int i = 1; i < list.size(); i++) {
			T val = list.get(i);
			int j = i - 1;
			while (j >= 0 && val.compareTo(list.get(j)) < 0) { //Smallest element can't move into index 0 so >=
				list.set(j+1, list.get(j));
				j--;
			}
			list.set(j+1, val);
		}
	}

	//=================================================================================


	// ################
	// ## MERGE SORT ## ----------------------------------------------------------------------
	// ################	
	/**
	 * Fully recursive Merge sort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * 
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void mergeSort(ArrayList<T> list) {
		mergeSort(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void mergeSort(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		if (i >= j){ //base case 0 or 1 element
			return;
		}
		int mid = (i + j) / 2;
		mergeSort(list, i, mid);
		mergeSort(list, mid+1, j);
		merge(list, i, mid, j);
	}
	
	/**
	 * Merge method for Merge Sort algorithm.
	 * Your mergeSort algorithm will call this method as appropriate to do the merging.
	 * @param list - An arrayList of items
	 * @param i - lower bound index
	 * @param mid - middle index
	 * @param j - upper bound index 
	 */
	public static<T extends Comparable<T>> void merge(ArrayList<T> list, int i, int mid, int j) {
		//TODO: write the body of this method
		ArrayList<T> temp = new ArrayList<>(j - i + 1);
		int left = i;
		int right = mid + 1;

		//merge two soted halves into temp
		while (left <= mid && right <= j){
			if(list.get(left).compareTo(list.get(right)) <= 0){
				temp.add(list.get(left));
				left++;
			}
			else{
				temp.add(list.get(right));
				right++;
			}
		}
		//leftover from left half
		while (left <= mid){
			temp.add(list.get(left));
			left++;
		}
		//leftover from right half
		while (right <= j){
			temp.add(list.get(right));
			right++;
		}
		//copy temp back into list[i --> j]
		for(int k = 0; k < temp.size(); k++){
			list.set(i+k, temp.get(k)); // i+k ends at j
		}
	}

	//=================================================================================

	// #######################
	// ## HYBRID MERGE SORT ## ----------------------------------------------------------------------
	// #######################
	/**
	 * Hybrid recursive Merge sort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * For this implementation, when the size of the portion of the arrayList
	 * to be sorted is less than 100 items, call insertionSort method to
	 * sort that chunk of the arrayList.
	 *
	 *
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void mergeSortHybrid(ArrayList<T> list) {
		mergeSortHybrid(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void mergeSortHybrid(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		// When the size of array to be sorted is < 100, call insertionSort rather than recurse
		if (i >= j){
			return;
		}
		// if chunk size < 100, do insertion sort on just that range
		if((j - i + 1) < 100){
			for(int a = i+1; a <= j; a++){
				T val = list.get(a);
				int b = a-1;
				while (b >= i && val.compareTo(list.get(b)) < 0){
					list.set(b+1, list.get(b));
					b--;
				}
				list.set(b+1, val);
			}
			return;
		}
		int mid = (i+j)/2;
		mergeSortHybrid(list, i, mid);
		mergeSortHybrid(list, mid+1, j);
		merge(list, i, mid, j);
	}

	//=================================================================================
	
	// ###############
	// ## QUICKSORT ## ----------------------------------------------------------------------
	// ###############	
	/**
	 * Fully recursive Quicksort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * >>> Use any partition scheme that you like. 
	 * 
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void quickSort(ArrayList<T> list) {
		quickSort(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void quickSort(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		if (i < j){
			// pi is the partitioning index, A[pi] is now at the correct place
			int pi = partition(list, i, j);

			//Recursively sort elements before and after partition
			quickSort(list, i, pi - 1);
			quickSort(list, pi + 1, j);
		}
	}
	
	/**
	 * Partition method for Quicksort - Use any valid partition algorithm that you like.
	 * Your quickSort algorithm will call this method as appropriate to do the partitioning.
	 * @param list - An arrayList of items
	 * @param i - lower bound
	 * @param j - upper bound
	 */
	public static<T extends Comparable<T>> int partition(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		T pivot = list.get(j);
        int a = (i - 1); // Index of smaller element

        for (int b = i; b < j; b++){
            // If current element is smaller than the pivot, expand < pivot region
            if(list.get(b).compareTo(pivot) < 0){
                a++;
                // Swap list a and list b
                T temp = list.get(a);
                list.set(a, list.get(b));
                list.set(b, temp);
            }
        }

        // Swap the pivot element with the element at a + 1
        T temp = list.get(a + 1);
        list.set(a + 1, list.get(j));
        list.set(j, temp);
        return a + 1; //pivot's final index
	}
	
	//=================================================================================

        // ######################
	// ## HYBRID QUICKSORT ## ----------------------------------------------------------------------
	// ######################
	/**
	 * Hybrid Quicksort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * >>> Use any partition scheme that you like.
	 * For this implementation, when the size of the portion of the arrayList
	 * to be sorted is less than 100 items, call insertionSort method to
	 * sort that chunk of the arrayList.
	 *
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void quickSortHybrid(ArrayList<T> list) {
		quickSortHybrid(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void quickSortHybrid(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		// When the size of array to be sorted is < 100, call insertionSort rather than recurse
		if(i >= j){
			return;
		}
		//if chunk size < 100, do insertion sort on just that range
		if((j-i+1) < 100){
			for(int a = i+1; a<=j; a++){
				T val = list.get(a);
				int b = a - 1;
				while(b >=i && val.compareTo(list.get(b)) < 0){
					list.set(b+1, list.get(b));
					b--;
				}
				list.set(b+1, val);
			}
			return;
		}
		int pi = partitionHybrid(list, i, j);
		quickSortHybrid(list, i, pi-1);
		quickSortHybrid(list, pi+1, j);
	}

	/**
	 * Partition method for Quicksort - Use any valid partition algorithm that you like.
	 * Your quickSort algorithm will call this method as appropriate to do the partitioning.
	 * @param list - An arrayList of items
	 * @param i - lower bound
	 * @param j - upper bound
	 */
	public static<T extends Comparable<T>> int partitionHybrid(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		return partition(list, i, j); // just reuse lomuto partition
	}

}