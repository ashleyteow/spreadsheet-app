package edu.cs3500.spreadsheets.model;

public interface CellContentsVisitor<R> {
  R visitString(ValueString v);
  R visitDouble(ValueDouble v);
  R visitBlank(ValueBlank v);
  R visitBoolean(ValueBoolean v);
  R visitError(ValueError v);
}
