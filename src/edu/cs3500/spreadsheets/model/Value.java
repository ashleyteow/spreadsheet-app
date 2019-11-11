package edu.cs3500.spreadsheets.model;

/**
 * Represents an implementation of CellContents. This class represents if a
 * cell's contents do not begin with an equal sign and is therefore simply
 * a value in the spreadsheet.
 */
public abstract class Value implements CellContents {

  /**
   * Initiates the visitor pattern.
   * @param visitor Cell Content's visitor
   * @param <R> generic return type
   * @return
   */
  public abstract <R> R accept(CellContentsVisitor<R> visitor);

  @Override
  public Value getVal() {
    return this;
  }

}
