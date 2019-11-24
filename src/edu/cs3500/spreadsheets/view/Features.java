package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;

public interface Features {

  /**
   * <p>Updates the cell at the given {@link Coord}.</p>
   *
   * @param coord the coordinates of the cell being updated.
   * @param s     the new raw value of the cell.
   */
  void updateCell(Coord coord, String s);
}
