package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
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
    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it, 
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */
    String fileName;
    String cellName = "";
    FileReader readFile;

    if (args.length == 4 && args[0].equals("-in") && args[2].equals("-eval")) {
      fileName = args[1];
      cellName = args[3];
    } else {
      throw new IllegalArgumentException("invalid command line input");
    }

    try {
      readFile = new FileReader(fileName);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file not found");
    }

    Worksheet worksheet = WorksheetReader.read(Worksheet.builder(), readFile);
    if (!worksheet.evaluateCells()) {
      System.out.println("Error");
    }

    int cellCol = Coord.colNameToIndex(cellName.substring(0, 1));
    int cellRow = Integer.parseInt(cellName.substring(1));

    Cell cell = worksheet.getCellAt(cellCol - 1, cellRow - 1);
    System.out.println(cell.getCellContents());
  }
}
