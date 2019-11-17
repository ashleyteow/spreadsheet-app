package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ColumnHeaderPanel extends JPanel {
  private JPanel colPanel;
  private int screenWidth;
  private int topLeftCol;

  public ColumnHeaderPanel(int screenWidth, int topLeftCol) {
    this.screenWidth = screenWidth;
    this.topLeftCol = topLeftCol;
    this.colPanel = new JPanel();
    createColHeader();
    this.setLayout(new GridLayout(1, screenWidth + 1));
  }

  public void createColHeader() {
    this.colPanel.removeAll();

    for (int i = 0; i < screenWidth + 1; i++) {
      String columnName = Coord.colIndexToName(i + topLeftCol + 1);
      JLabel column = new JLabel(columnName);
      column.setHorizontalAlignment(JTextField.CENTER);
      column.setBackground(Color.darkGray);
      column.setOpaque(true);
      column.setForeground(Color.white);
      column.setFocusable(false);
      column.setBorder(new EmptyBorder(0,0,0,0));
      colPanel.add(column);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

}
