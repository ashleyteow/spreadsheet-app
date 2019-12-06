package edu.cs3500.spreadsheets.provider.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * a customized key listener with maps of keys and function objects.
 */
public class KeyboardListener implements KeyListener {
  private Map<Character, Runnable> keyTypedMap;
  private Map<Integer, Runnable> keyPressedMap;
  private Map<Integer, Runnable> keyReleasedMap;


  /**
   * constructs maps of keys with their functionality.
   *
   * @param keyTypedMap functions when key typed
   * @param keyPressedMap functions when key presses
   * @param keyReleasedMap functions when key released
   */
  public KeyboardListener(Map<Character, Runnable> keyTypedMap,
                          Map<Integer, Runnable> keyPressedMap,
                          Map<Integer, Runnable> keyReleasedMap ) {
    this.keyTypedMap = keyTypedMap;
    this.keyPressedMap = keyPressedMap;
    this.keyReleasedMap = keyReleasedMap;
  }

  /**
   * This is called when the view detects that a key has been typed.
   * Find if anything has been mapped to this key character and if so, execute it
   *
   * @param e keyEvent
   *
   * @attribute from lecture note
   */

  @Override
  public void keyTyped(KeyEvent e) {
    if (keyTypedMap.containsKey(e.getKeyChar())) {
      keyTypedMap.get(e.getKeyChar()).run();
    }
  }

  /**
   * This is called when the view detects that a key has been pressed.
   * Find if anything has been mapped to this key code and if so, execute it
   *
   * @param e keyEvent
   *
   * @attribute from lecture note
   */

  @Override
  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  /**
   * This is called when the view detects that a key has been released.
   * Find if anything has been mapped to this key code and if so, execute it
   *
   * @param e keyEvent
   *
   * @attribute from lecture note
   */

  @Override
  public void keyReleased(KeyEvent e) {
    if (keyReleasedMap.containsKey(e.getKeyCode())) {
      keyReleasedMap.get(e.getKeyCode()).run();
    }
  }
}
