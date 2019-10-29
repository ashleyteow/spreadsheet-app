package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs the sum operation for a given formula in a cell.
 */
public class Sum implements Operation, SexpVisitor<Double> {
  private double sum;
  private ArrayList<Cell> cells;

  /**
   * Constructs a {@code Sum} object from the given arraylist of
   * cells.
   * @param cells arguments to be evaluated
   */
  public Sum(ArrayList<Cell> cells) {
    this.cells = cells;
    this.sum = 0;
  }

  @Override
  public void operate() {
    for (Cell c : cells) {
      c.getCellContents().getContents().accept(this);
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
    for (Sexp s : l) {
      s.accept(this);
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
