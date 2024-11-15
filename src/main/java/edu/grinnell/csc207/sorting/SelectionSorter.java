package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Khanh Do
 * @author Samuel A. Rebelsky
 */
public class SelectionSorter<T> implements Sorter<T> {
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
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    // Work left to right
    for (int i = 0; i < values.length; i++) {
      select(values, i);
    } // for
  } // sort(T[])

  /**
   * Helper method to find the smallest value in the unsorted portion to swap with the first element
   * in the unsorted portion.
   *
   * @param values
   * @param i
   */
  private void select(T[] values, int i) {
      int smallestIndex = indexOfSmallest(values, i);
      swap(values, i, smallestIndex);
  } // select(T[], int)

  /**
   * Finds the index of the smallest element in the unsorted portion of the array.
   *
   * @param values The array being sorted.
   * @param start The starting index for the unsorted portion.
   * @return The index of the smallest element in the unsorted portion.
   */
  private int indexOfSmallest(T[] values, int start) {
    int smallestIndex = start;
    for (int j = start + 1; j < values.length; j++) {
      if (order.compare(values[j], values[smallestIndex]) < 0) {
        smallestIndex = j;
      } // if
    } // for
    return smallestIndex;
  } // indexOfSmallest(T[], int)

  /**
   * Swaps two elements in an array.
   *
   * @param values The array.
   * @param i The index of the first element.
   * @param j The index of the second element.
   */
  private void swap(T[] values, int i, int j) {
    T temp = values[i];
    values[i] = values[j];
    values[j] = temp;
  } // swap(T[], int, int)
} // class SelectionSorter
