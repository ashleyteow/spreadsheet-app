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
   * Returns the raw contents of the cell at the specified coordinates.
   * @param coord location of the cell
   * @return the cell at the given position, or <code>null</code> if no card is there
   * @throws IllegalArgumentException if the coordinates are invalid
   */
  String getCellRaw(Coord coord);

  /**
   * Edits the contents of the cell at the specified coordinates.
   * @param coord location of the cell
   * @param newContents the new contents of the {@code Cell} as an unprocessed String
   */
  void editCellAt(Coord coord, String newContents);

  /**
   * Adds a new cell with the given contents at the specified coordinates.
   * @param coord location of the new cell
   * @param contents contents of the new {@code Cell} as an unprocessed String
   */
  void addCellAt(Coord coord, String contents);

//  /**
//   * Verifies that all cells were created in the {@code Worksheet}.
//   * @return true if all cells were evaluated correctly
//   */
//  boolean evaluateCells();

  /**
   * Evaluates the contents of the cell located at the given location
   * @param coord location of where the cell is
   * @return evaluated content of this cell (Value, Blank, Formula)
   * @throws IllegalArgumentException if the location given is invalid
   */
  CellContents evaluateSingleCell(Coord coord) throws IllegalArgumentException;
}
