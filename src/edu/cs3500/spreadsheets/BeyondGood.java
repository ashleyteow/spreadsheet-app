package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.Worksheet.Builder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
    String cellName = "";
    FileReader readFile;

    if (args.length == 4 && args[0].equals("-in") && args[2].equals("-eval")) {
      fileName = args[1];
      cellName = args[3];
    } else {
      throw new IllegalArgumentException("Invalid command line input");
    }

    try {
      readFile = new FileReader(fileName);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file not found");
    }

    Worksheet worksheet = WorksheetReader.read(new Builder(), readFile);
    if (!worksheet.evaluateCells()) {
      System.out.println("Error");
    }

    Cell cell = worksheet.getCellAt(new Coord(args[3]));
    System.out.println(cell.toString());
  }
}
