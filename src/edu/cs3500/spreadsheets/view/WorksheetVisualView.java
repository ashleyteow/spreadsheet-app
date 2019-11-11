package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.Worksheet.Builder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WorksheetVisualView extends JFrame implements WorksheetView {
  private Worksheet model;
  private JPanel mainPanel;
  private JTextField[][] cells = new JTextField[SCREENHEIGHT][SCREENWIDTH];
  private JPanel scrollButtons;
  private JPanel rowHeader;
  private JPanel colHeader;
  private static int SCREENWIDTH = 12;
  private static int SCREENHEIGHT = 20;
  private int rowOffset;
  private int colOffset;


  public WorksheetVisualView(Worksheet model) {
    this.model = model;
    this.rowOffset = 0;
    this.colOffset = 0;
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
    this.scrollButtons.add(left);
    this.scrollButtons.add(right);
    this.scrollButtons.add(up);
    this.scrollButtons.add(down);
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
      String columnName = Coord.colIndexToName(i + colOffset + 1);
      JTextField columnField = new JTextField(columnName);
      columnField.setHorizontalAlignment(JTextField.CENTER);
      columnField.setFocusable(false);
      columnField.setEditable(false);
      colPanel.add(columnField);
    }

    for (int j = 0; j < SCREENHEIGHT; j++) {
      String rowNum = Integer.toString(j + rowOffset + 1);
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
  }

  /**
   * Fills main panel grid with cells read in from the model.
   * @param panel main panel for the visual view
   */
  private void populateGrid(JPanel panel) {
    panel.removeAll();
    Cell cell;
    String cellStr;

    for (int i = 0; i < SCREENHEIGHT; i++) {
      for (int j = 0; j < SCREENWIDTH; j++) {
        cell = model.getCellAt(new Coord(j + colOffset + 1, i + rowOffset + 1));
        cellStr = cell == null ? "" : cell.getCellValue().toString();
        cells[i][j] = new JTextField(cellStr, 8);
        cells[i][j].setHorizontalAlignment(JTextField.RIGHT);

        panel.add(cells[i][j]);
      }
    }
  }

  /**
   * Updates the screen based on the width and height scroll lengths
   * @param scrollDirection either left, right, up, down
   */
  private void scroll(String scrollDirection) {
    switch (scrollDirection) {
      case "left":
        if (colOffset - SCREENWIDTH >= 0) {
          colOffset = colOffset - SCREENWIDTH;
        }
        break;
      case "right":
        colOffset += SCREENWIDTH;
        break;
      case "up":
        if (rowOffset - SCREENHEIGHT >= 0) {
          rowOffset = rowOffset - SCREENHEIGHT;
        }
        break;
      case "down":
        rowOffset += SCREENHEIGHT;
        break;
      default:
        colOffset = 0;
        rowOffset = 0;
        break;
    }
    populateGrid(mainPanel);
    createHeaders(rowHeader, colHeader);
    this.refresh();
  }

  public static void main(String[] args) throws IOException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("testParse1"));
    WorksheetView view = new WorksheetVisualView(testWorksheet);
    view.render();
  }
}
