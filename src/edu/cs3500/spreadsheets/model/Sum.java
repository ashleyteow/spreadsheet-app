package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.List;

/**
 * Performs the sum operation for a given formula in a cell.
 */
public class Sum implements Operation, SexpVisitor<Double> {
  private double sum;
  public static final SSymbol name = new SSymbol("SUM");
  private List<Sexp> vals;

  /**
   * Constructs a {@code Sum} object from the given arraylist of
   * cells.
   * @param vals arguments to be evaluated
   */
  public Sum(List<Sexp> vals) {
    this.vals = vals;
    this.sum = 0;
  }

  @Override
  public void operate() {
    for (Sexp s : this.vals) {
      s.accept(this);
    }
  }

  /**
   * Returns the result of this sum operation.
   * @return total sum
   */
  public double getSum() {
    return this.sum;
  }

  @Override
  public Double visitBoolean(boolean b) {
    return sum;
  }

  @Override
  public Double visitNumber(double d) {
    return sum += d;
  }

  @Override
  public Double visitSList(List<Sexp> l) {
    for (int i = 0; i < l.size(); i++) {
      if (l.get(i).equals(Sum.name)) {
        Sum s = new Sum(l.subList(i+1, l.size()));
        s.operate();
        sum += s.getSum();
      }
      else if (l.get(i).equals(Multiply.name)) {
        Multiply m = new Multiply(l.subList(i+1, l.size()));
        m.operate();
        sum += m.getProduct();
      }
    }
    return sum;
  }

  @Override
  public Double visitSymbol(String s) {
    return sum;
  }

  @Override
  public Double visitString(String s) {
    return sum;
  }
}
