package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Performs the less than (<) operation for a given formula in a cell.
 */
public class LessThan implements Operation, CellContentsVisitor<Double> {

  @Override
  public Double visitString(ValueString v) {
    return null;
    // TODO: throw exception??
  }

  @Override
  public Double visitDouble(ValueDouble v) {
    return Double.valueOf(v.toString());
  }

  @Override
  public Double visitBlank(ValueBlank v) {
    return null;
    // TODO: throw exception??
  }

  @Override
  public Double visitBoolean(ValueBoolean v) {
    return null;
    // TODO: throw exception??
  }

  @Override
  public Double visitError(ValueError v) {
    return null;
    // TODO: throw exception??
  }

  @Override
  public Value apply(List<Value> vals) {
    boolean result = vals.get(0).accept(this) < vals.get(1).accept(this);
    return new ValueBoolean(result);
  }
}
