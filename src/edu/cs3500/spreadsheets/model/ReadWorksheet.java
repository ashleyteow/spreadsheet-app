package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Read-only access implementation of the {@link IWorksheet} interface.
 */
public class ReadWorksheet implements IWorksheet {
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
  public ArrayList<Cell> getCells() {
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
