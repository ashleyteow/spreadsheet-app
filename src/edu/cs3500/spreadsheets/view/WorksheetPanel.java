package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
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

  /**
   * Fills main panel grid with cells read in from the model.
   */
  public void populateGrid(int screenHeight, int screenWidth, int topLeftCol, int topLeftRow,
      Worksheet model) {
    this.removeAll();
    Cell cell;
    String cellStr;
    JTextField[][] worksheetCells = new JTextField[screenHeight][screenWidth];

    for (int i = 0; i < screenHeight; i++) {
      for (int j = 0; j < screenWidth; j++) {
        cell = model.getCellAt(new Coord(j + topLeftCol + 1, i + topLeftRow + 1));
        cellStr = cell == null ? "" : cell.getCellValue().toString();
        worksheetCells[i][j] = new JTextField(cellStr, 8);
        worksheetCells[i][j].setHorizontalAlignment(JTextField.RIGHT);

        this.add(worksheetCells[i][j]);
      }
    }
  }
}
