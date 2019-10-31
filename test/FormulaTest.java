import static org.junit.Assert.*;

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
  public void testSelfReferentialFormula() {

  }

  @Test
  public void testIndirectSelfReferentialFormula() {

  }

  @Test
  public void testReferringToSameCellMultipleTimes() {

  }

  @Test
  public void testFormulasWithReferenceRegions() {

  }

  @Test
  public void testingFormulasWithWrongType() {
    // SUM(TRUE, 5)
  }

}