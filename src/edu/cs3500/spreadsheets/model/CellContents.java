package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * This represents the actual cell contents: this can either be a
 * Blank cell, Value cell, Formula cell. This class is responsible for holding the
 * cell's contents.
 */
public interface CellContents {

  /**
   * Returns the S-Expression value of this cell's contents.
   * @return S-expression
   */
  Sexp getContents();

}
