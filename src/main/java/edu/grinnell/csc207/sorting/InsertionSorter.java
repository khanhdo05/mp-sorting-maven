package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Khanh Do
 * @author Samuel A. Rebelsky (Starter code)
 */
public class InsertionSorter<T> implements Sorter<T> {
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
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using insertion sort.
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
      insert(values, i);
    } // for
  } // sort(T[])

  /**
   * Helper method to insert the the current element to its correct position.
   *
   * @param values the array to sort
   * @param i the index of the first unsorted element
   */
  private void insert(T[] values, int i) {
    T currentValue = values[i];
    int pointer = i - 1;

    // Move values that are greater than the current value to the right
    while (pointer >= 0 && (order.compare(values[pointer], currentValue) > 0)) {
      values[pointer + 1] = values[pointer];
      pointer--;
    } // while

    // Insert the current value
    values[pointer + 1] = currentValue;
  } // insert(T[], int)
} // class InsertionSorter
