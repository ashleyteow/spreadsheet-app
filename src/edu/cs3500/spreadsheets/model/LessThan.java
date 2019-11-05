package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Performs the less than (<) operation for a given formula in a cell.
 */
public class LessThan implements Operation, CellContentsVisitor<Double> {

  @Override
  public Double visitString(ValueString v) {
    return null;
    // throw exception
  }

  @Override
  public Double visitDouble(ValueDouble v) {
    return Double.valueOf(v.toString());
  }

  @Override
  public Double visitBlank(ValueBlank v) {
    return null;
    // throw exception
  }

  @Override
  public Double visitBoolean(ValueBoolean v) {
    return null;
    // throw exception
  }

  @Override
  public Value apply(List<Value> vals) {
    boolean result = vals.get(0).accept(this) < vals.get(1).accept(this);
    return new ValueBoolean(result);
  }

  //******* old code *************************
//  private boolean result;
//  private Map<Sexp, Double> test = new HashMap<>();
//  public static final SSymbol NAME = new SSymbol("<");
//  private List<Sexp> vals;
//
//  /**
//   * Constructs a {@code LessThan} object from the given arraylist of
//   * cells.
//   * @param vals arguments to be evaluated
//   */
//  public LessThan(List<Sexp> vals) {
//    this.vals = new ArrayList<>();
//    for (int i = 0; i < 2; i++) {
//      this.vals.add(vals.get(i));
//    }
//    this.result = false;
//  }
//
//  @Override
//  public void operate() {
//    for (Sexp s : this.vals) {
//      s.accept(this);
//    }
//  }
//
//  public boolean getResult() {
//    return this.result;
//  }
//
//  @Override
//  public Boolean visitBoolean(boolean b) {
//    return null;
//  }
//
//  @Override
//  public Boolean visitNumber(double d) {
//    Sexp sp = new SNumber(d);
//    if (!test.containsKey(sp)) {
//      test.put(sp, d);
//    }
//    return d < test.get(sp);
//  }
//
//  @Override
//  public Boolean visitSList(List<Sexp> l) {
//    return null;
//  }
//
//  @Override
//  public Boolean visitSymbol(String s) {
//    throw new IllegalArgumentException("cannot do less than with a Symbol type");
//  }
//
//  @Override
//  public Boolean visitString(String s) {
//    throw new IllegalArgumentException("cannot do less than with a String type");
//  }

}
