package edu.cs3500.spreadsheets.model;
import java.util.ArrayList;

/**
 * A formula is a value and therefore inherits all methods from the
 * Value class. This class is responsible for processing the operation the user
 * intends to process.
 */
public class Formula implements CellContents {
  private ArrayList<CellContents> args;
  private final Operation operation;

  public Formula(Operation o, ArrayList<CellContents> contents) {
    this.operation = o;
    this.args = contents;
  }

  @Override
  public Value getVal() {
    return this.operation.apply(populateArgsHelp());
  }

  /**
   * Prepares the arraylist of values to pass to the apply method for the appropriate function.
   * @return args to execute the appropriate operation
   */
  private ArrayList<Value> populateArgsHelp() {
    ArrayList<Value> vals = new ArrayList<>();
    for (CellContents c: this.args) {
      c.populateArgs(vals);
    }
    return vals;
  }

  @Override
  public void populateArgs(ArrayList<Value> args) {
    args.add(getVal());
  }

  @Override
  public String toString() {
    return getVal().toString();
  }
}
