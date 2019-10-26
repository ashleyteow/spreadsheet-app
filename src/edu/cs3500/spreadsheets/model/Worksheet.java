package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import java.util.ArrayList;

// another class implements worksheet builder
public class Worksheet {
//  private ArrayList<ArrayList<Cell>> workSheetCells = new ArrayList<>(200);

  public static Builder builder() {
    return new Builder(200);
  }

  public static final class Builder implements WorksheetBuilder<Worksheet> {
    private ArrayList<ArrayList<Cell>> workSheetCells;

    private Builder(int initialCapacity) {
      workSheetCells = new ArrayList<ArrayList<Cell>>(initialCapacity);
      for (int col = 0; col < initialCapacity; col++) {
        ArrayList<Cell> row = new ArrayList<>(initialCapacity);
        for (int rowNum = 0; rowNum < initialCapacity; rowNum++) {
          row.add(new Cell(new Coord(col + 1, rowNum + 1), ""));
        }
        workSheetCells.add(row);
      }
    }

    @Override
    public WorksheetBuilder<Worksheet> createCell(int col, int row, String contents) {
      Coord coordinate = new Coord(col, row);
      System.out.println("col:" + col);
      System.out.println("row:" + row);
      System.out.println(workSheetCells.size());

      Cell cell = new Cell(coordinate, contents);
      workSheetCells.get(col).set(row, cell);
      System.out.println(cell);
      return this;
    }

    @Override
    public Worksheet createWorksheet() {
//      for (int col = 0; col < workSheetCells.size(); col++) {
//        ArrayList<Cell> row = new ArrayList<>(workSheetCells.size());
//        for (int rowNum = 0; rowNum < workSheetCells.size(); rowNum++) {
////          row.add(createCell(col, rowNum, ____));
//        }
//      }
      return new Worksheet(workSheetCells);
    }

    private Worksheet createWorksheet(Readable readable) {
      return WorksheetReader.read(this, readable);
    }
  }

  private Worksheet(ArrayList<ArrayList<Cell>> cells) {
    new ArrayList<ArrayList<Cell>>(cells);
  }

}
