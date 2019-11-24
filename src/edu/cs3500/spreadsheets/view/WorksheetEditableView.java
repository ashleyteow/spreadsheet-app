package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Worksheet;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class WorksheetEditableView extends JFrame implements WorksheetView {
  private static int screenWidth = WorksheetVisualView.SCREENWIDTH;
  private static int screenHeight = WorksheetVisualView.SCREENHEIGHT;
  private final Worksheet worksheet;
  private WorksheetPanel worksheetPanel;
  private final JTextField[][] worksheetCells;
  private FeaturesListener listener;

  public WorksheetEditableView(Worksheet worksheet, FeaturesListener listener) {
    super();
    this.listener = listener;
    this.worksheet = worksheet;

    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());

    worksheetCells = new JTextField[screenHeight][screenWidth];

    this.worksheetPanel = new WorksheetPanel(worksheet);

    this.add(worksheetPanel, BorderLayout.CENTER);
    this.pack();
    this.setLocationByPlatform(true);

    for (int i = 0; i < screenHeight; i++) {
      for (int j = 0; j < screenWidth; j++) {
        listener.addTextEvent(worksheetCells[i][j]);
      }
    }

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
}
