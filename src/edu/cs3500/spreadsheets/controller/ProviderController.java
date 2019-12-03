package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.provider.controller.Features;
import edu.cs3500.spreadsheets.provider.model.SpreadSheet;
import edu.cs3500.spreadsheets.provider.view.EditableView;
import java.io.File;

public class ProviderController implements Features {
  private EditableView view;
  private SpreadSheet providerModel;

  public ProviderController(EditableView view,
      SpreadSheet providerModel) {
    this.view = view;
    this.providerModel = providerModel;
  }


  @Override
  public void editCell(String str, int col, int row) {

  }

  @Override
  public void restore() {

  }

  @Override
  public void start() {

  }

  @Override
  public void load(File file) {

  }
}
