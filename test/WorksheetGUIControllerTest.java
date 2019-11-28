import static org.junit.Assert.*;

import edu.cs3500.spreadsheets.controller.WorksheetController;
import edu.cs3500.spreadsheets.controller.WorksheetGUIController;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Value;
import edu.cs3500.spreadsheets.model.ValueDouble;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.Worksheet.Builder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

public class WorksheetGUIControllerTest {
  @Test
  public void confirmEdits() throws IOException {
    FileReader readFile = new FileReader("resources/" + "testParse1");
    Worksheet model = WorksheetReader.read(new Builder(), readFile);
    WorksheetController control = new WorksheetGUIController(model);
    control.start();
    // default coordToEdit
    Value before =  model.getCellAt(new Coord(1,1)).getCellValue();
    ((WorksheetGUIController) control).confirmEdits("32");
    Value after = model.getCellAt(new Coord(1,1)).getCellValue();
    assertEquals(new ValueDouble(32.000000),after);
  }
}