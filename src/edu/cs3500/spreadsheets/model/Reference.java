package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;
import java.util.ArrayList;

/**
 * Represents a region of cells in a {@code Worksheet}.
 */
public class Reference extends Formula {

  private Coord c1;
  private Coord c2;
  public final Worksheet w;
  private ArrayList<Sexp> finalVals;

  /**
   * Constructs a {@code Reference} object.
   *
   * @param cellContent S-expression
   */
  public Reference(Sexp cellContent, Coord c1, Coord c2, Worksheet w) {
    super(cellContent);
    this.w = w;
    this.finalVals = new ArrayList<>();
  }

  private ArrayList<Sexp> getFinalVals() {
    return this.finalVals;
  }

}
