import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.ValueDouble;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.Worksheet.Builder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.sexp.SString;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextArea;
import org.junit.Test;

/**
 * Testing all public methods for a Worksheet class.
 */
public class WorksheetTest {

  @Test
  public void testReadingBlankWorksheet() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("testBlankParse"));
    ArrayList<Cell> worksheet = testWorksheet.getCells();
    assertEquals(0, worksheet.size());
  }

  @Test
  public void testIfCellContentsAreFilledInSpreadsheet() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("testParse2"));
    System.out.println(testWorksheet.getCells());
    System.out.println(testWorksheet.getCellAt(new Coord(1, 1)));
      assertEquals("4", testWorksheet.getCellAt(new Coord(1, 1)).getRawValue());
//    assertEquals(a2, testWorksheet.getCellAt(new Coord(1, 2)));
//    assertEquals(a3, testWorksheet.getCellAt(new Coord(1, 3)));
//    assertEquals(a4, testWorksheet.getCellAt(new Coord(1, 4)));
//    assertEquals(a5, testWorksheet.getCellAt(new Coord(1, 5)));
  }

//  @Test
//  public void testGetCellAt() throws FileNotFoundException {
//    Cell a1 = new Cell(new Coord(0, 0),
//        "=(PRODUCT 2 (SUM 3 4 5))");
//    Worksheet testWorksheet = WorksheetReader.read(Worksheet.builder(),
//        new FileReader("/Users/ashleyteow/IdeaProjects/BeyondGoodProject/testParse1"));
//    assertEquals(a1, testWorksheet.getCellAt(0, 0));
//  }

  @Test
  public void testSingleCellReferenceWithFormula() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("testParse1"));
    Cell c = new Cell(new Coord(3, 6), "=A1", testWorksheet);
    assertEquals(new ValueDouble(24), c.getCellValue());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSingleCellReferenceWithDirectCycle() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("testParseSingleCycle"));
  }

  @Test
  public void testCellReferenceWithIndirectCycle() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(new Builder(),
        new FileReader("testParse1"));
    testWorksheet.addCellAt(new Coord(2, 6), "5");
    testWorksheet.addCellAt(new Coord(1, 5), "=B6");
    testWorksheet.editCellAt(new Coord(2, 6), "=A5");
  }

}

