package edu.cs3500.spreadsheets.provider.view;


import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;;


import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

import java.util.HashMap;
import java.util.Map;

import javax.swing.border.Border;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * a panel contains all cells in our sheet, just for the content part, not includes the header
 * part.
 */
public class SpreadSheetPanel extends JPanel implements MouseListener {

  static final int Cell_Width = 70;
  static final int Cell_Heighth = 25;
  static final int DEFAULT_SIZE = 15;
  private static final Border RED_BOARDER = BorderFactory.createLineBorder(Color.RED, 2);
  private static final Border BLACK_BOARDER = BorderFactory.createLineBorder(Color.BLACK);
  private JScrollPane jsp;
  private JPanel content;
  private HeaderPanel westHeader;
  private HeaderPanel headerPanel;
  private int colMax;
  private int rowMax;
  private MouseListener adapter;

  private CellLabel selectedLabel;

  /**
   * constructs a spreadsheet panel with current model, not set to visible yet.
   *
   * @param model current read-only model
   */
  public SpreadSheetPanel(SpreadSheetViewModel model) {
    super();
    this.colMax = Math.max(model.getMaxCoord().col, DEFAULT_SIZE);
    this.rowMax = Math.max(model.getMaxCoord().row, DEFAULT_SIZE);
    this.content = new JPanel();
    configureKeyBoardListener();


    content.setLayout(new GridBagLayout());

    for (int i = 1; i <= rowMax; i++) {
      for (int j = 1; j <= colMax; j++) {
        CellLabel jl = new CellLabel(new Coord(j, i), "", SwingConstants.CENTER);
        if (model.getSheet().containsKey(new Coord(j, i))) {
          jl.setText(model.getSheet().get(new Coord(j, i)).eval
                  (model.getSheet(), false).getString());
        }
        jl.setPreferredSize(new Dimension(Cell_Width, Cell_Heighth));
        jl.setBorder(BLACK_BOARDER);
        jl.addMouseListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = j - 1;
        c.gridy = i - 1;
        content.add(jl, c);
      }
    }

    this.westHeader = new HeaderPanel(rowMax, true);
    this.headerPanel = new HeaderPanel(colMax, false);

    jsp = new JScrollPane(content);
    jsp.setRowHeaderView(westHeader);
    jsp.setColumnHeaderView(headerPanel);
    jsp.setPreferredSize(new Dimension(Cell_Width * DEFAULT_SIZE,
            Cell_Heighth * DEFAULT_SIZE + Cell_Heighth / 2));
    JLabel sign = new JLabel("row\uD83E\uDC13 col\uD83E\uDC12", SwingConstants.CENTER);
    sign.setPreferredSize(new Dimension(Cell_Width,
            Cell_Heighth));
    sign.setOpaque(true);
    sign.setBackground(Color.GREEN);
    sign.setBorder(BorderFactory.createLineBorder(Color.black));
    jsp.setCorner(JScrollPane.UPPER_LEFT_CORNER,
            sign);

    //panel resizes when resize window
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        if (e.getComponent().getWidth() > SpreadSheetPanel.this.jsp.getWidth() + 100) {
          addColumn();
          jsp.setSize(e.getComponent().getSize().width - 50, jsp.getHeight());
        }

        if (e.getComponent().getHeight() > SpreadSheetPanel.this.jsp.getHeight() + 170) {
          addRow();
          jsp.setSize(jsp.getWidth(), e.getComponent().getSize().height - 120);
        }
      }
    });


    this.selectedLabel = (CellLabel) content.getComponent(0);
    selectedLabel.setBorder(RED_BOARDER);

    this.add(jsp);


    //enable infinite scrolling
    jsp.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

      @Override
      public void adjustmentValueChanged(AdjustmentEvent event) {
        if ((float) jsp.getVerticalScrollBar().getValue() / (jsp.getVerticalScrollBar().getMaximum()
                -
                jsp.getVerticalScrollBar().getVisibleAmount()) > 0.95) {
          addRow();
        }
      }

    });

    jsp.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {

      @Override
      public void adjustmentValueChanged(AdjustmentEvent event) {
        if ((float) jsp.getHorizontalScrollBar().getValue() /
                (jsp.getHorizontalScrollBar().getMaximum() -
                        jsp.getHorizontalScrollBar().getVisibleAmount()) > 0.95) {
          addColumn();
        }
      }
    });
  }

  private void addRow() {
    for (int i = 1; i <= colMax; i++) {
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = i - 1;
      c.gridy = rowMax - 1;
      CellLabel jl = new CellLabel(new Coord(i, rowMax), "", SwingConstants.CENTER);
      addLabel(jl, c);
    }
    rowMax++;
    westHeader.addOneUnit();
  }

  private void addColumn() {
    for (int i = 1; i <= rowMax; i++) {
      GridBagConstraints c = new GridBagConstraints();
      c.gridy = i - 1;
      c.gridx = colMax - 1;
      CellLabel jl = new CellLabel(new Coord(colMax, i), "", SwingConstants.CENTER);
      addLabel(jl, c);
    }
    colMax++;
    headerPanel.addOneUnit();
  }


//  private void enableInfiniteScroll(JScrollBar bar, int headerLength1,
//                                    int headerLength2, HeaderPanel panel,boolean isVertical) {
//    if ((float) bar.getValue() / (bar.getMaximum()
//            -
//            bar.getVisibleAmount()) > 0.95) {
//      for (int i = 1; i <= headerLength1; i++) {
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridx = i - 1;
//        c.gridy = headerLength2 - 1;
//        if (isVertical) {
//          CellLabel jl = new CellLabel(new Coord(headerLength2, i), "", SwingConstants.CENTER);
//          addLabel(jl, c);
//        } else {
//          CellLabel j2 = new CellLabel(new Coord(i, headerLength2), "", SwingConstants.CENTER);
//          addLabel(j2, c);
//        }
//      }
//    }
//    headerLength2++;
//    panel.addOneUnit();
//  }

  private void configureKeyBoardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<Character, Runnable>();
    Map<Integer, Runnable> keyPresses = new HashMap<Integer, Runnable>();
    Map<Integer, Runnable> keyReleases = new HashMap<Integer, Runnable>();

    keyPresses.put(KeyEvent.VK_UP, () -> {
              if (selectedLabel.cord.row != 1) {
                selectedLabel.setBorder(BLACK_BOARDER);
                selectedLabel =
                        getCellLabel(new Coord(selectedLabel.cord.col, selectedLabel.cord.row - 1));
                selectedLabel.setBorder(RED_BOARDER);
              }
            }
    );

    keyPresses.put(KeyEvent.VK_DOWN, () -> {
      if (selectedLabel.cord.row < rowMax - 1) {
        selectedLabel.setBorder(BLACK_BOARDER);
        selectedLabel =
                getCellLabel(new Coord(selectedLabel.cord.col, selectedLabel.cord.row + 1));
        selectedLabel.setBorder(RED_BOARDER);
      }
    });

    keyPresses.put(KeyEvent.VK_LEFT, () -> {
      if (selectedLabel.cord.col != 1) {
        selectedLabel.setBorder(BLACK_BOARDER);
        selectedLabel =
                getCellLabel(new Coord(selectedLabel.cord.col - 1, selectedLabel.cord.row));
        selectedLabel.setBorder(RED_BOARDER);
      }
    });

    keyPresses.put(KeyEvent.VK_RIGHT, () -> {
      if (selectedLabel.cord.col < colMax - 1) {
        selectedLabel.setBorder(BLACK_BOARDER);
        selectedLabel =
                getCellLabel(new Coord(selectedLabel.cord.col + 1, selectedLabel.cord.row));
        selectedLabel.setBorder(RED_BOARDER);
      }
    });


    KeyboardListener kbd = new KeyboardListener(keyTypes, keyPresses, keyReleases);

    this.addKeyListener(kbd);
  }


  private void addLabel(CellLabel jl, GridBagConstraints c) {
    jl.setPreferredSize(new Dimension(Cell_Width, Cell_Heighth));
    jl.setBorder(BLACK_BOARDER);
    jl.addMouseListener(SpreadSheetPanel.this);
    jl.addMouseListener(adapter);
    content.add(jl, c);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    return;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    CellLabel jb = (CellLabel) e.getSource();
    if (selectedLabel == null) {
      this.selectedLabel = jb;
      selectedLabel.setBorder(RED_BOARDER);
    } else {
      selectedLabel.setBorder(BLACK_BOARDER);
      this.selectedLabel = jb;
      selectedLabel.setBorder(RED_BOARDER);


      this.requestFocus();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    return;
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    return;
  }

  @Override
  public void mouseExited(MouseEvent e) {
    return;
  }


  /**
   * get the selected coordinate in the content panel.
   *
   * @return selected coordinate in the content panel.
   */
  protected Coord getSelected() {
    if (selectedLabel == null) {
      return null;
    }
    return selectedLabel.cord;
  }

  private CellLabel getCellLabel(Coord cord) {
    return (CellLabel) content
            .getComponentAt((int) (cord.col - 0.5) * SpreadSheetPanel.Cell_Width,
                    (int) (cord.row - 0.5) * SpreadSheetPanel.Cell_Heighth);
  }

  /**
   * set the cell in the given coordinate to the desired content.
   *
   * @param str  the content to be set to that cell
   * @param cord where the coordinate is
   */
  protected void setCell(Coord cord, String str) {
    CellLabel jb = getCellLabel(cord);
    jb.setText(str);
  }


  protected void setMouseAdapter(MouseListener ml) {
    this.adapter = ml;
    for (Component c : content.getComponents()) {
      c.addMouseListener(this.adapter);
    }
  }
}
