package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Parser;
import java.util.Objects;

/**
 * Represents a single cell in a {@code Worksheet}. A cell has a coordinate
 * that locates where it is in the grid of cells and it has cell contents:
 * either a Blank, Value, or Formula.
 */
public class Cell implements ICell {
  private final Coord coord;
  private CellContents cellContents;
  private String rawContent;

  /**
   * Constructs a {@code Cell} object.
   * @param coord {@code Coordinate} object representing location of cell
   * @param unprocessedText raw, unprocessed String input
   */
  public Cell(Coord coord, String unprocessedText) {
    if (coord == null) {
      throw new IllegalArgumentException("Coordinate cannot be null!");
    }
    this.coord = coord;
    this.rawContent = "";
    if (unprocessedText.length() == 0) {
      this.cellContents = new Blank();
    }
    else if (unprocessedText.substring(0,1).equals("=")) {
      this.cellContents = new Formula(Parser.parse(unprocessedText.substring(1)));
    }
    else {
      this.cellContents = new Value(Parser.parse(unprocessedText));
    }
  }

  /**
   * Copy constructor for testing / copy purposes
   * @param coord location of the cell
   * @param cellContents evaluated content of cell
   */
  public Cell(Coord coord, CellContents cellContents) {
    if (coord == null) {
      throw new IllegalArgumentException("Coordinate cannot be null!");
    }
    this.coord = coord;
    this.cellContents = cellContents;
  }

  @Override
  public CellContents evaluate() {
    return null;
//    return this.cellContents.evaluate();
  }

  @Override
  public CellContents getCellContents() {
    return this.cellContents;
  }

  @Override
  public String getRawValue() {
    return this.rawContent;
  }

  @Override
  public Coord getCoord() {
    return this.coord;
  }

  @Override
  public void setCellContent(String contents) {
    if (contents.length() == 0) {
      this.cellContents = new Blank();
    }
    else if (contents.substring(0,1).equals("=")) {
      this.cellContents = new Formula(Parser.parse(contents.substring(1)));
    }
    else {
      this.cellContents = new Value(Parser.parse(contents));
    }
  }

  @Override
  public String toString() {
    return this.cellContents.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Cell)) {
      return false;
    }
    Cell val = (Cell) other;
    val.hashCode();
    return val.cellContents.equals(((Cell) other).cellContents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cellContents);
  }
}
