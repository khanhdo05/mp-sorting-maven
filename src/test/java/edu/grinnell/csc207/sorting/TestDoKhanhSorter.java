package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Tests of our KhanhDoSorter.
 */
public class TestDoKhanhSorter extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new DoKhanhSorter<String>((x, y) -> x.compareTo(y));
    intSorter = new DoKhanhSorter<Integer>((x, y) -> x.compareTo(y));
  } // setup()

} // class TestInsertionSorter
