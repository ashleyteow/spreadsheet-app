package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.List;

/**
 * Performs the product operation for a given formula in a cell.
 */
public class Multiply implements Operation, SexpVisitor<Double> {
  private double product;
  public static final SSymbol name = new SSymbol("PRODUCT");
  private List<Sexp> vals;

  /**
   * Constructs a {@code Multiply} object from the given arraylist of
   * cells.
   * @param vals arguments to be evaluated
   */
  public Multiply(List<Sexp> vals) {
    this.vals = vals;
    this.product = 1;
  }

  @Override
  public void operate() {
    for (Sexp s : this.vals) {
      s.accept(this);
    }
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
    for (int i = 0; i < l.size(); i++) {
      if (l.get(i).equals(Sum.name)) {
        Sum s = new Sum(l.subList(i+1, l.size()));
        s.operate();
        product *= s.getSum();
      }
      else if (l.get(i).equals(Multiply.name)) {
        Multiply m = new Multiply(l.subList(i+1, l.size()));
        m.operate();
        product *= m.getProduct();
      }
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
