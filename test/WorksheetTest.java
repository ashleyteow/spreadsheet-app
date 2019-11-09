import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import org.junit.Test;

/**
 * Testing all public methods for a Worksheet class.
 */
public class WorksheetTest {

  @Test
  public void testBlankWorksheet() {
    Worksheet w = new Worksheet();
    assertEquals(0, w.getCells().size());
  }

  @Test
  public void testGetCellAt() {
    Worksheet w = new Worksheet();

    w.editCellAt(new Coord(1,1), "=2");
    assertEquals("=2", w.getCellAt(new Coord(1,1)).getRawValue());
    w.editCellAt(new Coord(1,1), "=4");
    assertEquals("=4", w.getCellAt(new Coord(1,1)).getRawValue());
  }

  @Test
  public void testCycles() {
    Worksheet w = new Worksheet();
    w.editCellAt(new Coord(1, 1), "=A2");
    w.editCellAt(new Coord(1, 2), "=A1");
    assertEquals(w.getCellAt(new Coord(1, 2)).toString(), "#NAME");
//
//    w.editCellAt(new Coord(2, 1), "=B1");
//    assertEquals(w.getCellAt(new Coord(2, 1)).toString(), "#NAME");

  }
}