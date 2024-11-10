package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    sort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * Helper method that recursively parition an array into 2 halves and sort each subarray.
   *
   * @param values the array to be sorted
   * @param left the starting index
   * @param right the ending index
   */
  private void sort(T[] values, int left, int right) {
    // base case:
    if (left >= right) {
      return;
    } // if

    // divide the array into smaller subarrays
    int pivotIndex = partition(values, left, right);

    // recursively sort each subarray
    sort(values, left, pivotIndex - 1);
    sort(values, pivotIndex + 1, right);
  } // sort(T[], int, int)

  /**
   * Helper method to choose a pivot and sort accordingly.
   *
   * @param values the array to be sorted
   * @param left the starting index of the to be sorted array
   * @param right the ending index of the to be sorted array
   * @return the index of the element that separates the array into a smaller value subarray and
   *         larger value subarray
   */
  private int partition(T[] values, int left, int right) {
    // choose a pivot
    T pivot = values[right];
    int leftIndex = left;
    int rightIndex = right;

    while (leftIndex <= rightIndex) {
      // walk until we find something on the left side that belongs to the right side (less than the
      // pivot)
      while (leftIndex <= right && (order.compare(values[leftIndex], pivot) < 0)) {
        leftIndex += 1;
      } // while

      // walk until we find something on the right side that belongs to the left side (>= pivot)
      while (rightIndex >= left && (order.compare(values[rightIndex], pivot) >= 0)) {
        rightIndex -= 1;
      } // while

      T temp = values[leftIndex];

      // swap
      if (leftIndex < rightIndex) {
        values[leftIndex] = values[rightIndex];
        values[rightIndex] = temp;
      } else {
        values[leftIndex] = values[right];
        values[right] = temp;
      } // if/else
    } // while

    return leftIndex;
  } // partition(T[], int, int)
} // class Quicksorter
