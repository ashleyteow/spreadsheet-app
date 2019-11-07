package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class ValueBlank extends Value {
  @Override
  public String toString() {
    return "";
  }

  @Override
  public <R> R accept(CellContentsVisitor<R> visitor) {
    return visitor.visitBlank(this);
  }

  @Override
  public void populateArgsHelp(ArrayList<Value> args) {
    // nothing to flatten if this is a blank valued cell
  }
}
