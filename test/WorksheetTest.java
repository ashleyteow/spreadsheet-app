import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.sexp.SString;
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
    Worksheet testWorksheet = WorksheetReader.read(Worksheet.builder(),
        new FileReader("/Users/ashleyteow/IdeaProjects/BeyondGoodProject/testBlankParse"));
    ArrayList<ArrayList<Cell>> worksheet = testWorksheet.getCells();
    for (int i = 0; i < worksheet.size(); i++) {
      for (int j = 0; j < worksheet.get(i).size(); j++) {
        assertEquals(new SString(""), worksheet.get(i).get(j).getCellContents().evaluate());
      }
    }
  }

  @Test
  public void testIfCellContentsAreFilledInSpreadsheet() throws FileNotFoundException {
    Cell a1 = new Cell(new Coord(0, 0),
        "=(PRODUCT 2 (SUM 3 4 5))");
    Cell b1 = new Cell(new Coord(1, 0), "=4");
    Cell c1 = new Cell(new Coord(2, 0), "9");
    Cell d1 = new Cell(new Coord(3, 0), "\"Hi\"");
    Cell e3 = new Cell(new Coord(4, 2), "3");
    Cell f1 = new Cell(new Coord(5, 0), "true");
    Worksheet testWorksheet = WorksheetReader.read(Worksheet.builder(),
        new FileReader("/Users/ashleyteow/IdeaProjects/BeyondGoodProject/testParse1"));
    assertEquals(a1, testWorksheet.getCellAt(0, 0));
    assertEquals(b1, testWorksheet.getCellAt(1, 0));
    assertEquals(c1, testWorksheet.getCellAt(2, 0));
    assertEquals(d1, testWorksheet.getCellAt(3, 0));
    assertEquals(e3, testWorksheet.getCellAt(4, 2));
    assertEquals(f1, testWorksheet.getCellAt(5, 0));
  }

  @Test
  public void testGetCellAt() throws FileNotFoundException {
    Cell a1 = new Cell(new Coord(0, 0),
        "=(PRODUCT 2 (SUM 3 4 5))");
    Worksheet testWorksheet = WorksheetReader.read(Worksheet.builder(),
        new FileReader("/Users/ashleyteow/IdeaProjects/BeyondGoodProject/testParse1"));
    assertEquals(a1, testWorksheet.getCellAt(0, 0));
  }

}