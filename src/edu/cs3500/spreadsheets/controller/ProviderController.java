package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.provider.controller.Features;
import edu.cs3500.spreadsheets.provider.view.SpreadSheetView;
import edu.cs3500.spreadsheets.provider.view.SpreadSheetViewModel;
import java.io.File;

public class ProviderController implements Features {
  private SpreadSheetView view;
  private SpreadSheetViewModel providerModel;

  public ProviderController(SpreadSheetView view,
      SpreadSheetViewModel providerModel) {
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
    view.render();
  }

  @Override
  public void load(File file) {

  }
}
