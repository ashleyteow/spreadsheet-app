package edu.cs3500.spreadsheets.controller;

import java.io.IOException;

/**
 * An interface to represent the controller for an editable worksheet.
 */
public interface WorksheetController {

  /**
   * Starts the program and runs the worksheet.
   * @throws IOException when an IOException is detected
   */
  void start() throws IOException;
}
