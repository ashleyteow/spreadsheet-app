import static org.junit.Assert.assertEquals;


import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellContents;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Sum;
import edu.cs3500.spreadsheets.model.ValueBlank;
import edu.cs3500.spreadsheets.model.ValueBoolean;
import edu.cs3500.spreadsheets.model.ValueDouble;
import edu.cs3500.spreadsheets.model.ValueString;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.Worksheet.Builder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests all public methods for a {@code Cell} object.
 */
public class CellTest {
  private Worksheet testWorksheet;

  @Before
  public void setup() throws FileNotFoundException {
    testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("resources/testParse1"));
  }

  @Test
  public void testBlankCellContent() {
    assertEquals(new ValueBlank(),
        testWorksheet.getCellAt(new Coord(15, 20)).getCellValue());
  }

  @Test
  public void testNumericValueCellContent() {
    assertEquals(new ValueDouble(3), testWorksheet.getCellAt(new Coord(5, 3))
        .getCellValue());
  }

  @Test
  public void testStringValueCellContent() {
    assertEquals(new ValueString("\"Hi\""), testWorksheet.getCellAt(new Coord(4, 4))
        .getCellValue());
  }

  @Test
  public void testBooleanValueCellContent() {
    assertEquals(new ValueBoolean(true), testWorksheet.getCellAt(new Coord(2, 4))
        .getCellValue());
  }

  @Test
  public void testBlanksAreBeingRenderedCorrectly() {
    assertEquals("",
        new ValueBlank().toString());
  }

  @Test
  public void testValuesAreBeingRenderedCorrectly() {
    assertEquals("4.0", new ValueDouble(4).toString());
  }

  @Test
  public void testFormulasAreBeingRenderedCorrectly() {
    ArrayList<CellContents> cellContents = new ArrayList<>();
    cellContents.add(new ValueDouble(5));
    cellContents.add(new ValueDouble(3));
    assertEquals("8.000000",
        new Formula(new Sum(), cellContents));
  }

  @Test (expected =  IllegalArgumentException.class)
  public void testSelfReferentialFormula() {
    Cell thisCell = new Cell(new Coord(0, 0), "(SUM A1 2)", testWorksheet);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIndirectSelfReferentialFormula() {
    Cell thisCell = new Cell(new Coord(0, 0), "=B1", testWorksheet);
    Cell thatCell = new Cell(new Coord(1, 0), "=A1 + 1", testWorksheet);
  }

}