package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Worksheet;
import java.io.IOException;

/**
 * Represents a textual view of a {@code Worksheet} and enables the user to save their edits.
 */
public class WorksheetTextualView implements WorksheetView {
  private Appendable writable;
  private Worksheet model;

  /**
   * Constructs a new textual view for a {@code Worksheet}.
   * @param writable appendable object
   * @param worksheet model
   */
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

  }


}
