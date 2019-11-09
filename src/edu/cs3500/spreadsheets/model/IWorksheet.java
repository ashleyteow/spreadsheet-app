package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents a Worksheet interface.
 */
public interface IWorksheet {

  /**
   * Getter method to access this worksheet's grid of cells.
   *
   * @return all cells in this spreadsheet
   */
  ArrayList<Cell> getCells();

  /**
   * Returns the cell at the specified coordinates.
   *
   * @param coord location of the cell
   * @return the cell at the given position, or <code>null</code> if no card is there
   * @throws IllegalArgumentException if the coordinates are invalid
   */
  ICell getCellAt(Coord coord);

  /**
   * Edits the contents of the cell at the specified coordinates.
   * @param coord location of the cell
   * @param newContents the new contents of the {@code Cell} as an unprocessed String
   */
  void editCellAt(Coord coord, String newContents);
}
