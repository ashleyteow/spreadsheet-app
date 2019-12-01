package edu.cs3500.spreadsheets.provider.view;

import edu.cs3500.spreadsheets.model.Coord;
import javax.swing.JLabel;

/**
 * A customized JLabel stores the coordinate of the cell, represents cell in a sheet.
 */
public class CellLabel extends JLabel {

  // the coordinate, set as final.
  public final Coord cord;

  /**
   * constructs a label represents a cell with its coordinate and label text.
   *
   * @param cord the Coordinate of the cell
   * @param text the text to be displayed
   * @param horizontalAlignment the alignment to arrange the text
   */
  public CellLabel(Coord cord, String text, int horizontalAlignment) {
    super(text, horizontalAlignment);
    this.cord = cord;
  }

}
