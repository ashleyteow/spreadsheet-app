package edu.cs3500.spreadsheets.provider.model;

import edu.cs3500.spreadsheets.model.Coord;
import java.util.List;

import edu.cs3500.spreadsheets.provider.view.SpreadSheetViewModel;

/**
 * an interface represent a spreadsheet filled in with cells.
 */
public interface SpreadSheet extends SpreadSheetViewModel {
  /**
   * evaluate a cell at the given coordinate.
   *
   * @param col collmn of coord
   * @param row row of coord
   * @return the result of the evaluated cell in a String form
   */
  String evaluate(int col, int row);

  /**
   * set the cell with the raw content.
   *
   * @param col collmn of coord
   * @param row row of coord
   * @param contents raw contents
   */
  void setCell(int col,int row, String contents);

  /**
   * retrieve the raw content of a cell.
   *
   * @param col collmn of coord
   * @param row row of coord
   * @return the raw contents of a cell as a string.
   */
  String retrieve(int col,int row);

  /**
   * get he dependencies of the given coordinate.
   * @param cord the coordinate to get.
   * @return list of coordinates represents the dependencies.
   */
  List<Coord> getDependencies(Coord cord);


}
