package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a single spreadsheet that contains a grid of cells. Uses composition to build a
 * worksheet.
 */
public class Worksheet implements IWorksheet {
//  private ArrayList<ArrayList<Cell>> cells;
    private HashMap<Coord, Cell> cells;

    public Worksheet() {
      this.cells  = new HashMap<>();
    }
//  public Worksheet(int initialCapacity) {
//    this.cells = new ArrayList<>(initialCapacity);
//    for (int col = 0; col < initialCapacity; col++) {
//      ArrayList<Cell> row = new ArrayList<>(initialCapacity);
//      for (int rowNum = 0; rowNum < initialCapacity; rowNum++) {
//        // intializes all cells with ValueBlank
//        row.add(new Cell(new Coord(col + 1, rowNum + 1), "", this));
//      }
//      cells.add(row);
//    }
//  }

  /**
   * Helper class that builds a {@code Worksheet}.
   */
  public static final class Builder implements WorksheetBuilder<Worksheet> {
    private ArrayList<Cell> workSheetCells = new ArrayList<>();
//    Worksheet worksheet = new Worksheet(200);
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
        worksheet.addCellAt(c.getCoord(), c.getRawValue());
      }
      return worksheet;
    }
  }

  @Override
  public ArrayList<Cell> getCells() {
    // TODO: test to see if this returns a copy
    ArrayList<Cell> copy = new ArrayList<>();

    for (Cell c : cells.values()) {
      copy.add(c);
    }

    return copy;
  }

  @Override
  public Cell getCellAt(Coord coord) throws IllegalArgumentException {
//    if (invalidCoord(coord)) {
//      throw new IllegalArgumentException("Coordinate is invalid!");
//    }
    return this.cells.get(coord);
  }

  @Override
  public String getCellRaw(Coord coord) {
    try {
      return this.cells.get(coord).getRawValue();
    } catch (NullPointerException e) {
      return new Cell(coord).getRawValue();
    }
  }

  @Override
  public CellContents evaluateSingleCell(Coord coord) throws IllegalArgumentException {
//    if (invalidCoord(coord)) {
//      throw new IllegalArgumentException("Coordinate is invalid!");
//    }
    return this.cells.get(coord).getCellValue();
  }

  @Override
  public void editCellAt(Coord coord, String newContents) throws IllegalArgumentException {
//    if (invalidCoord(coord)) {
//      throw new IllegalArgumentException("Coordinate is invalid!");
//    }
    this.cells.get(coord).setCellContent(newContents, this);
  }

  public void addCellAt(Coord coord, String newContents) throws IllegalArgumentException {
    this.cells.put(coord, new Cell(coord, newContents, this));
  }

//  @Override
//  public boolean evaluateCells() {
//    boolean valid = true;
//    for (int i = 0; i < this.cells.size(); i++) {
//      for (int j = 0; j < this.cells.get(i).size(); j++) {
//        if (this.cells.get(i).get(j) == null) {
//          valid = false;
//        }
//      }
//    }
//    return valid;
//  }

  /**
   * Determines whether the given coordinate is not in the boundaries of this sheet.
   *
   * @param coord location of the cell
   * @return true if coord is out of bounds/invalid, false otherwise
   */
  private boolean invalidCoord(Coord coord) {
    return coord.col < 0 || coord.col > this.cells.size()
        || coord.row < 0 || coord.row > this.cells.size();
  }

  /**
   * TODO: javadoc
   * @param grid
   * @return
   */
  public static List<Cell> flattenCells(HashMap<Coord, Cell> grid) {
    List<Cell> allCells = new ArrayList<>();

    for (Cell c : grid.values()) {
      allCells.add(c);
    }
    return allCells;
  }
}
