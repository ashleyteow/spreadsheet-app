import static org.junit.Assert.*;

import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.Worksheet.Builder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.WorksheetView;
import edu.cs3500.spreadsheets.view.WorksheetVisualView;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;

public class WorksheetVisualViewTest {

  @Test
  public void testVisualView1() throws FileNotFoundException {
    Worksheet worksheet = WorksheetReader.read(new Builder(),
        new FileReader("testParseIndirectCycle"));
    WorksheetView visual = new WorksheetVisualView();
  }

}