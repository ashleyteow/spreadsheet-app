package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WorksheetEditableView extends JFrame implements WorksheetView {
  private static int screenWidth = WorksheetVisualView.SCREENWIDTH;
  private static int screenHeight = WorksheetVisualView.SCREENHEIGHT;
  private final Worksheet worksheet;
  private WorksheetPanel worksheetPanel;
  private final WorksheetCell[][] worksheetCells;
  private FeaturesListener listener;
  private EditBarPanel editBar;

  public WorksheetEditableView(Worksheet worksheet, FeaturesListener listener) {
    super();
    this.listener = listener;
    this.worksheet = worksheet;

    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.editBar = new EditBarPanel();
    this.worksheetPanel = new WorksheetPanel(worksheet);
    this.worksheetCells = new WorksheetCell[screenHeight][screenWidth];

    this.add(editBar, BorderLayout.NORTH);
    this.add(worksheetPanel, BorderLayout.CENTER);
    this.pack();
    this.setLocationByPlatform(true);

    // fill main grid panel
    for (int i = 0; i < screenHeight; i++) {
      for (int j = 0; j < screenWidth; j++) {
        worksheetCells[i][j] = new WorksheetCell(worksheetPanel.worksheetCells[i][j], i, j);
      }
    }

    this.addListeners();

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

  /**
   * Adds the {@link FeaturesListener} as a listener for the worksheet cells.
   */
  private void addListeners() {
    // something like
    // worksheetCells[i][j].field.addFocusListener(this);
  }



  private class EditBarPanel extends JPanel implements ActionListener {

    JButton confirm = new JButton("Confirm");
    JTextField newText = new JTextField(30);

    EditBarPanel() {
      super(new GridLayout(1,2));
      this.add(confirm);
      confirm.addActionListener(this);
      this.add(newText);
      newText.setEditable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      listener.confirmEdits(newText.getText());
      WorksheetEditableView.this.refresh();
    }

    void setEditableCell(Coord coord) {
      listener.setEditableCoord(coord);
      Cell c = listener.getCellToEdit();
      newText.setText(c.getRawValue());
    }

  }
}
