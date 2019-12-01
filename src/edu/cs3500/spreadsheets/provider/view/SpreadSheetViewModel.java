package edu.cs3500.spreadsheets.provider.view;

import java.util.Map;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.IFormula;

/**
 * an immutable read-only model for view.
 */
public interface SpreadSheetViewModel {
  /**
   * get the current sheet of the model.
   *
   * @return the sheet of the model.
   */
  HashMap<Coord, IFormula> getSheet();

  /**
   * get the max coordinate among cells in the sheet.
   *
   * @return the max coordinate among cells in the sheet
   */
  Coord getMaxCoord();

  /**
   * retrieve the raw content of the cell in the given coordinate.
   *
   * @return the raw content of the cell;
   */
  String retrieve(int col, int row);


  /**
   * get all the raw contents of every cell.
   *
   * @return a raw content collection
   */
  Map<Coord,String> getAllRawContents();
}
