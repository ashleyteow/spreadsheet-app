package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.ICell;
import edu.cs3500.spreadsheets.model.IWorksheet;
import edu.cs3500.spreadsheets.view.FeaturesListener;
import edu.cs3500.spreadsheets.view.WorksheetEditableView;
import java.io.IOException;


public class WorksheetGUIController implements WorksheetController, FeaturesListener {
  WorksheetEditableView gui;
  IWorksheet model;
  Coord coordToEdit;

  public WorksheetGUIController(IWorksheet model) throws IOException {
    this.model = model;
    this.gui = new WorksheetEditableView(model, this);
    // default
    this.coordToEdit = new Coord(1,1);
    this.gui.addFeatures(this);
  }


  @Override
  public void start() throws IOException {
    gui.render();
  }


  @Override
  public void confirmEdits(String newVal) {
    model.editCellAt(coordToEdit, newVal);
    gui.displayRawCellValue(newVal);
    gui.refresh();
  }


  @Override
  public void getCellToEdit() {
    ICell cell = model.getCellAt(this.coordToEdit);
    gui.displayRawCellValue(cell.getRawValue());
  }

  @Override
  public void rejectEdits() {
    ICell cell = model.getCellAt(this.coordToEdit);
    gui.displayRawCellValue(cell.getRawValue());
  }

  @Override
  public void setCoordToEdit(Coord coord) {
    this.coordToEdit = coord;
  }

}
