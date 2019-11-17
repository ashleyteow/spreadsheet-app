package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RowHeaderPanel extends JPanel {
  private JPanel rowPanel;
  private int screenHeight;
  private int topLeftRow;

  public RowHeaderPanel(int screenHeight, int topLeftRow) {
    this.screenHeight = screenHeight;
    this.topLeftRow = topLeftRow;
    this.rowPanel = new JPanel();
    createRowHeader();
    this.setLayout(new GridLayout(screenHeight, 1));
  }

  public void createRowHeader() {
    this.rowPanel.removeAll();

    for (int j = 0; j < screenHeight; j++) {
      String rowNum = Integer.toString(j + topLeftRow + 1);
      JLabel row = new JLabel(rowNum);
      row.setHorizontalAlignment(JTextField.CENTER);
      row.setBackground(Color.darkGray);
      row.setOpaque(true);
      row.setForeground(Color.white);
      row.setFocusable(false);
      row.setBorder(new EmptyBorder(0,5,0,5));
      rowPanel.add(row);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

}
