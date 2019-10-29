package edu.cs3500.spreadsheets.model;

/**
 * Performs an operation on a cell based on what the user inputted
 * after the equal sign. Eg. a {@code Sum} object would be created in response
 * to user input =SUM(A1, A2).
 */
public interface Operation {
  /**
   * Performs an operation on a cell in some manner.
   */
  void operate();

}
