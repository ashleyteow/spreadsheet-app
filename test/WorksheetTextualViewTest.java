import static org.junit.Assert.assertEquals;

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

/**
 * Tests all public methods and functionality for a {@code WorksheetTextualView}.
 */
public class WorksheetTextualViewTest {

  @Test
  public void testReadingtestParseOne() {
    Worksheet worksheet;

    try {
      worksheet = WorksheetReader
          .read(new Worksheet.Builder(),
              new FileReader("resources/testParse1"));
    } catch (FileNotFoundException ex) {
      throw new IllegalStateException("File read error");
    }

    PrintWriter p;

    try {
      p = new PrintWriter(new FileOutputStream(new File("resources/testParse1")));
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
          .read(new Worksheet.Builder(), new FileReader("resources/testParse1"));
    } catch (FileNotFoundException ex) {
      throw new IllegalStateException("file not found");
    }

    assertEquals(true,
        worksheet.getCells().equals(worksheet2.getCells()));
  }

  @Test
  public void testReadingtestParseTwo() {
    Worksheet worksheet;

    try {
      worksheet = WorksheetReader
          .read(new Worksheet.Builder(),
              new FileReader("resources/testParse2"));
    } catch (FileNotFoundException ex) {
      throw new IllegalStateException("File read error");
    }

    PrintWriter p;

    try {
      p = new PrintWriter(new FileOutputStream(new File("resources/testParse2")));
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
          .read(new Worksheet.Builder(), new FileReader("resources/testParse2"));
    } catch (FileNotFoundException ex) {
      throw new IllegalStateException("file not found");
    }

    assertEquals(true,
        worksheet.getCells().equals(worksheet2.getCells()));
  }

  /*
  checks whether formula and reference cells render as their raw values rather than the values to which they evaluate.
   */

  @Test
  public void testParseFormulaWithReference() {
    Worksheet worksheet;

    try {
      worksheet = WorksheetReader
          .read(new Worksheet.Builder(),
              new FileReader("resources/testParse2"));
    } catch (FileNotFoundException ex) {
      throw new IllegalStateException("File read error");
    }

    PrintWriter p;

    try {
      p = new PrintWriter(new FileOutputStream(new File("resources/testParse2")));
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
          .read(new Worksheet.Builder(), new FileReader("resources/testParse2"));
    } catch (FileNotFoundException ex) {
      throw new IllegalStateException("file not found");
    }

    assertEquals("=(SUM A1:A2)",
        worksheet2.getCellAt(new Coord(1, 3)).getRawValue());
  }

}