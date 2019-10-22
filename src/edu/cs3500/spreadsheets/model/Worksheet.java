package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import edu.cs3500.spreadsheets.sexp.SString;
import java.util.ArrayList;

public class Worksheet implements WorksheetBuilder<ArrayList<ArrayList<Cell>>> {

  private ArrayList<ArrayList<Cell>> cellList = new ArrayList<ArrayList<Cell>>();

  public Worksheet() {
    for (int i = 0; i < 200; i++) {
      cellList.add(new ArrayList<Cell>(706));
    }
  }

  @Override
  public WorksheetBuilder<ArrayList<ArrayList<Cell>>>
  createCell(int col, int row, String contents) {
    if (col < 1 || row < 1) {
      throw new IllegalArgumentException("invalid cell");
    }

    if (contents == null) {
      cellList.get(row).set(col, new Cell(new Coord(col, row), new Blank()));
    } else if (contents.substring(0, 1).equals("=")) {
      cellList.get(row).set(col, new Cell(new Coord(col, row),
          new Formula(new SString(contents.substring(1)))));
    } else {
      cellList.get(row).set(col, new Cell(new Coord(col, row),
          new Value(new SString(contents.substring(1)))));
    }
    return this;
  }

  @Override
  public ArrayList<ArrayList<Cell>> createWorksheet() {
    for (int i = 0; i < cellList.size(); i++) {
      for (int j = 0; j < cellList.get(i).size(); j++) {
        this.createCell(i, j, "contents");
      }
    }
    return
  }

}
