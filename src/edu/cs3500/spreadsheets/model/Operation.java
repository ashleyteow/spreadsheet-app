package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Performs an operation on a cell based on what the user inputted after the equal sign. Eg. a
 * {@code Sum} object would be created in response to user input =SUM(A1, A2).
 */
public interface Operation {

  /**
   * Responsible for executing the given operation on its arguments.
   *
   * @param vals arguments to be evaluated
   * @return evaluated value after function has been executed
   */
  Value apply(List<Value> vals);
}
