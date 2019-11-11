package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Parser;

/**
 * Represents a single cell in a {@code Worksheet}. A cell has a coordinate
 * that locates where it is in the grid of cells and it has cell contents:
 * either a Blank, Value, or Formula.
 */
public class Cell implements ICell {
  private final Coord coord;
  private CellContents cellContents;
  private String rawContent;
  private final Worksheet worksheet;

  /**
   * Constructs a {@code Cell} object.
   * @param coord {@code Coordinate} object representing location of cell
   * @param unprocessedText raw, unprocessed String input
   */
  public Cell(Coord coord, String unprocessedText, Worksheet worksheet) {
    if (coord == null) {
      throw new IllegalArgumentException("Coordinate cannot be null!");
    }
    this.coord = coord;
    this.rawContent = unprocessedText;
    this.worksheet = worksheet;
    this.cellContents = evaluate(worksheet);
  }

  /**
   * Constructs an empty cell at the specified coordinate.
   * @param coord location of cell
   */
  public Cell(Coord coord) {
    this.coord = coord;
    this.cellContents = new ValueBlank();
    this.rawContent = "";
    this.worksheet = new Worksheet();
  }

  /**
   * Copy constructor for testing / copy purposes.
   * @param coord location of the cell
   * @param cellContents evaluated content of cell
   */
  public Cell(Coord coord, CellContents cellContents) {
    if (coord == null) {
      throw new IllegalArgumentException("Coordinate cannot be null!");
    }
    this.coord = coord;
    this.cellContents = cellContents;
    this.worksheet = new Worksheet();
  }

  @Override
  public String getRawValue() {
    return this.rawContent;
  }

  @Override
  public Value getCellValue() {
    return this.cellContents.getVal();
  }

  @Override
  public Coord getCoord() {
    return this.coord;
  }

  @Override
  public void setCellContent(String contents, Worksheet worksheet) {
    this.rawContent = contents;
    this.cellContents = evaluate(worksheet);
  }

  private CellContents evaluate(Worksheet worksheet) {
    if (rawContent.length() == 0) {
      return new ValueBlank();
    }
    if (rawContent.charAt(0) == '=') {
      return Parser.parse(this.rawContent.substring(1)).accept(new Evaluator(coord, worksheet));
    } else {
      return Parser.parse(this.rawContent).accept(new Evaluator(coord, worksheet));
    }
  }

  @Override
  public String toString() {
    if (this.cellContents == null) {
      return "";
    }
    return getCellValue().toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Cell)) {
      return false;
    }
    Cell otherCell = (Cell) other;

    return (this.rawContent.equals(otherCell.rawContent) && this.coord.equals(otherCell.coord));
  }

  @Override
  public int hashCode() {
    return 17 * this.rawContent.hashCode() * this.coord.hashCode();
  }
}
