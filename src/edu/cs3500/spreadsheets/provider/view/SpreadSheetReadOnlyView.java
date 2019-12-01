package edu.cs3500.spreadsheets.provider.view;

import edu.cs3500.spreadsheets.provider.controller.Features;
import edu.cs3500.spreadsheets.model.Coord;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;

/**
 * an visual implementation of the view to display the sheet and header.
 */
public class SpreadSheetReadOnlyView extends JFrame implements SpreadSheetView {
  private SpreadSheetViewModel model;
  private SpreadSheetPanel sheetPanel;

  /**
   * construct an visual view of the spreadsheet with the information from the given model. with the
   * functionality of display all non-empty cells and scrolling.
   *
   * @param model the read-only model.
   */
  public SpreadSheetReadOnlyView(SpreadSheetViewModel model) {
    super();
    this.model = model;
    this.setTitle("SpreadSheet");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.sheetPanel = new SpreadSheetPanel(model);


    this.add(sheetPanel, BorderLayout.CENTER);

    this.pack();
  }

  @Override
  public void render() {
    this.setVisible(true);
  }

  @Override
  public Coord getSelected() {
    return this.sheetPanel.getSelected();
  }

  @Override
  public void setTextField(String raw) {
    return;
  }

  @Override
  public void resetFocus() {
    this.requestFocus();
  }

  @Override
  public void addFeatures(Features features) {
    return;
  }

  @Override
  public void setCellContent(Coord cord, String content) {
    throw new UnsupportedOperationException("can't set cell in read only mode");
  }


  @Override
  public void close() {
    this.dispose();
  }

  @Override
  public void saveFile(String fileName) {
    try {
      PrintWriter wr = new PrintWriter(new File(fileName));
      SpreadSheetView textualView = new SpreadSheetTextualView(model, wr);
      textualView.saveFile(fileName);
      wr.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
