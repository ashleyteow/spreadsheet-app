package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessThan implements Operation, SexpVisitor<Boolean> {

  private boolean result;
  private Map<Sexp, Double> test = new HashMap<>();
  public static SSymbol name = new SSymbol("<");
  private List<Sexp> vals;

  public LessThan(List<Sexp> vals) {
    this.vals = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      this.vals.add(vals.get(i));
    }
    this.result = false;
  }

  @Override
  public void operate() {
    for (Sexp s : this.vals) {
      s.accept(this);
    }
  }

  public boolean getResult() {
    return this.result;
  }

  @Override
  public Boolean visitBoolean(boolean b) {
    return null;
  }

  @Override
  public Boolean visitNumber(double d) {
    Sexp sp = new SNumber(d);
    if (!test.containsKey(sp)) {
      test.put(sp, d);
    }
    return d < test.get(sp);
  }

  @Override
  public Boolean visitSList(List<Sexp> l) {
    return null;
  }

  @Override
  public Boolean visitSymbol(String s) {
    throw new IllegalArgumentException("cannot do less than with a Symbol type");
  }

  @Override
  public Boolean visitString(String s) {
    throw new IllegalArgumentException("cannot do less than with a String type");
  }

}
