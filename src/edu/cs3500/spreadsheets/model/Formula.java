package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * A formula is a value and therefore inherits all methods from the
 * Value class. This class is responsible for processing the operation the user
 * intends to process.
 */
public class Formula extends Value {

  private Sexp cellContent;

  /**
   * Constructs a {@code Formula} object.
   * @param cellContent S-expression
   */
  public Formula(Sexp cellContent) {
    super(cellContent);
  }

}
