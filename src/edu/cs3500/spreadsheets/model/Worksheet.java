package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import edu.cs3500.spreadsheets.sexp.SString;
import java.util.ArrayList;

// another class implements worksheet builder
public class Worksheet {

  public static final class Builder implements WorksheetBuilder<Worksheet> {

    @Override
    public WorksheetBuilder<Worksheet> createCell(int col, int row, String contents) {
      return null;
    }

    @Override
    public Worksheet createWorksheet() {
      return null;
    }
  }

  private ArrayList<ArrayList<Cell>> cellList = new ArrayList<ArrayList<Cell>>();
  private WorksheetReader worksheetReader = new WorksheetReader();

  private Worksheet() {
    for (int i = 0; i < 200; i++) {
      cellList.add(new ArrayList<Cell>(706));
    }
  }

}
