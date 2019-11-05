package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;
import java.util.Objects;

/**
 * Represents an implementation of CellContents. This class represents if a
 * cell's contents do not begin with an equal sign and is therefore simply
 * a value in the spreadsheet.
 */
public abstract class Value implements CellContents {

  public abstract <R> R accept(CellContentsVisitor<R> visitor);

  @Override
  public Value getVal() {
    return this;
  }

//
//
//  @Override
//  public Sexp evaluate() {
//    return this.cellContent;
//  }
//
//  @Override
//  public boolean equals(Object other) {
//    if (!(other instanceof Value)) {
//      return false;
//    }
//    Value val = (Value) other;
//    val.hashCode();
//    return val.cellContent.equals(((Value) other).cellContent);
//  }
//
//  @Override
//  public String toString() {
//    return this.cellContent.toString();
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(cellContent);
//  }
}
