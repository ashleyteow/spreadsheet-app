import static org.junit.Assert.*;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.WorksheetTextualView;
import edu.cs3500.spreadsheets.view.WorksheetView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.Test;

public class WorksheetTextualViewTest {

  @Test
  public void testReadingtestParseOne() {
    Worksheet worksheet;

    try {
      worksheet = WorksheetReader
          .read(new Worksheet.Builder(),
              new FileReader("/Users/ashleyteow/IdeaProjects/BeyondGoodProject/testParse1"));
    } catch (FileNotFoundException ex) {
      throw new IllegalStateException("File read error");
    }

    PrintWriter p;

    try {
      p = new PrintWriter(new FileOutputStream(new File("testParse1")));
    } catch (Exception ex) {
      throw new IllegalStateException("file not found");
    }

    WorksheetView textualView = new WorksheetTextualView(p, worksheet);

    try {
      textualView.render();
    } catch (IOException ex) {
      throw new IllegalStateException("IOException");
    }
    p.close();

    Worksheet worksheet2;

    try {
      worksheet2 = WorksheetReader
          .read(new Worksheet.Builder(), new FileReader("testParse1"));
    } catch (FileNotFoundException ex) {
      throw new IllegalStateException("file not found");
    }

    assertEquals(worksheet.getCellAt(new Coord(1,1)),
        worksheet2.getCellAt(new Coord(1,1)));
    // TODO test all coords in testParse1
  }

}