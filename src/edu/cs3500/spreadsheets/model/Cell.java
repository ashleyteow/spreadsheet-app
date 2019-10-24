package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Parser;

public class Cell {

  private Coord coord;
  private CellContents cellContents;

  public Cell(Coord coord, CellContents unprocessedText) {
    this.coord = coord;
  }



}
