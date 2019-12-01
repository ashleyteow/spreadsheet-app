package edu.cs3500.spreadsheets.provider.view;

import edu.cs3500.spreadsheets.provider.controller.Features;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * A textual view implementation mainly to render the user input from a model to a new file. which
 * can be saved to all kinds of appendable given in the constructor.
 */
public class SpreadSheetTextualView implements SpreadSheetView {
  private SpreadSheetViewModel model;
  private Appendable ap;

  public SpreadSheetTextualView(SpreadSheetViewModel model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void render() {
    for (Coord co : model.getAllRawContents().keySet()) {
      try {
        ap.append(Coord.colIndexToName(co.col) + co.row + " " +
                model.getAllRawContents().get(co)).append("\n");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public Coord getSelected() {
    throw new UnsupportedOperationException("can't select a cell in the textual view");
  }

  @Override
  public void setTextField(String raw) {
    throw new UnsupportedOperationException("can't set text field in textual mode");
  }

  @Override
  public void resetFocus() {
    throw new UnsupportedOperationException("can't reset focus in textual mode");
  }

  @Override
  public void addFeatures(Features features) {
    return;
  }

  @Override
  public void setCellContent(Coord cord, String content) {
    return;
  }

  @Override
  public void close() {
    return;
  }

  @Override
  public void saveFile(String fileName) {
    try {
      File secondFile = new File(fileName);
      new FileOutputStream(secondFile).close();
      this.render();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
