package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;


/**
 * Listens for events / actions a user could be doing when interacting with the {@code
 * WorksheetEditableView}.
 */
public interface FeaturesListener {

  /**
   * User confirms and updates the new cell's edited value and passes it to the model.
   *
   * @param newVal new raw value
   */
  void confirmEdits(String newVal);

  /**
   * User clicks on the cell on GUI.
   *
   * @return the editable cell
   */
  void getCellToEdit();

  /**
   * User wants to cancel the edits made in the {@code FormulaBarPanel}.
   *
   */
  void rejectEdits();

  /**
   * Updates the focused cell in {@code WorksheetGUIController}.
   *
   * @param coord
   */
  void setCoordToEdit(Coord coord);
}
