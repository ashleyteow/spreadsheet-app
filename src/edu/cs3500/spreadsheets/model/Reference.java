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
  private final IWorksheet worksheet;
  private Coord thisCellLoc;

  /**
   * Constructs a {@code Reference} object representing a referenced region of cells.
   *
   * @param firstCoord  location of the cell referenced before colon
   * @param secondCoord location of the cell referenced after colon
   * @param thisCell    location of cell that holds the reference (this cell)
   * @param worksheet   worksheet model
   */
  public Reference(Coord firstCoord, Coord secondCoord, Coord thisCell, IWorksheet worksheet) {
    this.thisCellLoc = thisCell;
    this.referencedCells = new ArrayList<>();
    this.worksheet = worksheet;

    // adding cell to referencedCells
    int firstCoordRow = firstCoord.row;
    int secondCoordRow = secondCoord.row;
    int firstCoordCol = firstCoord.col;
    int secondCoordCol = secondCoord.col;

    for (int i = firstCoordCol; i <= secondCoordCol; i++) {
      for (int j = firstCoordRow; j <= secondCoordRow; j++) {
        this.referencedCells.add(new Coord(i, j));
      }
    }

    if (cyclePresent(this.referencedCells)) {
      throw new IllegalArgumentException("no cycles can exist");
    }
  }

  /**
   * Constructs a {@code Reference} object representing a single referenced cell.
   *
   * @param referencedCell cell that is being referenced
   * @param thisCell       location of cell that holds the reference (this cell)
   * @param worksheet      worksheet model
   */
  public Reference(Coord referencedCell, Coord thisCell, IWorksheet worksheet) {
    this.thisCellLoc = thisCell;
    this.worksheet = worksheet;
    this.referencedCells = new ArrayList<>();
    this.referencedCells.add(referencedCell);
    if (cyclePresent(referencedCells)) {
      throw new IllegalArgumentException("no cycles can exist");
    }
  }

  /**
   * Determines whether a cell at the given coordinates initiates a cyclic reference.
   *
   * @param refCells list of referenced cells
   * @return true if there is a cyclic reference, false otherwise
   */
  private boolean cyclePresent(ArrayList<Coord> refCells) {
    boolean cycle = false;
    if (refCells.contains(this.thisCellLoc)) {
      cycle = true;
    }
    return cycle;
  }

  @Override
  public Value getVal() {
    if (referencedCells.size() == 1) {
      return worksheet.getCellAt(referencedCells.get(0)).getCellValue();
    } else {
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
    for (Coord c : this.referencedCells) {
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
