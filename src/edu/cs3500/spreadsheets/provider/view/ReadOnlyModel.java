package edu.cs3500.spreadsheets.provider.view;

import java.util.HashMap;
import java.util.Map;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.IFormula;
import edu.cs3500.spreadsheets.model.SpreadSheet;


/**
 * an read-only model implementation to use adapter pattern that blocks out the mutable access
 * to the model by composition.
 */
public class ReadOnlyModel implements SpreadSheetViewModel {
  private final SpreadSheet model;

  public ReadOnlyModel(SpreadSheet model) {
    this.model = model;
  }

  @Override
  public HashMap<Coord, IFormula> getSheet() {
    return model.getSheet();
  }

  @Override
  public Coord getMaxCoord() {
    return model.getMaxCoord();
  }

  @Override
  public String retrieve(int col, int row) {
    return model.retrieve(col,row);
  }

  @Override
  public Map<Coord, String> getAllRawContents() {
    return model.getAllRawContents();
  }
}
