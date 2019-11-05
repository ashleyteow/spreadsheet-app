package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.HashMap;
import java.util.List;

public class Evaluator implements SexpVisitor<CellContents> {
  HashMap<String, Operation> operations;
  Coord coord;
  IWorksheet worksheet;

  public Evaluator(Coord coord, IWorksheet worksheet) {
    this.worksheet = worksheet;
    this.coord = coord;
    operations = new HashMap<String, Operation>();
    operations.put("SUM", new Sum());
    operations.put("PRODUCT", new Multiply());
    operations.put("CONCAT", new Concatenate());
    operations.put("<", new LessThan());
  }

  @Override
  public CellContents visitBoolean(boolean b) {
    return new ValueBoolean(b);
  }

  @Override
  public CellContents visitNumber(double d) {
    return new ValueDouble(d);
  }

  @Override
  public CellContents visitSList(List<Sexp> l) {
    return null;
  }

  @Override
  public CellContents visitSymbol(String s) {
    // TODO : implement references before implementing this method
    if (s.contains(":")) {
      int colonIdx = s.indexOf(":");
      return new ValueBlank();
//      try {
//        return new Reference(new Coord(Integer.parseInt(s.substring(0, colonIdx)),
//            new Coord(Integer.parseInt(s.substring(colonIdx + 1))), coord, worksheet);
//      } catch (NumberFormatException e) {
//        return new ValueString(s);
//      }
    }
    else {
      return new ValueBlank();
    }
  }

  @Override
  public CellContents visitString(String s) {
    return new ValueString(s);
  }
}
