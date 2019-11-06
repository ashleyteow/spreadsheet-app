package edu.cs3500.spreadsheets.model;
import java.util.ArrayList;

/**
 * A formula is a value and therefore inherits all methods from the
 * Value class. This class is responsible for processing the operation the user
 * intends to process.
 */
public class Formula implements CellContents {
  private final Operation operation;
  private ArrayList<CellContents> args;

  public Formula(Operation o, ArrayList<CellContents> contents) {
    this.operation = o;
    this.args = contents;
  }

  @Override
  public Value getVal() {
    return this.operation.apply(flatten());
  }

  /**
   * TODO: javadoc
   * @return
   */
  private ArrayList<Value> flatten() {
    ArrayList<Value> vals = new ArrayList<>();
    for (CellContents c: this.args) {
      c.flatten(vals);
    }
    return vals;
  }

  @Override
  public void flatten(ArrayList<Value> args) {
    args.add(getVal());
  }

  @Override
  public String toString() {
    return getVal().toString();
  }
}
