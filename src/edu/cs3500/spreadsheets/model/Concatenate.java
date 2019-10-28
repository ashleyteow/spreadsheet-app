package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.ArrayList;
import java.util.List;

public class Concatenate implements Operation, SexpVisitor<String> {
  private String strResult;
  private ArrayList<Cell> cells;

  public Concatenate(ArrayList<Cell> cells) {
    this.cells = cells;
    this.strResult = "";
  }

  public String getStr() {
    return this.strResult;
  }

  @Override
  public void operate() {
    for (Cell c : cells) {
      c.getCellContents().getContents().accept(this);
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
