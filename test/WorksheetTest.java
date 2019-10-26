import static org.junit.Assert.*;

import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.junit.Test;

public class WorksheetTest {

  @Test
  public void testReadingInput() throws FileNotFoundException {

    WorksheetReader.read(Worksheet.builder(),
        new FileReader("/Users/ashleyteow/IdeaProjects/BeyondGoodProject/testParse1"));
  }

}