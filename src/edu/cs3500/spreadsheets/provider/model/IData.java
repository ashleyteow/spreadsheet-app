package edu.cs3500.spreadsheets.provider.model;

/**
 * A interface an atomic data which is an IFormula.
 */
public interface IData extends IFormula {
  /**
   * get the string of the data.
   *
   * @return the string of the data.
   */
  String getString();
}
