package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.ICell;
import edu.cs3500.spreadsheets.model.IWorksheet;
import java.util.ArrayList;

/**
 * Read-only access implementation of the {@link IWorksheet} interface.
 */
public class ReadWorksheet implements WorksheetViewModel {
  private final IWorksheet model;

  /**
   * Constructor for the model, with only read-access. No mutation.
   *
   * @param model final worksheet.
   */
  public ReadWorksheet(IWorksheet model) {
    this.model = model;
  }

  @Override
  public ArrayList<ICell> getCells() {
    return model.getCells();
  }

  @Override
  public ICell getCellAt(Coord coord) {
    return model.getCellAt(coord);
  }

  @Override
  public void editCellAt(Coord coord, String newContents) {
    // Because this is a read-only model, we should disallow editing to occur.
  }
}
