package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import java.util.ArrayList;

/**
 * Represents a single spreadsheet that contains a grid of cells. Uses composition to
 * build a worksheet.
 */
public class Worksheet {
  private ArrayList<ArrayList<Cell>> cells;

  /**
   * Creates a new Builder object that is responsible for producing a new Worksheet.
    * @return Builder object
   */
  public static Builder builder() {
    return new Builder(200);
  }

  /**
   * Helper class that fills in the grid of cells with its evaluated contents.
   */
  public static final class Builder implements WorksheetBuilder<Worksheet> {
    private ArrayList<ArrayList<Cell>> workSheetCells;

    /**
     * Constructs a {@code Builder} object initalized with an initialCapacity to set
     * default number of rows and columns in the spreadsheet.
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
      workSheetCells.get(col-1).set(row-1, cell);
      return this;
    }

    @Override
    public Worksheet createWorksheet() {
      // calling this method should also *i believe* fill in whitespace
      return new Worksheet(workSheetCells);
    }
  }

  /**
   * Constructs a {@code Worksheet} object with a given arraylist of cells
   * that have been processed and evaluated through the Builder pattern.
   * @param cells all cells in this spreadsheet
   */
  private Worksheet(ArrayList<ArrayList<Cell>> cells) {
    this.cells = cells;
  }

  /**
   * Getter method to access this worksheet's grid of cells.
   * @return all cells in this spreadsheet
   */
  public ArrayList<ArrayList<Cell>> getCells() {
    return this.cells;
  }

/**
 * Returns the cell at the specified coordinates.
 * @param col  column of the desired cell
 * @param row  row of the desired cell
 * @return the cell at the given position, or <code>null</code> if no card is there
 * @throws IllegalArgumentException if the coordinates are invalid
 */
  public Cell getCellAt(int col, int row) throws IllegalArgumentException {
    if (col < 0 || row < 0 || col > this.getCells().size()
        || row > this.getCells().size()) {
      throw new IllegalArgumentException("Coordinates out of bound!");
    }
    return this.getCells().get(col).get(row);
  }
}
