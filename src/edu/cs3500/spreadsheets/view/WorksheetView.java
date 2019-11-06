package edu.cs3500.spreadsheets.view;

import java.io.IOException;

public interface WorksheetView {

  /**
   * Displays implementation of view to user.
   * @throws IOException if appendable is unable to write
   */
  void render() throws IOException;

}
