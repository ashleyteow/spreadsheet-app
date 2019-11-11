package edu.cs3500.spreadsheets.model;

/**
 * Visitor interface for {@code CellContents}.
 * @param <R> generic type
 */
public interface CellContentsVisitor<R> {

  /**
   * Process a {@code ValueError}.
   * @param v the value
   * @return the desired result
   */
  R visitError(ValueError v);

  /**
   * Process a {@code ValueString}.
   * @param v the value
   * @return the desired result
   */
  R visitString(ValueString v);

  /**
   * Process a {@code ValueBlank}.
   * @param v the value
   * @return the desired result
   */
  R visitBlank(ValueBlank v);

  /**
   * Process a {@code ValueDouble}.
   * @param v the value
   * @return the desired result
   */
  R visitDouble(ValueDouble v);

  /**
   * Process a {@code ValueBoolean}.
   * @param v the value
   * @return the desired result
   */
  R visitBoolean(ValueBoolean v);
}
