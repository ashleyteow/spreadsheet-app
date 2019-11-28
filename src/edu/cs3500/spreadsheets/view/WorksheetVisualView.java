package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.ReadWorksheet;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * Represents a GUI view of a {@code Worksheet}.
 */
public class WorksheetVisualView extends JFrame implements WorksheetView {
  private WorksheetPanel worksheetPanel;
  public static int SCREENWIDTH = 12;
  public static int SCREENHEIGHT = 20;

  /**
   * Constructs a {@code WorksheetVisualView} object.
   * @param model worksheet
   */
  public WorksheetVisualView(ReadWorksheet model) {
    this.setTitle("Excel");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.worksheetPanel = new WorksheetPanel(model);
    this.add(worksheetPanel, BorderLayout.CENTER);
    this.pack();
    this.setLocationByPlatform(true);
  }


  @Override
  public void render() throws IOException {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.worksheetPanel.refresh();
    this.pack();
    this.repaint();
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
