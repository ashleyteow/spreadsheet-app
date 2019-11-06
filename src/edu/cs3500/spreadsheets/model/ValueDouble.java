package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class ValueDouble extends Value {
  private final double doubleContent;

  public ValueDouble(double d) {
    this.doubleContent = d;
  }

  @Override
  public String toString() {
    return String.format("%f", this.doubleContent);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ValueDouble)) {
      return false;
    }
    ValueDouble otherDouble = (ValueDouble) other;

    return (Math.abs(this.doubleContent - otherDouble.doubleContent) < 0.0001);
  }

  @Override
  public int hashCode() {
    Double d = this.doubleContent;
    return d.hashCode();
  }

  @Override
  public <R> R accept(CellContentsVisitor<R> visitor) {
    return visitor.visitDouble(this);
  }

  @Override
  public void populateArgs(ArrayList<Value> args) {
    args.add(this);
  }
}
