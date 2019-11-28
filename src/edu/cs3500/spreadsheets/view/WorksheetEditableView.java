package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.IWorksheet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Represents an implementation of {@code WorksheetView} that allows users to manipulate values that
 * they see on the GUI interface.
 */
public class WorksheetEditableView extends JFrame implements WorksheetView, FocusListener {

  private static int screenWidth = WorksheetVisualView.SCREENWIDTH;
  private static int screenHeight = WorksheetVisualView.SCREENHEIGHT;
  private WorksheetPanel worksheetPanel;
  private IWorksheet worksheet;
  private final WorksheetCell[][] worksheetCells;
  private JButton confirmBtn;
  private JButton cancelBtn;
  private JTextField formulaTextPanel;
  private JPanel formulaBarPanel;
  private FeaturesListener listener;

  /**
   * Constructs an editable view of a {@code Worksheet}.
   *
   * @param worksheet mutable worksheet
   * @param listener  listener
   */
  public WorksheetEditableView(IWorksheet worksheet, FeaturesListener listener) {
    super();
    this.listener = listener;
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.worksheetPanel = new WorksheetPanel(worksheet);
    this.worksheetCells = fillGrid();
    this.worksheet = worksheet;

    // creates edit formula container and sets configurations
    formulaBarPanel = new JPanel();
    formulaBarPanel.setLayout(new FlowLayout());

    // create confirm button for edits
    confirmBtn = new JButton("✔");
    confirmBtn.setPreferredSize(new Dimension(80, 32));

    // create reject button for edits
    cancelBtn = new JButton("X");
    cancelBtn.setPreferredSize(new Dimension(80, 32));

    // creates edit text bar panel
    formulaTextPanel = new JTextField("", 90);
    formulaTextPanel.setEditable(true);

    formulaBarPanel.add(confirmBtn);
    formulaBarPanel.add(cancelBtn);
    formulaBarPanel.add(formulaTextPanel);
    this.add(formulaBarPanel, BorderLayout.NORTH);
    this.add(worksheetPanel, BorderLayout.CENTER);
    this.setLocationByPlatform(true);
    this.pack();

    this.addCellListeners();
  }

  @Override
  public void render() throws IOException {
    this.worksheetCells[0][0].cellPanel.requestFocus();
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
    confirmBtn.addActionListener(evt -> features.confirmEdits(formulaTextPanel.getText()));
    cancelBtn.addActionListener(evt -> features.rejectEdits(formulaTextPanel.getText()));
    for (int i = 0; i < screenHeight; i++) {
      for (int j = 0; j < screenWidth; j++) {
        worksheetCells[i][j].cellPanel.addActionListener(evt -> features.getCellToEdit());
      }
    }
  }

  @Override
  public void displayRawCellValue(String rawVal) {
    formulaTextPanel.setText(rawVal);
  }

  @Override
  public void focusGained(FocusEvent e) {
    JTextField field = (JTextField) e.getComponent();
    field.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
    formulaTextPanel.setText(worksheet.getCellAt(getCoord(field)).getRawValue());
    listener.setCoordToEdit(getCoord(field));
  }

  @Override
  public void focusLost(FocusEvent e) {
    JTextField field = (JTextField) e.getComponent();
    field.setBorder(new JTextField().getBorder());
  }

  /**
   * Gets the coordinate of the cell from the given textfield.
   *
   * @param field the textfield in the grid of cells
   * @return the coordinate of the cell
   */
  private Coord getCoord(JTextField field) {
    int row = -1;
    int col = -1;
    boolean match = false;
    for (int i = 0; i < screenHeight; i++) {
      if (match) {
        break;
      } else {
        for (int j = 0; j < screenWidth; j++) {
          if (worksheetCells[i][j].cellPanel.equals(field)) {
            row = i;
            col = j;
            match = true;
            break;
          }
        }
      }
    }
    if (row == -1 || col == -1) {
      throw new IllegalStateException("Invalid text field");
    }
    return new Coord(col + worksheetPanel.topLeftCol + 1, row + worksheetPanel.topLeftRow + 1);
  }

  /**
   * Populate the 2d grid of cells from {@code WorksheetPanel}.
   *
   * @return 2D grid of cells
   */
  private WorksheetCell[][] fillGrid() {
    WorksheetCell[][] grid = new WorksheetCell[screenHeight][screenWidth];
    for (int i = 0; i < screenHeight; i++) {
      for (int j = 0; j < screenWidth; j++) {
        grid[i][j] = new WorksheetCell(worksheetPanel.worksheetCells[i][j], i, j);
      }
    }
    return grid;
  }

  /**
   * Adds the {@link FocusListener} as a listener for the worksheet cells.
   */
  private void addCellListeners() {
    for (int i = 0; i < screenHeight; i++) {
      for (int j = 0; j < screenWidth; j++) {
        worksheetCells[i][j].cellPanel.addFocusListener(this);
      }
    }
  }
}
