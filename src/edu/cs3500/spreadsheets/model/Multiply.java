package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs the product operation for a given formula in a cell.
 */
public class Multiply implements Operation, SexpVisitor<Double> {
  private double product;
  public static SSymbol name = new SSymbol("PRODUCT");

  private Sexp vals;

  /**
   * Constructs a {@code Multiply} object from the given arraylist of
   * cells.
   * @param vals arguments to be evaluated
   */
  public Multiply(Sexp vals) {
    this.vals = vals;
    this.product = 1;
  }

  @Override
  public void operate() {
    vals.accept(this);
  }

  /**
   * Returns the result of this multiply operation.
   * @return total sum
   */
  public double getProduct() {
    return this.product;
  }

  @Override
  public Double visitBoolean(boolean b) {
    return product;
  }

  @Override
  public Double visitNumber(double d) {
    return product *= d;
  }

  @Override
  public Double visitSList(List<Sexp> l) {
    for (Sexp s : l) {
      s.accept(this);
    }
    return product;
  }

  @Override
  public Double visitSymbol(String s) {
    return product;
  }

  @Override
  public Double visitString(String s) {
    return product;
  }
}
