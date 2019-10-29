package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Parser;

/**
 * Represents a single cell in a {@code Worksheet}. A cell has a coordinate
 * that locates where it is in the grid of cells and it has cell contents:
 * either a Blank, Value, or Formula.
 */
public class Cell {
  private final Coord coord;
  private CellContents cellContents;

  /**
   * Constructs a {@code Cell} object.
   * @param coord {@code Coordinate} object representing location of cell
   * @param unprocessedText raw, unprocessed String input
   */
  public Cell(Coord coord, String unprocessedText) {
    this.coord = coord;
    if (unprocessedText.length() == 0) {
      this.cellContents = new Blank();
    }
    else if (unprocessedText.substring(0,1).equals("=")) {
      this.cellContents = new Formula(Parser.parse(unprocessedText));
    }
    else {
      this.cellContents = new Value(Parser.parse(unprocessedText));
    }
  }

  /**
   * Getter method for this cells' contents.
   * @return {@code CellContents} object
   */
  public CellContents getCellContents() {
    return this.cellContents;
  }
}
