package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;

public class Formula implements CellContents {

  private Sexp cellContent;
  private Coord c1;
  private Coord c2;

  // Value
  public Formula(Sexp cellContent) {
    this.cellContent = cellContent;
  }

  @Override
  public Sexp evaluate() {
    return cellContent;
  }

}
