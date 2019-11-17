import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.ValueDouble;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.Worksheet.Builder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Testing all public methods for a Worksheet class.
 */
public class WorksheetTest {

  @Test
  public void testReadingBlankWorksheet() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("resources/testBlankParse"));
    ArrayList<Cell> worksheet = testWorksheet.getCells();
    assertEquals(0, worksheet.size());
  }

  @Test
  public void testIfCellContentsAreFilledInSpreadsheet() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("resources/testParse2"));
    System.out.println(testWorksheet.getCells());
    System.out.println(testWorksheet.getCellAt(new Coord(1, 1)));
    assertEquals("4", testWorksheet.getCellAt(new Coord(1, 1)).getRawValue());
  }

  @Test
  public void testSingleCellReferenceWithFormula() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("resources/testParse1"));
    Cell c = new Cell(new Coord(3, 6), "=A1", testWorksheet);
    assertEquals(new ValueDouble(24), c.getCellValue());
  }

  @Test
  public void testSingleCellReference() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("resources/testParse2"));
    assertEquals(new ValueDouble(4),
        testWorksheet.getCellAt(new Coord(1, 5)).getCellValue());
  }

  @Test
  public void testReferenceBlock() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("resources/testParseReferenceBlock"));
    assertEquals(new ValueDouble(19),
        testWorksheet.getCellAt(new Coord(3, 4)).getCellValue());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSingleCellReferenceWithDirectCycle() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("testParseSingleCycle"));
    System.out.println(testWorksheet.getCellAt(new Coord(4, 1))
        .getCellValue().toString());
  }
}
