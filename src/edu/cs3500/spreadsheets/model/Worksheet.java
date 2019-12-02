package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a single spreadsheet that contains a grid of cells. Uses composition to build a
 * worksheet.
 */
public class Worksheet implements IWorksheet {

  private HashMap<Coord, Cell> cells;

  public Worksheet() {
    this.cells = new HashMap<>();
  }

  /**
   * Helper class that builds a {@code Worksheet}.
   */
  public static final class Builder implements WorksheetBuilder<Worksheet> {

    private ArrayList<Cell> workSheetCells = new ArrayList<>();
    Worksheet worksheet = new Worksheet();

    @Override
    public WorksheetBuilder<Worksheet> createCell(int col, int row, String contents) {
      Coord coordinate = new Coord(col, row);
      Cell cell = new Cell(coordinate, contents, worksheet);
      workSheetCells.add(cell);
      return this;
    }

    @Override
    public Worksheet createWorksheet() {
      for (Cell c : workSheetCells) {
        worksheet.editCellAt(c.getCoord(), c.getRawValue());
      }
      return worksheet;
    }
  }

  @Override
  public ArrayList<ICell> getCells() {
    ArrayList<ICell> copy = new ArrayList<>();

    for (ICell c : cells.values()) {
      copy.add(c);
    }

    return copy;
  }

  @Override
  public Cell getCellAt(Coord coord) {
    return this.cells.getOrDefault(coord, new Cell(coord));
  }

  @Override
  public void editCellAt(Coord coord, String newContents) throws IllegalArgumentException {
    if (!this.cells.containsKey(coord)) {
      this.cells.put(coord, new Cell(coord, newContents, this));
    }
    this.cells.get(coord).setCellContent(newContents, this);
  }
}
