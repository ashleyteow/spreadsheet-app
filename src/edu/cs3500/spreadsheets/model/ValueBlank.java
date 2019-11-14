package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a blank cell in a worksheet and initiates the visitor pattern.
 */
public class ValueBlank extends Value {

  String blank;

  public ValueBlank() {
    blank = "";
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ValueBlank)) {
      return false;
    }
    ValueBlank otherBlank = (ValueBlank) other;

    return this.blank.equals(otherBlank.blank);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.blank);
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
