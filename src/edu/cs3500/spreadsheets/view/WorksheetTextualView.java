package edu.cs3500.spreadsheets.view;

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
    return "";
  }

  @Override
  public void render() throws IOException {
    writable.append(toString());
  }
}
