package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * This represents the actual cell contents: this can either be a
 * Blank cell, Value cell, Formula cell. This class is responsible for holding the
 * cell's contents.
 */
public interface CellContents {

  /**
   * Returns the evaluated value of this cell's contents.
   * @return value of this cell
   */
  Value getVal();

  /**
   * Helper function for the {@link Formula} flatten method.
   * @param args list of args to process from formula.
   */
  void flatten(ArrayList<Value> args);
}
