package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.Sexp;
import java.util.ArrayList;

/**
 * A formula is a value and therefore inherits all methods from the
 * Value class. This class is responsible for processing the operation the user
 * intends to process.
 */
public class Formula extends Value {

  private Value evaluatedCellContent;

  /**
   * Constructs a {@code Formula} object.
   * @param cellContent S-expression
   */
  public Formula(Sexp cellContent) {
    super(cellContent);
  }

  /**
   * Updates the cellContent to be the evaluated value of this formula.
   */
  public void evaluate() {
    TransformSexp transform = new TransformSexp(cellContent);
    transform.transform();
    ArrayList<Sexp> arguments = transform.getList();

    for (int i = 0; i < arguments.size(); i++) {
      if (arguments.get(i).equals(Multiply.name)) {
        Multiply m = new Multiply(arguments.subList(i + 1, arguments.size()));
        m.operate();
        evaluatedCellContent = new Value(new SNumber(m.getProduct()));
      }
      if (arguments.get(i).equals(Sum.name)) {
        Sum s = new Sum(arguments.subList(i + 1, arguments.size()));
        s.operate();
        evaluatedCellContent = new Value(new SNumber((s.getSum())));
      }
      if (arguments.get(i).equals(Concatenate.name)) {
        Concatenate c = new Concatenate(arguments.subList(i + 1, arguments.size()));
        c.operate();
        evaluatedCellContent = new Value(new SString((c.getStr())));
      }
      if (arguments.get(i).equals(LessThan.name)) {
        LessThan lt = new LessThan(arguments.subList(i + 1, arguments.size()));
        evaluatedCellContent = new Value(new SBoolean(lt.getResult()));
      }
    }
  }

  public Value getEvaluatedCellContent() {
    return evaluatedCellContent;
  }
}
