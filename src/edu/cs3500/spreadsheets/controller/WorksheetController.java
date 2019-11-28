package edu.cs3500.spreadsheets.controller;

import java.io.IOException;

/**
 * Represents a controller for the worksheet that starts the editable view of the worksheet.
 */
public interface WorksheetController {

  /**
   * Starts the program and runs the worksheet.
   * @throws IOException if there is an input/output exception that occurs
   */
  void start() throws IOException;
}
