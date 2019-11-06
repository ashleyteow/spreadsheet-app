package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class ValueError extends Value {

  @Override
  public <R> R accept(CellContentsVisitor<R> visitor) {
    return visitor.visitError(this);
  }

  @Override
  public void populateArgs(ArrayList<Value> args) {
    args.add(this);
  }

  @Override
  public String toString() {
    return "#NAME";
  }
}
