package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import java.util.ArrayList;

/**
 * Represents a single spreadsheet that contains a grid of cells. Uses composition to build a
 * worksheet.
 */
public class Worksheet implements IWorksheet {

  private ArrayList<ArrayList<Cell>> cells;

  /**
   * Helper class that fills in the grid of cells with its evaluated contents.
   */
  public static final class Builder implements WorksheetBuilder<Worksheet> {

    private ArrayList<ArrayList<Cell>> workSheetCells;

    /**
     * Constructs a {@code Builder} object initalized with an initialCapacity to set default number
     * of rows and columns in the spreadsheet.
     *
     * @param initialCapacity default number of rows / columns
     */
    private Builder(int initialCapacity) {
      workSheetCells = new ArrayList<>(initialCapacity);
      for (int col = 0; col < initialCapacity; col++) {
        ArrayList<Cell> row = new ArrayList<>(initialCapacity);
        for (int rowNum = 0; rowNum < initialCapacity; rowNum++) {
          row.add(new Cell(new Coord(col + 1, rowNum + 1), ""));
        }
        workSheetCells.add(row);
      }
    }

    @Override
    public WorksheetBuilder<Worksheet> createCell(int col, int row, String contents) {
      Coord coordinate = new Coord(col, row);
      Cell cell = new Cell(coordinate, contents);
      workSheetCells.get(col - 1).set(row - 1, cell);
      return this;
    }

    @Override
    public Worksheet createWorksheet() {
      return new Worksheet(workSheetCells);
    }
  }

  /**
   * Creates a new Builder object that is responsible for producing a new Worksheet.
   *
   * @return Builder object
   */
  public static Builder builder() {
    return new Builder(200);
  }

  /**
   * Constructs a {@code Worksheet} object with a given arraylist of cells that have been processed
   * and evaluated through the Builder pattern.
   *
   * @param cells all cells in this spreadsheet
   */
  private Worksheet(ArrayList<ArrayList<Cell>> cells) {
    this.cells = cells;
  }

  @Override
  public ArrayList<ArrayList<Cell>> getCells() {
    return this.cells;
  }

  @Override
  public Cell getCellAt(int col, int row) throws IllegalArgumentException {
    if (col < 0 || row < 0 || col >= this.getCells().size()
        || row >= this.getCells().size()) {
      throw new IllegalArgumentException("Coordinates out of bound!");
    }
    return new Cell(new Coord(col, row), this.getCells().get(col).get(row).getCellContents());
  }

  @Override
  public void addCell(Coord location, String content) throws IllegalArgumentException {
    if (location == null) {
      throw new IllegalArgumentException("Location cannot be null!");
    }

  }

  @Override
  public CellContents evaluateSingleCell(Coord location) throws IllegalArgumentException {
    return null;
  }

  @Override
  public void deleteCellAt(int col, int row) {
    this.cells.get(col).set(row, new Cell(new Coord(col, row), ""));
  }

  @Override
  public void editCellAt(int col, int row, String newContents) {
    this.cells.get(col).set(row, new Cell(new Coord(col, row), newContents));
  }

  @Override
  public boolean evaluateCells() {
    boolean valid = true;
    for (int i = 0; i < this.cells.size(); i++) {
      for (int j = 0; j < this.cells.get(i).size(); j++) {
        if (this.cells.get(i).get(j) == null) {
          valid = false;
        }
      }
    }
    return valid;
  }
}
