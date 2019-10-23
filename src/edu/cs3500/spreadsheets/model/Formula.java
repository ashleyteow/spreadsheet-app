package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;

public class Formula extends Value {

  private Sexp cellContent;

  // Value
  public Formula(String unprocessed) {
    this.cellContent = convertToValue(unprocessed);
  }

  private Sexp convertToValue(String s) {
    return null;
  }

}
