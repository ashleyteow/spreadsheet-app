package edu.cs3500.spreadsheets.provider.view;


import edu.cs3500.spreadsheets.provider.controller.Features;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * a spreadsheet view interface to display different types of spreadsheet views.
 */
public interface SpreadSheetView {

  /**
   * render the view for the user, save or display the view.
   */
  void render();

  /**
   * get the selected cell of txhe view in its coordinates.
   *
   * @return the selected cell of the view in its coordinates
   * @throws UnsupportedOperationException when the textual view call this method
   */
  Coord getSelected() throws UnsupportedOperationException;

  /**
   * Set the content from the given raw content.
   *
   * @param raw raw content from model to be set to the text field.
   */
  void setTextField(String raw);

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard listener attached
   * to it, so that keyboard events will still flow through.
   *
   * @attribute purpose statement from MVC Code
   */
  void resetFocus();

  /**
   * add listeners to the UI.
   *
   * @param features features to add
   */
  void addFeatures(Features features);

  /**
   * set the label content with given string at the given position.
   *
   * @param cord given position.
   * @param content content to set.
   */
  void setCellContent(Coord cord, String content);

  /**
   * close the current view.
   */
  void close();

  /**
   * save the view to the given file.
   *
   * @param fileName the given file name.
   */
  void saveFile(String fileName);
}
