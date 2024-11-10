package edu.grinnell.csc207.sorting;

import java.util.Arrays;
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
  @Override
  public void sort(T[] values) {
    int n = values.length;

    // base case: if the array has 0 or 1 elements, it is already sorted
    if (n <= 1) {
      return;
    } // if

    // recursive case
    int mid = n / 2;
    T[] first = Arrays.copyOfRange(values, 0, mid);
    T[] second = Arrays.copyOfRange(values, mid, n);
    sort(first);
    sort(second);
    values = merge(first, second);
  } // sort(T[])

  /**
   * Merge the two sorted arrays values1 and values2 into a single sorted array.
   *
   * @param first the first sorted array
   * @param second the second sorted array
   * @return a new array that contains all the values from values1 and values2, in order
   * @pre values1 is sorted
   * @pre values2 is sorted
   * @post the new array is sorted
   * @post values1 and values2 are unchanged
   */
  @SuppressWarnings("unchecked")
  private T[] merge(T[] first, T[] second) {
    int newArrLength = first.length + second.length;
    T[] newArr = (T[]) (new Object[newArrLength]);
    int p1 = 0;
    int p2 = 0;
    int i = 0;
    while (i < newArrLength && p1 < first.length && p2 < second.length) {
      if (order.compare(second[p2], first[p1]) < 0) {
        newArr[i] = second[p2];
        p2++;
      } else {
        newArr[i] = first[p1];
        p1++;
      } // if/else
      i++;
    } // while

    // grab any lingering items in the first array after we've
    // exhausted the second array
    while (p1 < first.length) {
      newArr[i] = first[p1];
      i += 1;
      p1 += 1;
    } // while

    // grab any lingering items in the second array after we've
    // exhausted the first array
    while (p2 < second.length) {
      newArr[i] = second[p2];
      i += 1;
      p2 += 1;
    } // while

    return newArr;
  } // merge(T[], T[])
} // class MergeSorter
