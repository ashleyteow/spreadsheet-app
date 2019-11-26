package edu.cs3500.spreadsheets.view;

import javax.swing.JTextField;

/**
 * Represents the visual representation an individual cell in a {@code Worksheet}.
 */
public class WorksheetCell {

  private final int row;
  private final int col;
  private final JTextField cellPanel;

  public WorksheetCell(JTextField field, int row, int col) {
    this.row = row;
    this.col = col;
    this.cellPanel = field;
  }
}
