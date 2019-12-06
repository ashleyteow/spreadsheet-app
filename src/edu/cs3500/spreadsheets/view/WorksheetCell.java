package edu.cs3500.spreadsheets.view;

import javax.swing.JTextField;

/**
 * Represents the visual representation an individual cell in a {@code Worksheet}.
 */
public class WorksheetCell {

  final int row;
  final int col;
  JTextField cellPanel;

  /**
   * Initializes a single cell in a {@code Worksheet}.
   * @param field the JTextField representing this cell
   * @param row the row in which this cell is in
   * @param col the column in which this cell is in
   */
  public WorksheetCell(JTextField field, int row, int col) {
    this.row = row;
    this.col = col;
    this.cellPanel = field;
  }
}
