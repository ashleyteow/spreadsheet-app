import static org.junit.Assert.*;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import org.junit.Test;

public class FormulaTest {

  @Test
  public void testValidSumFormula() {
    Formula formula = new Formula(new SList(new SSymbol("SUM"),
        new SNumber(3), new SNumber(4)));
    assertEquals(new SNumber(7.0), formula.evaluate());
  }

  @Test
  public void testValidSumFormula2() {
    Formula formula = new Formula(new SList(new SSymbol("SUM"),
        new SNumber(3), new SNumber(4), new SBoolean(true),
        new SList(new SSymbol("PRODUCT"), new SNumber(6), new SNumber(7))));
    assertEquals(new SNumber(20.0), formula.evaluate());
  }

  @Test
  public void testValidProductFormula() {
    Formula formula = new Formula(new SList(new SSymbol("PRODUCT"),
        new SNumber(3), new SNumber(4)));
    assertEquals(new SNumber(12.0), formula.evaluate());
  }

  @Test
  public void testValidProductFormula2() {
    Formula formula = new Formula(new SList(new SSymbol("PRODUCT"),
        new SNumber(2), new SList(new SSymbol("SUM"), new SNumber(3), new SNumber(4), new SNumber(5))));
    assertEquals(new SNumber(24.0), formula.evaluate());
  }

  @Test
  public void testValidConcatFormula() {
    Formula formula = new Formula(new SList(new SSymbol("CONCAT"),
        new SString("Hello "), new SString("World")));
    assertEquals(new SString("Hello World"), formula.evaluate());
  }

  @Test
  public void testValidLessThanFormula() {
    Formula formula = new Formula(new SList(new SSymbol("<"), new SNumber(5), new SNumber(3)));
    assertEquals(new SBoolean(false), formula.evaluate());
  }

  @Test
  public void testReferringToSameCellMultipleTimes() {
    Cell c1 = new Cell(new Coord(0, 1), "4");
    Formula formula = new Formula(new SList(new SSymbol("SUM"),
        new SSymbol("A2"), new SSymbol("A2")));
    assertEquals(new SNumber(8.0), formula.evaluate());
  }

  @Test
  public void testFormulasWithReferenceRegions() {
    Cell c1 = new Cell(new Coord(0, 0), "4");
    Cell c2 = new Cell(new Coord(0, 1), "5");
    Cell c3 = new Cell(new Coord(1, 0), "1");
    Cell c4 = new Cell(new Coord(1, 1), "3");
    Formula formula = new Formula(new SList(new SSymbol("SUM"),
        new SSymbol("A1:B2")));
    assertEquals(new SNumber(13.0), formula.evaluate());
  }

  @Test
  public void testingFormulasWithWrongType() {
    Formula formula = new Formula(new SList(new SSymbol("PRODUCT"),
        new SBoolean(true), new SNumber(5)));
    assertEquals(new SNumber(5.0), formula.evaluate());
  }

}