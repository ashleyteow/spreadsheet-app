package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.view.SpreadSheetViewModel;
import java.util.Map;

public class WorksheetAdapter implements SpreadSheetViewModel {
  private Worksheet originalWorksheet;

  @Override
  public HashMap<Coord, IFormula> getSheet() {
    return null;
  }

  @Override
  public Coord getMaxCoord() {
    return null;
  }

  @Override
  public String retrieve(int col, int row) {
    return null;
  }

  @Override
  public Map<Coord, String> getAllRawContents() {
    return null;
  }
}
