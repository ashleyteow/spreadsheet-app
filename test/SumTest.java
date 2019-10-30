import static org.junit.Assert.*;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Multiply;
import edu.cs3500.spreadsheets.model.Sum;
import edu.cs3500.spreadsheets.model.Value;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import java.util.ArrayList;
import org.junit.Test;

public class SumTest {
//  @Test
//  public void testSumWithOnlyNumbers() {
//    ArrayList<Cell> cells = new ArrayList<Cell>();
//    cells.add(new Cell(new Coord(1,1), "87"));
//    cells.add(new Cell(new Coord(2,1), "3"));
//    Sum sum = new Sum(cells);
//    sum.operate();
//    assertEquals(90.0, sum.getSum(), 0.0001);
//  }
//
//  @Test
//  public void testSumWithNumbersAndStrings() {
//    ArrayList<Cell> cells = new ArrayList<Cell>();
//    cells.add(new Cell(new Coord(1,1), "hello"));
//    cells.add(new Cell(new Coord(2,1), "3"));
//    cells.add(new Cell(new Coord(2,1), "cake"));
//    cells.add(new Cell(new Coord(5, 2), "6"));
//    Sum sum = new Sum(cells);
//    sum.operate();
//    assertEquals(10.0, sum.getSum(), 0.0001);
//  }
//
//  @Test (expected = IllegalArgumentException.class)
//  public void testSumWithInvalidSexp() {
//    ArrayList<Cell> cells = new ArrayList<>();
//    cells.add(new Cell(new Coord(2, 4), "\"|hello"));
//    cells.add(new Cell(new Coord(4, 5), "5"));
//    cells.add(new Cell(new Coord(5, 6), "5"));
//    Sum sum = new Sum(cells);
//    sum.operate();
//  }

  @Test
  public void testFormula() {
    Formula formula = new Formula(new SList(new SSymbol("PRODUCT"),
        new SNumber(3), new SList(new SSymbol("SUM"), new SNumber(2), new SNumber(4))));
    formula.evaluate();
    assertEquals(new Value(new SNumber(18)), formula.getEvaluatedCellContent());
  }

}