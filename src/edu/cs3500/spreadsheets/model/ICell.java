package edu.cs3500.spreadsheets.model;

public interface ICell {

  /**
   * Getter method for this cells' contents.
   * @return evaluated value of this cell (Value, Blank, Formula)
   */
  CellContents getCellContents();

  /**
   * Getter method for this cells' location in the {@code Worksheet}.
   * @return the location of this cell
   */
  Coord getCoord();

  /**
   * Transforms unprocessed raw contents into evaluated content.
   * @return evaluated value of this cell (Value, Blank, Formula)
   */
  CellContents evaluate();

  /**
   * Set new content for this cell using the given unprocessed contents.
   * @param contents unprocessed String content to be evaluated as the new content of this cell
   */
  void setCellContent(String contents);

  /**
   * Getter method for the raw, unevaluated String input of this cell.
   * @return the raw content of this cell
   */
  String getRawValue();
}
