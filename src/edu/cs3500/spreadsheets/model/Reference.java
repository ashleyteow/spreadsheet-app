package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import java.util.ArrayList;

/**
 * Represents a region of cells in a {@code Worksheet}.
 */
public class Reference implements CellContents {
  private final ArrayList<Coord> referencedCells;
  private final Worksheet worksheet;
  private Coord thisCellLoc;

  /**
   * Constructs a {@code Reference} object representing a referenced region of cells.
   * @param firstCoord location of the cell referenced before colon
   * @param secondCoord location of the cell referenced after colon
   * @param thisCell location of cell that holds the reference (this cell)
   * @param worksheet worksheet model
   */
  public Reference(Coord firstCoord, Coord secondCoord, Coord thisCell, Worksheet worksheet) {
    this.referencedCells = new ArrayList<>();
    this.worksheet = worksheet;

    // TODO figure out the math to find all cells from firstCoord to secondCoord
    // adding cell to referencedCells
    int firstCoordRow = firstCoord.row;
    int secondCoordRow = secondCoord.row;
    int firstCoordCol = firstCoord.col;
    int secondCoordCol = secondCoord.col;

    for (int i = firstCoordCol; i <= secondCoordCol; i++) {
      for (int j = firstCoordRow; j <= secondCoordRow; j++) {
        if (!cyclePresent(new Coord(i, j))) {
          this.referencedCells.add(new Coord(i, j));
        } else {
          throw new IllegalArgumentException("cycle detected");
        }
      }
    }
  }

  /**
   * Constructs a {@code Reference} object representing a single referenced cell.
   * @param referencedCell cell that is being referenced
   * @param thisCell location of cell that holds the reference (this cell)
   * @param worksheet worksheet model
   */
  public Reference(Coord referencedCell, Coord thisCell, Worksheet worksheet) {
    this.thisCellLoc = thisCell;
    this.worksheet = worksheet;
    this.referencedCells = new ArrayList<>();
    if (!cyclePresent(referencedCell)) {
      this.referencedCells.add(referencedCell);
    }
  }

  /**
   * Determines whether a cell at the given coordinates initiates a cyclic reference.
   * @param coord location of cell to check
   * @return true if there is a cyclic reference, false otherwise
   */
  private boolean cyclePresent(Coord coord) {
    // TODO: check if this works
//    String otherCellRawVal = worksheet.getCellAt(coord).getRawValue();
//    if (otherCellRawVal.charAt(0) == '=' && otherCellRawVal.contains(this.thisCellLoc.toString())) {
//      return true;
//    }
//    return false;
//    return false;
    if (this.referencedCells.contains(coord) || this.thisCellLoc.equals(coord)) {
      throw new IllegalArgumentException("there cannot be cyclic references");
    }
    return false;
  }

  @Override
  public Value getVal() {
    if (referencedCells.size() == 1) {
      return worksheet.getCellAt(referencedCells.get(0)).getCellValue();
    }
    else {
      ArrayList<Sexp> coords = new ArrayList<>();
      for (Sexp s : coords) {
        coords.add(new SSymbol(s.toString()));
      }
      SList list = new SList(coords);
      return list.accept(new Evaluator(this.thisCellLoc, this.worksheet)).getVal();
    }
  }

  @Override
  public void populateArgsHelp(ArrayList<Value> args) {
    for (Coord c: this.referencedCells) {
      if (worksheet.getCells().contains(worksheet.getCellAt(c))) {
        args.add(worksheet.getCellAt(c).getCellValue());
      }
    }
  }

  @Override
  public String toString() {
    return getVal().toString();
  }
}
