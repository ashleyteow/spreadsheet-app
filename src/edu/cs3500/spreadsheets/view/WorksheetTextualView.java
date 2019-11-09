package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Worksheet;
import java.io.IOException;

public class WorksheetTextualView implements WorksheetView {
  private Appendable writable;
  private Worksheet model;

  public WorksheetTextualView(Appendable writable, Worksheet worksheet) {
    this.writable = writable;
    this.model = worksheet;
  }

  @Override
  public String toString() {
    String result = "";
    for (Cell c : model.getCells()) {
      result += c.getCoord();
      result += "  ";
      result += c.getRawValue();
      result += "\n";
    }
    return result;
  }

  @Override
  public void render() throws IOException {
    writable.append(toString());
  }
}
