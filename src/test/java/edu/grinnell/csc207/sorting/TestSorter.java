package edu.grinnell.csc207.sorting;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Tests of Sorter objects. Please do not use this class directly. Rather, you should subclass it
 * and initialize stringSorter and intSorter in a static @BeforeAll method.
 *
 * @author Khanh Do
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the permutation and assert that it
   * equals the original.
   *
   * @param <T> The type of values in the array.
   * @param sorted The sorted array.
   * @param perm The permuted sorted array.
   * @param sorter The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm, () -> String.format("sort(%s) yields %s rather than %s",
        Arrays.toString(tmp), Arrays.toString(perm), Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably just to make sure that some
   * test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {"alpha", "bravo", "charlie", "delta", "foxtrot"};
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {"foxtrot", "delta", "charlie", "bravo", "alpha"};
    String[] expected = {"alpha", "bravo", "charlie", "delta", "foxtrot"};
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized array sorts correctly.
   */
  @Test
  public void permutedIntegersTest() {
    int SIZE = 100;
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers

  /**
   * Ensure that a randomly permuted version of a large array sorts correctly.
   */
  @Test
  public void largePermutedIntegersTest() {
    int SIZE = 1000;
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // largePermutedIntegers

  /**
   * Ensure that an emtpy array sorts correctly.
   */
  @Test
  public void emptyCollectionTest() {
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = new Integer[0];
    Integer[] expected = original.clone();
    assertSorts(expected, original, intSorter);
  } // emptyCollectionTest()

  /**
   * Ensure that an array with a single element sorts correctly.
   */
  @Test
  public void singleElementCollectionTest() {
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = {42};
    Integer[] expected = original.clone();
    assertSorts(expected, original, intSorter);
  } // singleElementCollectionTest()

  /**
   * Ensure that sorting works with negative integers.
   */
  @Test
  public void negativeIntergersTest() {
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = {-1, -2, -3, -4, -5};
    Integer[] expected = {-5, -4, -3, -2, -1};
    assertSorts(expected, original, intSorter);
  } // negativeIntergersTest()

  /**
   * Ensure that sorting works with both uppercase and lowercase letters.
   */
  @Test
  public void caseInsensitiveTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {"A", "b", "C", "d", "E"};
    String[] expected = {"A", "C", "E", "b", "d"};
    assertSorts(expected, original, stringSorter);
  } // caseInsensitiveTest()
} // class TestSorter
