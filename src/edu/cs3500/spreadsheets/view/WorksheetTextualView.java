package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.ICell;
import java.io.IOException;

/**
 * Represents a textual view of a {@code Worksheet} and enables the user to save their edits.
 */
public class WorksheetTextualView implements WorksheetView {
  private Appendable writable;
  private WorksheetViewModel model;

  /**
   * Constructs a new textual view for a {@code Worksheet}.
   * @param writable appendable object
   * @param worksheet model
   */
  public WorksheetTextualView(Appendable writable, WorksheetViewModel worksheet) {
    this.writable = writable;
    this.model = worksheet;
  }

  @Override
  public String toString() {
    String result = "";
    for (ICell c : model.getCells()) {
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

  @Override
  public void refresh()throws IOException {
    // Not applicable for a textual view, used only in WorksheetVisualView
    writable.append(toString());
  }

  @Override
  public void addFeatures(FeaturesListener features) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void displayRawCellValue(String rawVal) {
    throw new UnsupportedOperationException();
  }


}
