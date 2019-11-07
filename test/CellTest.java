import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Worksheet;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests all public methods for a {@code Cell} object.
 */
public class CellTest {
  private Worksheet testWorksheet;

  @Before
  public void setup() {
    testWorksheet = new Worksheet();
  }

/*
  @Test
  public void testBlankCellContent() {
    assertEquals(new SString(""), testWorksheet.getCellAt(40, 31)
        .getCellContents().evaluate());
  }

  @Test
  public void testNumericValueCellContent() {
    assertEquals(new SNumber(9), testWorksheet.getCellAt(2, 0)
        .getCellContents().evaluate());
  }

  @Test
  public void testStringValueCellContent() {
    assertEquals(new SString("\"Hi\""), testWorksheet.getCellAt(3, 0)
        .getCellContents().evaluate());
  }

  @Test
  public void testBooleanValueCellContent() {
    assertEquals(new SBoolean(true), testWorksheet.getCellAt(5, 0)
        .getCellContents().evaluate());
  }

  @Test
  public void testBlanksAreBeingRenderedCorrectly() {
    assertEquals("",
        new Blank().toString());
  }

  @Test
  public void testValuesAreBeingRenderedCorrectly() {
    assertEquals("4.0", new Value(new SNumber(4)).toString());
  }

  @Test
  public void testFormulasAreBeingRenderedCorrectly() {
    assertEquals("8.0",
        new Formula(new SList(new SSymbol("SUM"),
            new SNumber(5), new SNumber(3))).toString());
  }

  @Test (expected =  IllegalArgumentException.class)
  public void testSelfReferentialFormula() {
    Cell thisCell = new Cell(new Coord(0, 0), "(SUM A1 2)");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIndirectSelfReferentialFormula() {
    Cell thisCell = new Cell(new Coord(0, 0), "=B1");
    Cell thatCell = new Cell(new Coord(1, 0), "=A1 + 1");
  }
*/

}