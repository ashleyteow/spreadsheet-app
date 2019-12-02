package edu.cs3500.spreadsheets.provider.view;


import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * a class represents the column and row header of the gui.
 * extending a Jpanel to contain all cells in the header.
 * with a default cell width and heighth for every cell in the header.
 */
public class HeaderPanel extends JPanel {
  private boolean isVertical;
  private int wid;


  /**
   * construct a header whose max width is the given integer.
   *
   * @param width the width of the header.
   */
  public HeaderPanel(int width, boolean isVertical) {
    super();
    wid = Math.max(width, SpreadSheetPanel.DEFAULT_SIZE);
    this.isVertical = isVertical;
    if (!this.isVertical) {
      this.setLayout(new GridLayout(1, wid));
      for (int i = 1; i <= wid; i++) {
        Border b = BorderFactory.createLineBorder(Color.black);
        JLabel jl = new JLabel(Coord.colIndexToName(i), SwingConstants.CENTER);
        makeCell(b, jl);
      }
    } else {
      this.setLayout(new GridLayout(wid, 1));
      //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      for (int i = 1; i <= wid; i++) {
        Border b = BorderFactory.createLineBorder(Color.black);
        JLabel jl = new JLabel(Integer.toString(i), SwingConstants.CENTER);
        makeCell(b, jl);
      }
    }
  }

  private void makeCell(Border b, JLabel jl) {
    jl.setPreferredSize(new Dimension(SpreadSheetPanel.Cell_Width, SpreadSheetPanel.Cell_Heighth));
    jl.setOpaque(true);
    jl.setBackground(Color.LIGHT_GRAY);
    jl.setBorder(b);
    this.add(jl);
  }


  protected void addOneUnit() {
    Border b = BorderFactory.createLineBorder(Color.black);
    JLabel jl;
    if (!this.isVertical) {
      this.wid++;
      this.setLayout(new GridLayout(1, wid));
      jl = new JLabel(Coord.colIndexToName(wid), SwingConstants.CENTER);

    }
    else {
      this.wid++;
      this.setLayout(new GridLayout(wid, 1));
      jl = new JLabel(Integer.toString(wid), SwingConstants.CENTER);
    }

    makeCell(b, jl);
    this.add(jl);
  }
}
