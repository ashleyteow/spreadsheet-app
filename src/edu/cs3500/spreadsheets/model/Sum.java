package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Performs the sum operation for a given formula in a cell.
 */
public class Sum implements Operation, CellContentsVisitor<Double> {

  @Override
  public Double visitString(ValueString v) {
    try {
      return Double.valueOf(v.toString());
    } catch (NumberFormatException e) {
      return 0.0;
    }
  }

  @Override
  public Double visitDouble(ValueDouble v) {
    return Double.valueOf(v.toString());
  }

  @Override
  public Double visitBlank(ValueBlank v) {
    return 0.0;
  }

  @Override
  public Double visitBoolean(ValueBoolean v) {
    return 0.0;
  }

  @Override
  public Double visitError(ValueError v) {
    return null;
    // TODO: throw exception??
  }

  @Override
  public Value apply(List<Value> vals) {
    Double result = 0.0;
    for (Value v: vals) {
      result += v.accept(this);
    }
    return new ValueDouble(result);
  }
}
