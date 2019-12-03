package edu.cs3500.spreadsheets.provider.model;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.IData;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * an interface represents the formulas of the cell.
 */
public interface IFormula {

  /**
   * Does the cell represented by the coordinate has a cyclic reference to itself.
   *
   * @param c            the coordinate of the cell
   * @param hm           worksheet
   * @param checkedCoord visited list of cells.
   * @return whether the cell has cyclic reference.
   */
  boolean contains(Coord c, HashMap<Coord, IFormula> hm, List<Coord> checkedCoord);

  /**
   * make reference of the cell has concrete data from the sheet.
   *
   * @param cords current coord to set
   * @param depend dependencies sheet
   */
  void setReference(Coord cords, HashMap<Coord, List<Coord>> depend);


  /**
   * evaluate the formula ot a IData.
   *
   * @param sheet the worksheet itself that contains all data
   * @param reeval whether to reevaluate the cell
   * @return evaluated data
   * @throws IllegalArgumentException if the function is not supported.
   */
  IData eval(HashMap<Coord, IFormula> sheet, boolean reeval) throws IllegalArgumentException;

  /**
   * evaluate the formula as a string.
   *
   * @param base base case of the evaluation
   * @param func function object
   * @param sheet the worksheet itself that contains all data
   * @return a string result of evaluation
   */
  String evalAsString(String base, Function<String, String> func, HashMap<Coord, IFormula> sheet);

  /**
   * evaluate the formula as a double.
   *
   * @param base base case of the evaluation
   * @param func function object
   * @param sheet the worksheet itself that contains all data
   * @return a double result of evaluation
   */
  Double evalAsDouble(Double base, Function<Double, Double> func, HashMap<Coord, IFormula> sheet);

  /**
   * evaluate the formula as a boolean.
   *
   * @param base base case of the evaluation
   * @param func function object
   * @param sheet the worksheet itself that contains all data
   * @return a boolean result of evaluation
   */
  boolean evalAsBoolean(boolean base, Function<Boolean, Boolean> func,
      HashMap<Coord, IFormula> sheet);
}
