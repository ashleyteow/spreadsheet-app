package edu.cs3500.spreadsheets.model;

import java.util.Objects;

/**
 * A value type representing coordinates in a {@link Worksheet}.
 */
public class Coord {

  public final int row;
  public final int col;

  /**
   * Constructs a coordinate for a {@code Cell}.
   *
   * @param col column number
   * @param row row number
   */
  public Coord(int col, int row) {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Coordinates should be strictly positive");
    }
    this.row = row;
    this.col = col;
  }

  /**
   * Constructs a coordinate for a {@code Cell}, to be used in main method for user input.
   *
   * @param coord user-inputted coordinate
   */
  public Coord(String coord) {
    String columnName = "";
    String rowNum = "";
    boolean isDigit = false;

    for (int i = 0; i < coord.length(); i++) {
      if (Character.isDigit(coord.charAt(i))) {
        isDigit = true;
        rowNum += String.valueOf(coord.charAt(i));
      } else if (isDigit && Character.isAlphabetic(coord.charAt(i))) {
        throw new IllegalArgumentException("Invalid coordinate!");
      } else {
        // only column names
        columnName += coord.charAt(i);
      }
    }
    this.row = Integer.parseInt(rowNum);
    this.col = Coord.colNameToIndex(columnName);
  }

  /**
   * Determines whether this coord is valid based on the worksheet's max row index and max column
   * index.
   *
   * @param maxRow maximum row of a {@code Worksheet}
   * @param maxCol maximum column of a {@code Worksheet}
   * @return true if this is valid, false otherwise
   */
  public boolean validCoord(int maxRow, int maxCol) {
    return row < maxRow && row >= 0 && col < maxCol && col >= 0;
  }

  /**
   * Converts from the A-Z column naming system to a 1-indexed numeric value.
   *
   * @param name the column name
   * @return the corresponding column index
   */
  public static int colNameToIndex(String name) {
    name = name.toUpperCase();
    int ans = 0;
    for (int i = 0; i < name.length(); i++) {
      ans *= 26;
      ans += (name.charAt(i) - 'A' + 1);
    }
    return ans;
  }

  /**
   * Converts a 1-based column index into the A-Z column naming system.
   *
   * @param index the column index
   * @return the corresponding column name
   */
  public static String colIndexToName(int index) {
    StringBuilder ans = new StringBuilder();
    while (index > 0) {
      int colNum = (index - 1) % 26;
      ans.insert(0, Character.toChars('A' + colNum));
      index = (index - colNum) / 26;
    }
    return ans.toString();
  }

  @Override
  public String toString() {
    return colIndexToName(this.col) + this.row;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coord coord = (Coord) o;
    return row == coord.row
        && col == coord.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }
}
