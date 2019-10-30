package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.ArrayList;
import java.util.List;

public class LessThan implements Operation, SexpVisitor<Boolean> {

  private boolean result;
  private Sexp first;
  private Sexp second;
  public static SSymbol name = new SSymbol("<");
  private List<Sexp> vals;

  public LessThan(List<Sexp> vals) {
    this.vals = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      this.vals.add(vals.get(i));
    }
    this.result = false;
    this.first = this.vals.get(0);
    this.second = this.vals.get(1);
  }

  @Override
  public void operate() {
    first.accept(this);
    second.accept(this);
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
    return null;
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
