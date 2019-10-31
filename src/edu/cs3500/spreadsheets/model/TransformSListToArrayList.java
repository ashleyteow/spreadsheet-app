package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to help transform SLists into ArrayLists so that its
 * contents can be accessed.
 */
public class TransformSListToArrayList implements SexpVisitor<ArrayList<Sexp>> {

  private Sexp s;
  private ArrayList<Sexp> list;

  public TransformSListToArrayList(Sexp s) {
    this.s = s;
    this.list = new ArrayList<Sexp>();
  }

  public void transform() {
    s.accept(this);
  }

  public ArrayList<Sexp> getList() {
    ArrayList<Sexp> copyList = new ArrayList<Sexp>();
    for (Sexp s : list) {
      copyList.add(s);
    }
    return copyList;
  }

  @Override
  public ArrayList<Sexp> visitBoolean(boolean b) {
    return list;
  }

  @Override
  public ArrayList<Sexp> visitNumber(double d) {
    list.add(new SNumber(d));
    return list;
  }

  @Override
  public ArrayList<Sexp> visitSList(List<Sexp> l) {
    for (Sexp s : l) {
      list.add(s);
    }
    return list;
  }

  @Override
  public ArrayList<Sexp> visitSymbol(String s) {
    return list;
  }

  @Override
  public ArrayList<Sexp> visitString(String s) {
    return list;
  }
}
