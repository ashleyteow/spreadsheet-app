package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents a cell with a String in a worksheet and initiates the visitor pattern.
 */
public class ValueString extends Value {
  private final String strContents;

  public ValueString(String str) {
    this.strContents = str;
  }

  @Override
  public String toString() {
    return this.strContents;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ValueString)) {
      return false;
    }
    ValueString otherString = (ValueString) other;
    return this.strContents.equals(otherString.strContents);
  }

  @Override
  public int hashCode() {
    return this.strContents.hashCode();
  }


  @Override
  public <R> R accept(CellContentsVisitor<R> visitor) {
    return visitor.visitString(this);
  }

  @Override
  public edu.cs3500.spreadsheets.model.Value getVal() {
    return null;
  }

  @Override
  public void populateArgsHelp(ArrayList<Value> args) {
    args.add(this);
  }
}
