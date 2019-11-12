package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents an error cell in a worksheet and initiates the visitor pattern.
 */
public class ValueError extends Value {

  @Override
  public <R> R accept(CellContentsVisitor<R> visitor) {
    return visitor.visitError(this);
  }

  @Override
  public void populateArgsHelp(ArrayList<Value> args) {
    args.add(this);
  }

  @Override
  public String toString() {
    return "#NAME";
  }
}
