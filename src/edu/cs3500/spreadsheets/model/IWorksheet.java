package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public interface IWorksheet {

  /**
   * Getter method to access this worksheet's grid of cells.
   *
   * @return all cells in this spreadsheet
   */
  ArrayList<ArrayList<Cell>> getCells();

  /**
   * Returns the cell at the specified coordinates.
   *
   * @param col column of the desired cell
   * @param row row of the desired cell
   * @return the cell at the given position, or <code>null</code> if no card is there
   * @throws IllegalArgumentException if the coordinates are invalid
   */
  Cell getCellAt(int col, int row);

  /**
   * Verifies that all cells were created in the {@code Worksheet}.
   *
   * @return true if all cells were evaluated correctly
   */
  boolean evaluateCells();

}
