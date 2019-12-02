package edu.cs3500.spreadsheets.provider.view;

import edu.cs3500.spreadsheets.provider.controller.Features;
import edu.cs3500.spreadsheets.model.Coord;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JFileChooser;


/**
 * A editable spreadsheet view to support editable features for users to make edit on the sheet.
 */
public class EditableView extends JFrame implements SpreadSheetView {

  private SpreadSheetView textualView;

  private SpreadSheetViewModel model;
  private SpreadSheetPanel sheetPanel;
  private JPanel editableArea;

  private JTextField textField;
  private JButton confirm;
  private JButton cancel;
  private JMenuItem save;
  private JMenuItem load;

  private JMenuBar menu;


  /**
   * constructs a editable view with a readonly model.
   *
   * @param model the immutable model
   */
  public EditableView(SpreadSheetViewModel model) {
    super();
    this.model = model;
    this.setTitle("Editable SpreadSheet");


    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    this.setLayout(new BorderLayout());
    this.sheetPanel = new SpreadSheetPanel(model);


    this.editableArea = new JPanel();
    this.editableArea.setLayout(new FlowLayout());

    this.confirm = new JButton("\uD83D\uDC4D");
    this.confirm.setPreferredSize(new Dimension(50, 25));


    this.cancel = new JButton("\uD83D\uDEC7");
    this.cancel.setPreferredSize(new Dimension(50, 25));
    this.textField = new JTextField(10);
    textField.setColumns(65);


    JMenu m = new JMenu("File");
    save = new JMenuItem("Save");
    load = new JMenuItem("Load");
    m.add(save);
    m.add(load);
    menu = new JMenuBar();
    menu.add(m);
    menu.setPreferredSize(new Dimension(50, 25));
    this.setJMenuBar(menu);


    //setting up the saving file feature
    save.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        try {
          String des = JOptionPane.showInputDialog("please enter the file destination");
          EditableView.this.saveFile(des);
        } catch (NullPointerException ex) {
          ex.getMessage();
        }

      }
    });

    editableArea.add(confirm);
    editableArea.add(cancel);
    editableArea.add(textField);

    this.add(editableArea, BorderLayout.NORTH);


    //setting up the panel
    sheetPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
    this.add(sheetPanel, BorderLayout.WEST);

    //Add windows resizable
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        sheetPanel.setPreferredSize(sheetPanel.getPreferredSize());
        sheetPanel.setSize(e.getComponent().getSize());
        if ((e.getComponent().getSize().height < sheetPanel.getPreferredSize().height)
                || (e.getComponent().getSize().width < sheetPanel.getPreferredSize().width)) {
          EditableView.this.pack();
        }
      }
    });


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
    this.textField.setText(raw);
  }

  @Override
  public void resetFocus() {
    sheetPanel.setFocusable(true);
    sheetPanel.requestFocus();
  }

  @Override
  public void addFeatures(Features features) {
    confirm.addActionListener(actionEvent ->
            features.editCell(textField.getText(), getSelected().col, getSelected().row));
    cancel.addActionListener(actionEvent -> features.restore());
    sheetPanel.setMouseAdapter(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        features.restore();
        sheetPanel.requestFocus();
      }
    });


    //add load features
    load.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(EditableView.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fc.getSelectedFile();
          //This is where a real application would open the file.
          features.load(file);
        }
      }
    });

    sheetPanel.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
          case KeyEvent.VK_UP:
          case KeyEvent.VK_DOWN:
          case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
              features.restore();
              break;
          default:
            break;
        }
      }
    });

    //delete cell feature
    sheetPanel.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\u007F') {
          features.editCell("", getSelected().col, getSelected().row);
        }
      }
    });

  }



  @Override
  public void setCellContent(Coord cord, String content) {
    sheetPanel.setCell(cord, content);
  }

  @Override
  public void close() {
    this.dispose();
  }

  @Override
  public void saveFile(String fileName) {
    try {
      PrintWriter wr = new PrintWriter(new File(fileName));
      textualView = new SpreadSheetTextualView(model, wr);
      textualView.saveFile(fileName);
      wr.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
