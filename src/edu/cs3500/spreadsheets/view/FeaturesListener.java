package edu.cs3500.spreadsheets.view;

import javax.swing.JTextField;

public interface FeaturesListener {

  /**
   * Adds this as a ViewEvent listener to the given {@link javax.swing.JTextField}.
   */
  void addTextEvent(JTextField field);
}
