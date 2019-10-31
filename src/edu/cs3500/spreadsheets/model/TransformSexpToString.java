package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.List;

public class TransformSexpToString implements SexpVisitor<String> {

  private List<Sexp> s;
  private String strResults;

  public TransformSexpToString(List<Sexp> s) {
    this.s = s;
    this.strResults = "";
  }

  public void transform() {
    for (Sexp sexp: s) {
      sexp.accept(this);
    }
  }

  @Override
  public String visitBoolean(boolean b) {
    if (b) {
      strResults += "TRUE";
    } else {
      strResults += "FALSE";
    }
    return strResults;
  }

  @Override
  public String visitNumber(double d) {
    return strResults += String.valueOf(d);
  }

  @Override
  public String visitSList(List<Sexp> l) {
    for (Sexp s : l) {
      s.accept(this);
    }
    return strResults;
  }

  @Override
  public String visitSymbol(String s) {
    return strResults += s;
  }

  @Override
  public String visitString(String s) {
    return strResults += s;
  }
}
