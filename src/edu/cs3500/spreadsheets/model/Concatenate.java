package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.List;

/**
 * Performs the concatenate operation for a given formula in a cell.
 */
public class Concatenate implements Operation, SexpVisitor<String> {
  private String strResult;
  public static SSymbol name = new SSymbol("CONCAT");
  private List<Sexp> vals;

  /**
   * Constructs a {@code Concatenate} object from the given arraylist of
   * cells.
   * @param vals arguments to be evaluated
   */
  public Concatenate(List<Sexp> vals) {
    this.vals = vals;
    this.strResult = "";
  }

  /**
   * Returns the result of this concatenate operation.
   * @return total sum
   */
  public String getStr() {
    return this.strResult;
  }

  @Override
  public void operate() {
    for (Sexp s : this.vals) {
      s.accept(this);
    }
  }

  @Override
  public String visitBoolean(boolean b) {
    if (b) {
      return strResult += "TRUE";
    }
    else {
      return strResult += "FALSE";
    }
  }

  @Override
  public String visitNumber(double d) {
    return strResult += String.valueOf(d);
  }

  @Override
  public String visitSList(List<Sexp> l) {
    for (Sexp s : l) {
      s.accept(this);
    }
    return strResult;
  }

  @Override
  public String visitSymbol(String s) {
    return strResult += s;
  }

  @Override
  public String visitString(String s) {
    return strResult += s;
  }
}
