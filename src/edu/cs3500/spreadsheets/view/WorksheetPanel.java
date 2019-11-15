package edu.cs3500.spreadsheets.view;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WorksheetPanel extends JPanel {
  private JPanel mainPanel;
  private int screenHeight;
  private int screenWidth;

  public WorksheetPanel(int screenHeight, int screenWidth) {
    this.screenHeight = screenHeight;
    this.screenWidth = screenWidth;
    this.mainPanel = new JPanel();
    JTextField[][] worksheetCells = new JTextField[this.screenHeight][this.screenWidth];
    for (int i = 0; i < screenHeight; i++) {
      for (int j = 0; j < screenWidth; j++) {
        worksheetCells[i][j] = new JTextField("", 8);
        worksheetCells[i][j].setHorizontalAlignment(JTextField.RIGHT);
        this.mainPanel.add(worksheetCells[i][j]);
      }
    }
    this.setLayout(new GridLayout(screenHeight, screenWidth));
  }
}
