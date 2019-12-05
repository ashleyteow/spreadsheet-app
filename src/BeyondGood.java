import edu.cs3500.spreadsheets.controller.ProviderController;
import edu.cs3500.spreadsheets.controller.WorksheetController;
import edu.cs3500.spreadsheets.controller.WorksheetGUIController;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.Worksheet.Builder;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.provider.controller.Features;
import edu.cs3500.spreadsheets.provider.view.EditableView;
import edu.cs3500.spreadsheets.provider.view.SpreadSheetView;
import edu.cs3500.spreadsheets.provider.view.SpreadSheetViewModel;
import edu.cs3500.spreadsheets.view.ReadWorksheet;
import edu.cs3500.spreadsheets.view.WorksheetTextualView;
import edu.cs3500.spreadsheets.view.WorksheetView;
import edu.cs3500.spreadsheets.view.WorksheetVisualView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   * @param args any command-line arguments
   */
  public static void main(String[] args) {
    if (args.length > 4) {
      throw new IllegalArgumentException("Too many arguments!");
    }

    switch(args[0]) {
      case "-in":
        if (args[2].equals("-save")) {
          saveHelp(args[1], args[3]);
          break;
        }
        else if (args[2].equals("-eval")) {
          evalHelp(args[1], args[3]);
          break;
        }
        else if (args[2].equals("-gui")) {
          guiHelp(args[1]);
          break;
        }
        else if (args[2].equals("-edit")) {
          editHelp(args[1]);
          break;
        }
        else if (args[2].equals("-provider")) {
          providerHelp(args[1]);
          break;
        }
        else {
          throw new IllegalArgumentException("Invalid third argument!");
        }
      case "-gui":
        guiHelp(null);
        break;
      case "-edit":
        editHelp(null);
        break;
      case "-provider":
        providerHelp(null);
        break;
      default:
        throw new IllegalArgumentException("Invalid arguments!");
    }
  }

  /**
   * Helper function that evaluates the cell at the given location.
   * @param fileName file name as in resources folder
   * @param coord location of cell to be evaluated
   */
  private static void evalHelp(String fileName, String coord) {
    Worksheet model;
    try {
      model = WorksheetReader
          .read(new Worksheet.Builder(),
              new FileReader("resources/" + fileName));
    } catch (FileNotFoundException ex) {
      throw new IllegalStateException("File not found");
    }
    String cell = model.getCellAt(new Coord(coord)).toString();
    if (cell.equals("")) {
      System.out.println("Empty cell");
    }
    else {
      System.out.println(cell);
    }
  }

  /**
   * Helper function that saves the first file's contents into another file with the name of file2.
   * @param file1 file being saved
   * @param file2 new file being created
   */
  private static void saveHelp(String file1, String file2) {
    Worksheet model;
    WorksheetView view;
    try {
      model = WorksheetReader
          .read(new Worksheet.Builder(),
              new FileReader("resources/" + file1));
    } catch (FileNotFoundException ex) {
      throw new IllegalStateException("File not found");
    }
    PrintWriter p;
    try {
      p = new PrintWriter(new FileOutputStream(new File(file2)));
    } catch (Exception ex) {
      throw new IllegalStateException("File not found");
    }
    view = new WorksheetTextualView(p, model);
    try {
      view.render();
    } catch (IOException ex) {
      throw new IllegalStateException("IO Error");
    }
    p.close();
  }

  /**
   * Helper function that handles displaying the interactive GUI view {@code WorksheetEditableView}.
   * @param fileName file name as in resources folder
   */
  private static void editHelp(String fileName) {
    Worksheet model;
    WorksheetController controller;
    if (fileName == null) {
      model = new Worksheet();
    }
    else {
      FileReader readFile;
      try {
        readFile = new FileReader(fileName);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("Error: File not found.");
      }
      model = WorksheetReader.read(new Builder(), readFile);
    }
    try {
      controller = new WorksheetGUIController(model);
      controller.start();
    } catch (IOException io) {
      throw new IllegalStateException("IO Error");
    }
  }

  /**
   * Helper function that handles displaying the read-only GUI view {@code WorksheetVisualView}.
   * @param fileName file name as in resources folder
   */
  private static void guiHelp(String fileName) {
    Worksheet model;
    WorksheetView view;
    if (fileName == null) {
      model = new Worksheet();
    }
    else {
      FileReader readFile;
      try {
        readFile = new FileReader("resources/" + fileName);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("Error: File not found.");
      }
      model = WorksheetReader.read(new Builder(), readFile);
    }
    view = new WorksheetVisualView(new ReadWorksheet(model));
    try {
      view.render();
    } catch (IOException io) {
      throw new IllegalStateException("IO Error");
    }
  }

  /**
   * Helper function that handles displaying the provider's interactive GUI
   * view {@code EditableView}.
   * @param fileName file name as in resources folder
   */
  private static void providerHelp(String fileName) {
    SpreadSheetViewModel adapter;
    SpreadSheetView view;
    Worksheet model;
    Features controller;
    if (fileName == null) {
      model = new Worksheet();
    }
    else {
      FileReader readFile;
      try {
        readFile = new FileReader("resources/" +fileName);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("Error: File not found.");
      }
      model = WorksheetReader.read(new Builder(), readFile);
    }
    adapter = new WorksheetAdapter(model);
    view = new EditableView(adapter);
    controller = new ProviderController(view, adapter);
    controller.start();
  }
}
