package edu.cs3500.spreadsheets.model;

/**
 * Visitor interface for {@code CellContents}.
 * @param <R>
 */
public interface CellContentsVisitor<R> {
  R visitError(ValueError v);
  R visitString(ValueString v);
  R visitBlank(ValueBlank v);
  R visitDouble(ValueDouble v);
  R visitBoolean(ValueBoolean v);
}
