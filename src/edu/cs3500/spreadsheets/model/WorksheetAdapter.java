package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.model.IFormula;
import edu.cs3500.spreadsheets.provider.view.SpreadSheetViewModel;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

/**
 * DELETE THIS: this is here to help us understand this class
 * WorksheetAdapter is-a SpreadSheetViewModel and therefore is able to be used with their
 * view interfaces.
 */
public class WorksheetAdapter implements SpreadSheetViewModel {
  private Worksheet ourModel;

  public WorksheetAdapter(Worksheet ourModel) {
    this.ourModel = ourModel;
  }

  @Override
  public HashMap<Coord, IFormula> getSheet() {
    // this is probably where we have to turn our values into their formula
    return null;
  }

  @Override
  public Coord getMaxCoord() {
    // call ourModel.getCells() which will return ArrayList<ICell>
    // iterate through cells to get the maximum coord
    return null;
  }

  @Override
  public String retrieve(int col, int row) {
    return ourModel.getCellAt(new Coord(col, row)).getRawValue();
  }

  @Override
  public Map<Coord, String> getAllRawContents() {
    Map<Coord, String> rawCells = new HashMap<>();
    ArrayList<ICell> allCells = ourModel.getCells();
    for (ICell c : allCells) {
      rawCells.put(c.getCoord(), c.getRawValue());
    }
    return rawCells;
  }

  @Override
  public String evaluate(int col, int row) {
    return ourModel.getCellAt(new Coord(col, row)).getCellValue().toString();
  }
}
