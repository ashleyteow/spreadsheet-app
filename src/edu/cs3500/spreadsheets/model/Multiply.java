package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.ArrayList;
import java.util.List;

public class Multiply implements Operation, SexpVisitor<Double> {
  private double product;
  private ArrayList<Cell> cells;

  public Multiply(ArrayList<Cell> cells) {
    this.cells = cells;
    this.product = 1;
  }

  @Override
  public void operate() {
    for (Cell c : cells) {
      c.getCellContents().getContents().accept(this);
    }
  }

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
