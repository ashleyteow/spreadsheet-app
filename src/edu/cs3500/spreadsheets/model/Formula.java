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

  private Sexp evaluatedCellContent;
  private ArrayList<Sexp> vals;

  /**
   * Constructs a {@code Formula} object.
   * @param cellContent S-expression
   */
  public Formula(Sexp cellContent) {
    super(cellContent);
    this.evaluatedCellContent = this.evaluate();
  }

  /**
   * Updates the cellContent to be the evaluated value of this formula.
   */
  public Sexp evaluate() {
    TransformSListToArrayList transform = new TransformSListToArrayList(cellContent);
    transform.transform();
    ArrayList<Sexp> arguments = transform.getList();

    for (int i = 0; i < arguments.size(); i++) {
      if (arguments.get(i).equals(Multiply.NAME)) {
        Multiply m = new Multiply(arguments.subList(i + 1, arguments.size()));
        m.operate();
        evaluatedCellContent = new Value(new SNumber(m.getProduct())).evaluate();
      }
      if (arguments.get(i).equals(Sum.NAME)) {
        Sum s = new Sum(arguments.subList(i + 1, arguments.size()));
        s.operate();
        evaluatedCellContent = new Value(new SNumber((s.getSum()))).evaluate();
      }
      if (arguments.get(i).equals(Concatenate.NAME)) {
        Concatenate c = new Concatenate(arguments.subList(i + 1, arguments.size()));
        c.operate();
        evaluatedCellContent = new Value(new SString((c.getStr()))).evaluate();
      }
      if (arguments.get(i).equals(LessThan.NAME)) {
        LessThan lt = new LessThan(arguments.subList(i + 1, arguments.size()));
        evaluatedCellContent = new Value(new SBoolean(lt.getResult())).evaluate();
      }
    }
    return evaluatedCellContent;
  }

  @Override
  public String toString() {
    return evaluatedCellContent.toString();
  }

}
