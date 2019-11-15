package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.Worksheet.Builder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
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
    String fileName;
    FileReader readFile;

    if (args.length > 4) {
      throw new IllegalArgumentException("Invalid command input!");
    }
    else if (args.length == 4) {
      // -in some-filename -save some-new-filename
      // -in some-filename -eval some-cell
      if (args[0].equals("-in") && args[2].equals("-eval")) {
        fileName = args[1];

        try {
          readFile = new FileReader(fileName);
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException("file not found");
        }
        Worksheet worksheet = WorksheetReader.read(new Builder(), readFile);

        Cell cell = worksheet.getCellAt(new Coord(args[3]));
        System.out.println(cell.toString());
      }
      else if (args[0].equals("-in") && args[2].equals("-save")) {
        // printwriter stuff goes here
        Worksheet worksheet;
        try {
          worksheet = WorksheetReader
              .read(new Worksheet.Builder(),
                  new FileReader(args[1]));
        } catch (FileNotFoundException ex) {
          throw new IllegalStateException("File read error");
        }

        PrintWriter p;

        try {
          p = new PrintWriter(new FileOutputStream(new File(args[3])));
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

      }
    }
    else if (args.length == 3) {
      // -in some-filename -gui
      fileName = args[1];
      try {
        readFile = new FileReader("resources/" + fileName);

      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("file not found");
      }
      Worksheet worksheet = WorksheetReader.read(new Builder(), readFile);
      WorksheetView view = new WorksheetVisualView(worksheet);
      try {
        view.render();
      } catch (IOException io) {
        throw new IllegalStateException("IO exception found");
      }
    }
    else if (args.length == 1) {
      // -gui
      if (args[0].equals("-gui")) {
        try {
          WorksheetView view = new WorksheetVisualView(new Worksheet());
          view.render();
        } catch (IOException io) {
          throw new IllegalStateException("IO exception found");
        }
      }
    }
    else {
      throw new IllegalArgumentException("Invalid commandline input");
    }
  }
}
