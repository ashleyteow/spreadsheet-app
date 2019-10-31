
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;

/**
 * Testing all public methods for a Worksheet class.
 */
public class WorksheetTest {

  @Test
  public void testReadingInput() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(Worksheet.builder(),
        new FileReader("/Users/ashleyteow/IdeaProjects/BeyondGoodProject/testParse1"));
    testWorksheet.getCells();
  }

  @Test
  public void testReadingInput2() throws FileNotFoundException {
    Worksheet testWorksheet = WorksheetReader.read(Worksheet.builder(),
        new FileReader("/Users/gauri_dandi/Documents/Northeastern/2019-2020/"
            + "CS 3500/HWK5/testParse2"));
    testWorksheet.getCells();
  }

}