package edu.cs3500.spreadsheets.view;

import java.io.IOException;

/**
 * Represents methods needed to display view to user.
 */
public interface WorksheetView {

  /**
   * Displays implementation of view to user.
   * @throws IOException if appendable is unable to write
   */
  void render() throws IOException;

  /**
   * Updates the view when the user makes a change.
   * @throws IOException if appendable is unable to write
   */
  void refresh() throws IOException;

  /**
   * Adds listeners onto GUI components so that the controller is able execute command.
   */
  void addFeatures(FeaturesListener features);

  /**
   * Updates the view and displays the selected cell's raw value in the formula bar panel.
   * @param rawVal raw value to be displayed
   */
  void displayRawCellValue(String rawVal);

}
