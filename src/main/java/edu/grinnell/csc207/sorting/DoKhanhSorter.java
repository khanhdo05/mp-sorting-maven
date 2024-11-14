package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using my own sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Khanh Do
 */
public class DoKhanhSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The threshold beyond which we use insertion sort.
   */
  private static final int INSERTION_SORT_THRESHOLD = 32;

  /**
   * The threshold beyond which we check for nearly sorted arrays.
   */
  private static final int CHECK_SORTED_THRESHOLD = 1000;

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public DoKhanhSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // DoKhanhSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using my sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    T[] tempArray = values.clone();
    adaptiveMergeSort(values, tempArray, 0, values.length - 1);
  } // sort(T[])

  /**
   * Recursively partition the array into smaller arrays and sort them using insertion or merge
   * sort.
   *
   * @param array the array to sort
   * @param lb the left index
   * @param ub the right index
   */
  private void adaptiveMergeSort(T[] array, T[] tempArray, int lb, int ub) {
    int difference = ub - lb + 1;

    if (difference <= INSERTION_SORT_THRESHOLD) {
      // Use insertion sort for small arrays
      insertionSort(array, lb, ub);
    } else if (isNearlySorted(array, lb, ub) && difference < CHECK_SORTED_THRESHOLD) {
      // Use insertion sort for nearly sorted arrays
      insertionSort(array, lb, ub);
    } else {
      // Use merge sort for large arrays
      int mid = lb + (ub - lb) / 2;
      adaptiveMergeSort(array, tempArray, lb, mid);
      adaptiveMergeSort(array, tempArray, mid + 1, ub);
      merge(array, tempArray, lb, mid, ub);
    } // if/else
  } // adaptiveMergeSort(T[], int, int)

  /**
   * Check if the array is nearly sorted.
   *
   * @param array the array to check
   * @param lb the left index
   * @param ub the right index
   * @return true if the array is nearly sorted, false otherwise
   */
  private boolean isNearlySorted(T[] array, int lb, int ub) {
    int misplacedCount = 0;
    for (int i = lb + 1; i <= ub; i++) {
      if (order.compare(array[i], array[i - 1]) < 0) {
        misplacedCount++;
      } // if
    } // for
    return misplacedCount < (ub - lb) * 0.1;
  } // isNearlySorted(T[], int, int)

  /**
   * Sort an array using insertion sort. This is different than the one in InsertionSorter because
   * we have lower and upper bounds.
   *
   * @param array the array to sort
   * @param lb the left index
   * @param ub the right index
   */
  private void insertionSort(T[] array, int lb, int ub) {
    for (int i = lb + 1; i <= ub; i++) {
      T key = array[i];
      int j = i - 1;
      while (j >= lb && order.compare(array[j], key) > 0) {
        array[j + 1] = array[j];
        j--;
      } // while
      array[j + 1] = key;
    } // for
  } // insertionSort(T[], int, int)

  /**
   * Helper method that merges two sorted subarrays into a single sorted subarray. Copied from
   * MergeSorter.java.
   *
   * @param values the array containing the subarrays to be merged
   * @param tempArray a temporary array used for merging
   * @param lb the starting index of the first subarray
   * @param mid the ending index of the first subarray
   * @param ub the ending index of the second subarray
   * @pre The subarray from values[lb] to values[mid] is sorted
   * @pre The subarray from values[mid+1] to values[ub] is sorted
   * @post The subarray from values[lb] to values[ub] is sorted
   */
  private void merge(T[] values, T[] tempArray, int lb, int mid, int ub) {
    System.arraycopy(values, lb, tempArray, lb, ub - lb + 1);

    int i = lb, j = mid + 1, k = lb;

    while (i <= mid && j <= ub) {
      if (order.compare(tempArray[i], tempArray[j]) <= 0) {
        values[k++] = tempArray[i++];
      } else {
        values[k++] = tempArray[j++];
      } // if/else
    } // while

    // Place the remaining of the left side after we have exhaust the right side
    while (i <= mid) {
      values[k++] = tempArray[i++];
    } // while

    // Place the remaining of the right side after we have exhaust the left side
    while (j <= ub) {
      values[k++] = tempArray[j++];
    } // while
  } // merge(T[], T[])
} // class DoKhanhSorter
