package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents a region of cells in a {@code Worksheet}.
 */
public class Reference implements CellContents {
  private final ArrayList<Coord> referencedCells;
  private final Worksheet worksheet;
  private Coord thisCellLoc;

  /**
   * Constructs a {@code Reference} object representing a referenced region of cells.
   * @param firstCoord location of the cell referenced before colon
   * @param secondCoord location of the cell referenced after colon
   * @param thisCell location of cell that holds the reference (this cell)
   * @param worksheet worksheet model
   */
  public Reference(Coord firstCoord, Coord secondCoord, Coord thisCell, Worksheet worksheet) {
    this.referencedCells = new ArrayList<>();
    this.worksheet = worksheet;

    // TODO figure out the math to find all cells from firstCoord to secondCoord
  }

  /**
   * Constructs a {@code Reference} object representing a single referenced cell.
   * @param referencedCell cell that is being referenced
   * @param thisCell location of cell that holds the reference (this cell)
   * @param worksheet worksheet model
   */
  public Reference(Coord referencedCell, Coord thisCell, Worksheet worksheet) {
    this.thisCellLoc = thisCell;
    this.worksheet = worksheet;
    this.referencedCells = new ArrayList<>();
    if (!cyclePresent(referencedCell)) {
      this.referencedCells.add(referencedCell);
    }
  }

  /**
   * Determines whether a cell at the given coordinates initiates a cyclic reference.
   * @param coord location of cell to check
   * @return true if there is a cyclic reference, false otherwise
   */
  private boolean cyclePresent(Coord coord) {
    // TODO: implement this before starting view
    return false;
  }

  @Override
  public Value getVal() {
    return null;
  }

  @Override
  public void populateArgs(ArrayList<Value> args) {
    for (Coord c: this.referencedCells) {
      if (Worksheet.flattenCells(worksheet.getCells()).contains(worksheet.getCellAt(c))) {
        args.add(worksheet.getCellAt(c).getCellValue());
      }
    }
  }

  @Override
  public String toString() {
    return getVal().toString();
  }
}
