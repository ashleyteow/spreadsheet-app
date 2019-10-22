package edu.cs3500.spreadsheets.model;

public class Cell {

  private Coord coord;
  private CellContents cellContents;

  public Cell(Coord coord, CellContents cellContents) {
    this.coord = coord;
    this.cellContents = cellContents;
  }

}
