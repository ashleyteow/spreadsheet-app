package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Represents a GUI view of a {@code Worksheet}.
 */
public class WorksheetVisualView extends JFrame implements WorksheetView {
  private Worksheet model;
  private JPanel mainPanel;
  private JPanel scrollButtons;
  private JPanel rowHeader;
  private JPanel colHeader;
  private static int SCREENWIDTH = 12;
  private static int SCREENHEIGHT = 20;
  private int topLeftRow;
  private int topLeftCol;

  /**
   * Constructs a {@code WorksheetVisualView} object.
   * @param model worksheet
   */
  public WorksheetVisualView(Worksheet model) {
    this.model = model;
    this.topLeftRow = 0;
    this.topLeftCol = 0;
    this.mainPanel = new JPanel(new GridLayout(SCREENHEIGHT, SCREENWIDTH));
    this.rowHeader = new JPanel(new GridLayout(SCREENHEIGHT, 0));
    this.colHeader = new JPanel(new GridLayout(0, SCREENWIDTH + 1));
    this.scrollButtons = new JPanel(new GridLayout(2,2));

    populateGrid(mainPanel);
    createHeaders(rowHeader, colHeader);
    JButton left = new JButton("Left");
    JButton right = new JButton("Right");
    JButton up = new JButton("Up");
    JButton down = new JButton("Down");
    addButtonListeners(left, right, up , down);

    this.add(mainPanel, BorderLayout.CENTER);
    this.add(colHeader, BorderLayout.NORTH);
    this.add(rowHeader, BorderLayout.WEST);
    this.add(scrollButtons, BorderLayout.SOUTH);
    this.pack();
  }

  @Override
  public void render() throws IOException {
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.pack();
    this.repaint();
  }

  /**
   * Fills in the row and column headers according to default capacity for the screen.
   * @param rowPanel row header JPanel
   * @param colPanel col header JPanel
   */
  private void createHeaders(JPanel rowPanel, JPanel colPanel) {
    colPanel.removeAll();
    rowPanel.removeAll();

    for (int i = 0; i < SCREENWIDTH + 1; i++) {
      String columnName = Coord.colIndexToName(i + topLeftCol + 1);
      JTextField columnField = new JTextField(columnName);
      columnField.setHorizontalAlignment(JTextField.CENTER);
      columnField.setFocusable(false);
      columnField.setEditable(false);
      colPanel.add(columnField);
    }

    for (int j = 0; j < SCREENHEIGHT; j++) {
      String rowNum = Integer.toString(j + topLeftRow + 1);
      JTextField rowField = new JTextField(rowNum);
      rowField.setHorizontalAlignment(JTextField.CENTER);
      rowField.setEditable(false);
      rowField.setFocusable(false);
      rowPanel.add(rowField);
    }
  }

  /**
   * Creates and adds action listeners for the appropriate scroll mechanisms.
   * @param left left scroll button
   * @param right right scroll button
   * @param up up scroll button
   * @param down down scroll button
   */
  private void addButtonListeners(JButton left, JButton right, JButton up, JButton down) {
    left.addActionListener(e -> scroll("left"));
    right.addActionListener(e -> scroll("right"));
    up.addActionListener(e -> scroll("up"));
    down.addActionListener(e -> scroll("down"));

    this.scrollButtons.add(left);
    this.scrollButtons.add(right);
    this.scrollButtons.add(up);
    this.scrollButtons.add(down);
  }

  /**
   * Fills main panel grid with cells read in from the model.
   * @param panel main panel for the visual view
   */
  private void populateGrid(JPanel panel) {
    panel.removeAll();
    Cell cell;
    String cellStr;
    JTextField[][] cells = new JTextField[SCREENHEIGHT][SCREENWIDTH];

    for (int i = 0; i < SCREENHEIGHT; i++) {
      for (int j = 0; j < SCREENWIDTH; j++) {
        cell = model.getCellAt(new Coord(j + topLeftCol + 1, i + topLeftRow + 1));
        cellStr = cell == null ? "" : cell.getCellValue().toString();
        cells[i][j] = new JTextField(cellStr, 8);
        cells[i][j].setHorizontalAlignment(JTextField.RIGHT);

        panel.add(cells[i][j]);
      }
    }
  }

  /**
   * Updates the screen based on the width and height scroll lengths.
   * @param scrollDirection either left, right, up, down
   */
  private void scroll(String scrollDirection) {
    if (scrollDirection.equals("left")) {
      if (topLeftCol - SCREENWIDTH >= 0) {
        topLeftCol = topLeftCol - SCREENWIDTH;
      }
    }
    else if (scrollDirection.equals("right")) {
      topLeftCol += SCREENWIDTH;
    }
    else if (scrollDirection.equals("up")) {
      if (topLeftRow - SCREENHEIGHT >= 0) {
        topLeftRow = topLeftRow - SCREENHEIGHT;
      }
    }
    else {
      // down scroll
      topLeftRow += SCREENHEIGHT;
    }
    populateGrid(mainPanel);
    createHeaders(rowHeader, colHeader);
    this.refresh();
  }
}
