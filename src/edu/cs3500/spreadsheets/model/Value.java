package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Represents an implementation of CellContents. This class represents if a
 * cell's contents do not begin with an equal sign and is therefore simply
 * a value in the spreadsheet.
 */
public class Value implements CellContents {
  private Sexp cellContent;

  /**
   * Constructs a {@code Value} object.
   * @param cellContent S-expression
   */
  public Value(Sexp cellContent) {
    this.cellContent = cellContent;
  }

  @Override
  public Sexp getContents() {
    return this.cellContent;
  }
}
