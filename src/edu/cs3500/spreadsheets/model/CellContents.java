package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * This represents the actual cell contents: this can either be a Blank cell, Value cell, Formula
 * cell. This class is responsible for holding the cell's contents.
 */
public interface CellContents {

  /**
   * Returns the evaluated value of this cell's contents.
   *
   * @return value of this cell
   */
  Value getVal();

  /**
   * Converts / updates a {@code Formula}'s arraylist of values to be evaluated. This is a helper
   * function that simply adds its value to the arraylist of args.
   *
   * @param args args to pass to a formula
   */
  void populateArgsHelp(ArrayList<Value> args);
}
