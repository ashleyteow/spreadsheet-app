import edu.cs3500.spreadsheets.controller.WorksheetController;
import edu.cs3500.spreadsheets.controller.WorksheetGUIController;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.Worksheet.Builder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Tests that the triangular numbers pattern is properly read in to the program.
 */
public class TestTriangularNumbers {

  /**
   * Main method runs and creates a triangle input file.
   * @param args passed from command line.
   */
  public static void main(String[] args) throws FileNotFoundException {
    FileReader readFile = new FileReader("resources/" + "triangularNumbers");
    Worksheet worksheet = WorksheetReader.read(new Builder(), readFile);

    try {
      WorksheetController control = new WorksheetGUIController(worksheet);
      control.start();
    } catch (IOException io) {
      throw new IllegalStateException();
    }
  }
}
