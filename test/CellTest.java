import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.Worksheet.Builder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        new FileReader("testParse1"));
  }

  @Test
  public void testBlankCellContent() {
    assertEquals(null, testWorksheet.getCellAt(new Coord(40, 31)));
  }
}