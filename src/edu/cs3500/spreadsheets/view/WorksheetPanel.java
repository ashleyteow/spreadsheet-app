package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Custom JPanel for the GUI view of a {@code Worksheet}.
 */
public class WorksheetPanel extends JPanel {

  private Worksheet worksheet;
  private int topLeftRow;
  private int topLeftCol;
  private JPanel mainPanel;
  private JPanel rowHeader;
  private JPanel colHeader;
  private JTextField[][] worksheetCells;
  private static int screenHeight = WorksheetVisualView.SCREENHEIGHT;
  private static int screenWidth = WorksheetVisualView.SCREENWIDTH;
  private JPanel navigateButtons;

  /**
   * Intializes a WorksheetPanel.
   */
  public WorksheetPanel(Worksheet worksheet) {
    super(new BorderLayout());
    this.worksheet = worksheet;
    this.topLeftCol = 0;
    this.topLeftRow = 0;

    this.worksheetCells = new JTextField[screenHeight][screenWidth];

    this.mainPanel = new JPanel(new GridLayout(screenHeight, screenWidth));

    this.rowHeader = new JPanel(new GridLayout(screenHeight, 1));
    this.colHeader = new JPanel(new GridLayout(1, screenWidth + 1));
    this.navigateButtons = new JPanel(new GridLayout(2,2));
    this.build();
  }

  /**
   * Builds the GUI.
   */
  private void build() {
    populateGrid();
    createHeaders();

    JButton left = new JButton("⇦");
    JButton right = new JButton("⇨");
    JButton up = new JButton("⇧");
    JButton down = new JButton("⇩");
    this.setButtonListener(left, right, up, down);
    this.navigateButtons.setPreferredSize(new Dimension(100, 100));

    this.add(colHeader, BorderLayout.NORTH);
    this.add(rowHeader, BorderLayout.WEST);
    this.add(mainPanel, BorderLayout.CENTER);
    this.add(navigateButtons, BorderLayout.SOUTH);

    //this.refresh();
  }

  /**
   * Fills main panel grid with cells read in from the model.
   */
  public void populateGrid() {
    this.mainPanel.removeAll();
    Cell cell;
    String cellStr;
    JTextField[][] worksheetCells = new JTextField[screenHeight][screenWidth];

    for (int i = 0; i < screenHeight; i++) {
      for (int j = 0; j < screenWidth; j++) {
        cell = worksheet.getCellAt(new Coord(j + topLeftCol + 1, i + topLeftRow + 1));
        cellStr = cell == null ? "" : cell.getCellValue().toString();
        worksheetCells[i][j] = new JTextField(cellStr, 8);
        worksheetCells[i][j].setHorizontalAlignment(JTextField.RIGHT);

        this.mainPanel.add(worksheetCells[i][j]);
      }
    }
  }

  /**
   * Creates and adds action listeners for the appropriate scroll mechanisms.
   * @param left left scroll button
   * @param right right scroll button
   * @param up up scroll button
   * @param down down scroll button
   */
  private void setButtonListener(JButton left, JButton right, JButton up, JButton down) {
    left.setFont(new Font("Arial", Font.PLAIN, 30));
    right.setFont(new Font("Arial", Font.PLAIN, 30));
    up.setFont(new Font("Arial", Font.PLAIN, 30));
    down.setFont(new Font("Arial", Font.PLAIN, 30));

    left.addActionListener(e -> navigate("left"));
    right.addActionListener(e -> navigate("right"));
    up.addActionListener(e -> navigate("up"));
    down.addActionListener(e -> navigate("down"));

    this.navigateButtons.add(left);
    this.navigateButtons.add(right);
    this.navigateButtons.add(up);
    this.navigateButtons.add(down);
  }

  /**
   * Updates the screen based on the width and height scroll lengths.
   * @param direction either left, right, up, down
   */
  private void navigate(String direction) {
    if (direction.equals("left")) {
      if (topLeftCol - screenWidth >= 0) {
        topLeftCol = topLeftCol - screenWidth;
      }
    }
    else if (direction.equals("right")) {
      topLeftCol += screenWidth;
    }
    else if (direction.equals("up")) {
      if (topLeftRow - screenHeight >= 0) {
        topLeftRow = topLeftRow - screenHeight;
      }
    }
    else {
      // down scroll
      topLeftRow += screenHeight;
    }
    this.populateGrid();
    this.createHeaders();
    this.refresh();
  }

  /**
   * Fills the Coordinate headers with the row and column values.
   */
  public void createHeaders() {
    this.rowHeader.removeAll();
    this.colHeader.removeAll();


    for (int i = 0; i < screenWidth + 1; i++) {
      String columnName = Coord.colIndexToName(i + topLeftCol + 1);
      JLabel column = new JLabel(columnName);
      column.setHorizontalAlignment(JTextField.CENTER);
      column.setBackground(Color.darkGray);
      column.setOpaque(true);
      column.setForeground(Color.white);
      column.setFocusable(false);
      column.setBorder(new EmptyBorder(0,0,0,0));
      colHeader.add(column);
    }

    for (int j = 0; j < screenHeight; j++) {
      String rowNum = Integer.toString(j + topLeftRow + 1);
      JLabel row = new JLabel(rowNum);
      row.setHorizontalAlignment(JTextField.CENTER);
      row.setBackground(Color.darkGray);
      row.setOpaque(true);
      row.setForeground(Color.white);
      row.setFocusable(false);
      row.setBorder(new EmptyBorder(0,5,0,5));
      rowHeader.add(row);
    }
  }

  public void refresh() {
    this.revalidate();
    this.repaint();
  }
}
