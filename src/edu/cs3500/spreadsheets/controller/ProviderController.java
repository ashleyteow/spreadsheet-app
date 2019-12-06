package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.provider.controller.Features;
import edu.cs3500.spreadsheets.provider.view.SpreadSheetView;
import edu.cs3500.spreadsheets.provider.view.SpreadSheetViewModel;
import java.io.File;

/**
 * A controller that works with our provider's view classes.
 */
public class ProviderController implements Features {
  private SpreadSheetView view;
  private SpreadSheetViewModel providerModel;

  /**
   * Initializes the provider's view and model to work with our view.
   * @param view the provider's view
   * @param providerModel the provider's model
   */
  public ProviderController(SpreadSheetView view, SpreadSheetViewModel providerModel) {
    this.view = view;
    this.providerModel = providerModel;
    this.view.addFeatures(this);
  }

  @Override
  public void editCell(String str, int col, int row) {
    // provider uses private recursive helper called updateCell(Coord) that
    // gets the dependencies of the given coordinate out from the model

    // it then commands the model to reevaluate these cells

    // it gets the evaluated value(now updated) out to the
    // view and set the content to with that data

    // it calls itself again with the dependencies and
    // their Coords to update the cell recursively
  }

  @Override
  public void restore() {
    // get Cell
    // set formula text panel to be the original cell before edits
  }

  @Override
  public void start() {
    view.render();
  }

  @Override
  public void load(File file) {
    // we did not implement this functionality from our GUI
  }
}
