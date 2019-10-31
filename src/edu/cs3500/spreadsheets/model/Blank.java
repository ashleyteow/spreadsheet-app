package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Represents an implementation of CellContents. This class represents if a
 * cell is blank and is defaulted to have a blank String.
 */
public class Blank implements CellContents {
  private Sexp cellContent;

  /**
   * Constructs a {@code Blank} object.
   */
  public Blank() {
    this.cellContent = new SString("");
  }

  @Override
  public Sexp evaluate() {
    return this.cellContent;
  }

  @Override
  public String toString() {
    return "";
  }

}
