package edu.cs3500.spreadsheets.view;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Custom JPanel for the GUI view of a {@code Worksheet}.
 */
public class WorksheetPanel extends JPanel {

  /**
   * Intializes a WorksheetPanel.
   * @param screenHeight height of the panel
   * @param screenWidth width of the panel
   */
  public WorksheetPanel(int screenHeight, int screenWidth) {
    JPanel mainPanel = new JPanel();
    JTextField[][] worksheetCells = new JTextField[screenHeight][screenWidth];
    for (int i = 0; i < screenHeight; i++) {
      for (int j = 0; j < screenWidth; j++) {
        worksheetCells[i][j] = new JTextField("", 8);
        worksheetCells[i][j].setHorizontalAlignment(JTextField.RIGHT);
        mainPanel.add(worksheetCells[i][j]);
      }
    }
    this.setLayout(new GridLayout(screenHeight, screenWidth));
  }
}
