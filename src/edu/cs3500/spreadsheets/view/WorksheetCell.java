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
   * Initializes a cell in the worksheet's visual view.
   * @param field a new JTextField that represents a visual cell
   * @param row the row position of this cell
   * @param col the column position of this cell
   */
  public WorksheetCell(JTextField field, int row, int col) {
    this.row = row;
    this.col = col;
    this.cellPanel = field;
  }
}
