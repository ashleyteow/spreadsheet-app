package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Performs the concatenate operation for a given formula in a cell.
 */
public class Concatenate implements Operation, CellContentsVisitor<String> {

  @Override
  public String visitString(ValueString v) {
    return v.toString();
  }

  @Override
  public String visitDouble(ValueDouble v) {
    return v.toString();
  }

  @Override
  public String visitBlank(ValueBlank v) {
    return "";
  }

  @Override
  public String visitBoolean(ValueBoolean v) {
    return v.toString();
  }

  @Override
  public String visitError(ValueError v) {
    return null;
    // TODO: don't know if we should throw exceptions here??
  }

  @Override
  public Value apply(List<Value> vals) {
    String result = "";
    for (Value v : vals) {
      result += v.accept(this);
    }
    return new ValueString(result);
  }
}
