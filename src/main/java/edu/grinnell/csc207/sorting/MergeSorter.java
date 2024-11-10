package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @SuppressWarnings("unchecked")
  @Override
  public void sort(T[] values) {
    T[] tempArray = (T[]) new Object[values.length];
    sort(values, tempArray, 0, values.length - 1);
  } // sort(T[])

  /**
   * Helper method that recursively split array into 2 halves, sort each half, and then merge them.
   * 
   * @param values the array to be sorted
   * @param tempArray the holder
   * @param left left pointer
   * @param right right pointer
   */
  private void sort(T[] values, T[] tempArray, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;
      sort(values, tempArray, left, mid);
      sort(values, tempArray, mid + 1, right);
      merge(values, tempArray, left, mid, right);
    } // if
  } // sort(T[], T[], int, int)

  /**
   * Helper method that merges two sorted subarrays into a single sorted subarray.
   *
   * @param values the array containing the subarrays to be merged
   * @param tempArray a temporary array used for merging
   * @param left the starting index of the first subarray
   * @param mid the ending index of the first subarray
   * @param right the ending index of the second subarray
   * @return a new array that contains all the values from values1 and values2, in order
   * @pre The subarray from values[left] to values[mid] is sorted
   * @pre The subarray from values[mid+1] to values[right] is sorted
   * @post The subarray from values[left] to values[right] is sorted
   */
  private void merge(T[] values, T[] tempArray, int left, int mid, int right) {
    System.arraycopy(values, left, tempArray, left, right - left + 1);

    int i = left;
    int j = mid + 1;
    int k = left;

    while (i <= mid && j <= right) {
      if (order.compare(tempArray[i], tempArray[j]) <= 0) {
        values[k] = tempArray[i];
        i++;
      } else {
        values[k] = tempArray[j];
        j++;
      } // if/else
      k++;
    } // while

    // Place the remaining of the left side after we have exhaust the right side
    while (i <= mid) {
      values[k] = tempArray[i];
      i++;
      k++;
    } // while

    // Place the remaining of the right side after we have exhaust the left side
    while (j <= right) {
      values[k] = tempArray[j];
      j++;
      k++;
    } // while
  } // merge(T[], T[])
} // class MergeSorter
