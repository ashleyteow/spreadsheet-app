package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Parser;

public class Cell {

  private Coord coord;
  private CellContents cellContents;

  public Cell(Coord coord, String unprocessedText) {
    this.coord = coord;
    if (unprocessedText.length() == 0) {
      this.cellContents = new Blank();
    }
    else if (unprocessedText.substring(0,1).equals("=")) {
      this.cellContents = new Formula(Parser.parse(unprocessedText));
    }
    else {
      this.cellContents = new Value(Parser.parse(unprocessedText));
    }
  }

  public CellContents getCellContents() {
    return this.cellContents;
  }
}
