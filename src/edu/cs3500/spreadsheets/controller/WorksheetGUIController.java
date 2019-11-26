package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.view.FeaturesListener;
import edu.cs3500.spreadsheets.view.WorksheetEditableView;
import java.io.IOException;

public class WorksheetGUIController implements WorksheetController, FeaturesListener {
  WorksheetEditableView gui;
  Worksheet model;
  Coord coordToEdit;

  public WorksheetGUIController(Worksheet model) throws IOException {
    this.model = model;
    this.gui = new WorksheetEditableView(model, this);
  }


  @Override
  public void go() throws IOException {
    gui.render();
  }

  @Override
  public void confirmEdits(String newVal) {
    model.editCellAt(coordToEdit, newVal);
  }

  @Override
  public void setEditableCoord(Coord coord) {
    this.coordToEdit = coord;
  }

  @Override
  public Cell getCellToEdit() {
    return model.getCellAt(this.coordToEdit);
  }
}
