package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Helper class that transforms S-expressions into Cells with evaluated contents.
 */
public class Evaluator implements SexpVisitor<CellContents> {
  Coord coord;
  IWorksheet worksheet;
  HashMap<String, Operation> operations = new HashMap<>();

  /**
   * Default constructor for a {@code Evaluator} object.
   *
   * @param coord     location of cell
   * @param worksheet worksheet model
   */
  public Evaluator(Coord coord, IWorksheet worksheet) {
    this.worksheet = worksheet;
    this.coord = coord;
    operations.put("SUM", new Sum());
    operations.put("PRODUCT", new Multiply());
    operations.put("<", new LessThan());
    operations.put("CONCAT", new Concatenate());
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
    ArrayList<CellContents> contents = new ArrayList<>();
    String operationName = l.get(0).toString();
    for (int i = 1; i < l.size(); i++) {
      CellContents c = l.get(i).accept(this);
      contents.add(c);
    }
    return new Formula(operations.get(operationName), contents);
  }

  @Override
  public CellContents visitSymbol(String s) {
    if (!s.contains(":")) {
      return new Reference(new Coord(s), coord, worksheet);
    } else {
      int colonIndex = s.indexOf(":");
      return new Reference(new Coord(s.substring(0, colonIndex)),
          new Coord(s.substring(colonIndex + 1)), coord, worksheet);
    }
  }

  @Override
  public CellContents visitString(String s) {
    return new ValueString(s);
  }
}
