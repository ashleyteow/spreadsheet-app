package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;


/**
 * Listens for events / actions a user could be doing when
 * interacting with the {@code WorksheetEditableView}.
 */
public interface FeaturesListener {

  /**
   * Confirms and updates the new cell's edited value and passes it to the model.
   * @param newVal new raw value
   * @return
   */
  void confirmEdits(String newVal);


  /**
   * Sets the coordinates of the cell being edited.
   * @param coord the coordinate of the cell being edited.
   */
  void setEditableCoord(Coord coord);

  /**
   * Gets the cell to be edited
   * @return the editable cell
   */
  Cell getCellToEdit();
}
