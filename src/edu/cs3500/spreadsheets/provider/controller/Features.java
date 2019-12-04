package edu.cs3500.spreadsheets.provider.controller;

import java.io.File;

/**
 * different provided features for users to make edit with spreadsheet.
 */
public interface Features {

  /**
   * set the evaluated content to the cell at the given coordinate.
   * and set the raw content of the cell at the typed are.
   *
   * @param str the typed input
   * @param col column of the cell
   * @param row row of the cell
   */
  void editCell(String str, int col,int row);

  /**
   * undo the current edit and restore the raw content of the clicked cell.
   */
  void restore();

  /**
   * enable the controller.
   */
  void start();

  /**
   * load the given file and provide a new gui.
   *
   * @param file the chosen file to be loaded
   */
  void load(File file);
}
