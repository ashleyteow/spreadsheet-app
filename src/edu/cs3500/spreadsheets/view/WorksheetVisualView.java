package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


/**
 * Represents a GUI view of a {@code Worksheet}.
 */
public class WorksheetVisualView extends JFrame implements WorksheetView {
  private Worksheet model;
  private WorksheetPanel worksheetPanel;
  private RowHeaderPanel rowHeader;
  private ColumnHeaderPanel colHeader;
  public static int SCREENWIDTH = 12;
  public static int SCREENHEIGHT = 20;
//  private JPanel rowHeader;
//  private JPanel colHeader;
  private int topLeftRow;
  private int topLeftCol;
  private JPanel navigateButtons;

  /**
   * Constructs a {@code WorksheetVisualView} object.
   * @param model worksheet
   */
  public WorksheetVisualView(Worksheet model) {
    this.setTitle("Excel");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.model = model;

    this.topLeftCol = 0;
    this.topLeftRow = 0;

    //    this.rowHeader = new JPanel(new GridLayout(SCREENHEIGHT, 1));
    this.rowHeader = new RowHeaderPanel(SCREENHEIGHT, topLeftRow);
    this.colHeader = new ColumnHeaderPanel(SCREENWIDTH, topLeftCol);
//    this.colHeader = new JPanel(new GridLayout(1, SCREENWIDTH + 1));
//    this.createHeaders();
//    this.rowHeader.createRowHeader();
//    this.colHeader.createColHeader();
//    this.createHeaders();

    this.worksheetPanel = new WorksheetPanel(SCREENHEIGHT, SCREENWIDTH);
    this.createHeaders();
    this.add(this.rowHeader, BorderLayout.NORTH);
    this.add(this.colHeader, BorderLayout.WEST);
//    this.worksheetPanel.setVisible(true);
    this.worksheetPanel.populateGrid(SCREENHEIGHT, SCREENWIDTH, topLeftCol, topLeftRow, this.model);

    this.navigateButtons = new JPanel(new GridLayout(2,2));
    JButton left = new JButton("⇦");
    JButton right = new JButton("⇨");
    JButton up = new JButton("⇧");
    JButton down = new JButton("⇩");
    this.setButtonListener(left, right, up, down);
    this.navigateButtons.setPreferredSize(new Dimension(100, 100));

//    JScrollPane sp = new JScrollPane(worksheetPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//    sp.setPreferredSize(new Dimension(SCREENHEIGHT, SCREENWIDTH));

    this.add(worksheetPanel, BorderLayout.CENTER);
    this.add(colHeader, BorderLayout.NORTH);
    this.add(rowHeader, BorderLayout.WEST);
    this.add(navigateButtons, BorderLayout.SOUTH);
//    this.add(sp, BorderLayout.EAST);
    this.pack();
    this.setVisible(true);
  }

//  /**
//   * Fills main panel grid with cells read in from the model.
//   */
//  public void populateGrid() {
//    this.worksheetPanel.removeAll();
//    Cell cell;
//    String cellStr;
//    JTextField[][] worksheetCells = new JTextField[SCREENHEIGHT][SCREENWIDTH];
//
//    for (int i = 0; i < SCREENHEIGHT; i++) {
//      for (int j = 0; j < SCREENWIDTH; j++) {
//        cell = model.getCellAt(new Coord(j + topLeftCol + 1, i + topLeftRow + 1));
//        cellStr = cell == null ? "" : cell.getCellValue().toString();
//        worksheetCells[i][j] = new JTextField(cellStr, 8);
//        worksheetCells[i][j].setHorizontalAlignment(JTextField.RIGHT);
//
//        this.worksheetPanel.add(worksheetCells[i][j]);
//      }
//    }
//  }

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
      if (topLeftCol - SCREENWIDTH >= 0) {
        topLeftCol = topLeftCol - SCREENWIDTH;
      }
    }
    else if (direction.equals("right")) {
      topLeftCol += SCREENWIDTH;
    }
    else if (direction.equals("up")) {
      if (topLeftRow - SCREENHEIGHT >= 0) {
        topLeftRow = topLeftRow - SCREENHEIGHT;
      }
    }
    else {
      // down scroll
      topLeftRow += SCREENHEIGHT;
    }
    this.worksheetPanel.populateGrid(SCREENHEIGHT, SCREENWIDTH, topLeftCol, topLeftRow, this.model);
    this.createHeaders();
    this.refresh();
  }


  @Override
  public void render() throws IOException {
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.pack();
    this.repaint();
  }

  /**
   * Fills the Coordinate headers with the row and column values.
   */
  public void createHeaders() {
//    this.rowHeader.removeAll();
//    this.colHeader.removeAll();
    this.rowHeader.createRowHeader();
    this.colHeader.createColHeader();
//    rowHeader.removeAll();
//    colHeader.removeAll();

//    for (int i = 0; i < SCREENWIDTH + 1; i++) {
//      String columnName = Coord.colIndexToName(i + topLeftCol + 1);
//      JLabel column = new JLabel(columnName);
//      column.setHorizontalAlignment(JTextField.CENTER);
//      column.setBackground(Color.darkGray);
//      column.setOpaque(true);
//      column.setForeground(Color.white);
//      column.setFocusable(false);
//      column.setBorder(new EmptyBorder(0,0,0,0));
//      colHeader.add(column);
//    }
//
//    for (int j = 0; j < SCREENHEIGHT; j++) {
//      String rowNum = Integer.toString(j + topLeftRow + 1);
//      JLabel row = new JLabel(rowNum);
//      row.setHorizontalAlignment(JTextField.CENTER);
//      row.setBackground(Color.darkGray);
//      row.setOpaque(true);
//      row.setForeground(Color.white);
//      row.setFocusable(false);
//      row.setBorder(new EmptyBorder(0,5,0,5));
//      rowHeader.add(row);
//    }
  }
}
