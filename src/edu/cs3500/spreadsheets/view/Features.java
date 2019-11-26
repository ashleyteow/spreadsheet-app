package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * Performs the actual function that manipulates the {@code WorksheetEditableView} in some form.
 */
public interface Features {

  /**
   * Updates the cell at the given location {@link Coord}.</p>
   *
   * @param coord the coordinates of the cell being updated.
   * @param newVal    the new raw value of the cell.
   */
  void updateCell(Coord coord, String newVal);
}
