package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;

public class Value implements CellContents {

  private Sexp cellContent;

  public Value(Sexp cellContent) {
    this.cellContent = cellContent;
  }

  @Override
  public Sexp evaluate() {
    return cellContent;
  }

}
