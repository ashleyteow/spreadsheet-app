package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;

public class Formula extends Value {

  private Sexp cellContent;

  // Value
  public Formula(Sexp cellContent) {
    super(cellContent);
  }

//  private Sexp convertToValue(String s) {
//    return null;
//  }

}
