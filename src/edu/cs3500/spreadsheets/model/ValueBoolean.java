package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class ValueBoolean extends Value {
  private final boolean booleanContent;

  public ValueBoolean(boolean b) {
    this.booleanContent = b;
  }

  @Override
  public String toString() {
    return String.valueOf(this.booleanContent);
  }


  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof ValueBoolean)) {
      return false;
    }

    ValueBoolean otherBoolean = (ValueBoolean) other;

    return (this.booleanContent == otherBoolean.booleanContent);
  }

  @Override
  public int hashCode() {
    if (this.booleanContent) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public <R> R accept(CellContentsVisitor<R> visitor) {
    return visitor.visitBoolean(this);
  }

  @Override
  public void populateArgs(ArrayList<Value> args) {
    args.add(this);
  }
}
