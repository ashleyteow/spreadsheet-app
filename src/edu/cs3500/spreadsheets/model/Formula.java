package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 * A formula is a value and therefore inherits all methods from the
 * Value class. This class is responsible for processing the operation the user
 * intends to process.
 */
public class Formula extends Value {

  private Sexp cellContent;


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
  private void evaluate() {
    TransformSexp transform = new TransformSexp(cellContent);
    transform.transform();
    ArrayList<Sexp> arguments = transform.getList();

    for (Sexp s : arguments) {
      if (s.equals(Multiply.name)) {
        System.out.println("hello");
      }
    }
  }
}
