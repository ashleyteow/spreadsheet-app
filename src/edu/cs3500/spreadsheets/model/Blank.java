package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.Sexp;

public class Blank implements CellContents {

  private Sexp cellContent;

  public Blank() {
    this.cellContent = new SString("");
  }

  @Override
  public Sexp evaluate() {
    return cellContent;
  }

}
