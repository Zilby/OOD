package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is our handler for keyboard inputs it overrides the default
 * methods so that it can add them to its hashmaps of runnable functions.
 */
public class KeyboardHandler implements KeyListener {
  Map<Integer, Runnable> typed = new HashMap<Integer, Runnable>();
  Map<Integer, Runnable> pressed = new HashMap<Integer, Runnable>();
  Map<Integer, Runnable> released = new HashMap<Integer, Runnable>();

  @Override
  public void keyTyped(KeyEvent e) {
    Runnable r = typed.get(e.getKeyCode());
    if (r != null) {
      r.run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    Runnable r = pressed.get(e.getKeyCode());
    if (r != null) {
      r.run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    Runnable r = released.get(e.getKeyCode());
    if (r != null) {
      r.run();
    }
  }

  /**
   * Adds a Runnable to our typed hashmap
   *
   * @param key the key for our Runnable
   * @param r our Runnable
   */
  public void addTypedRunnable(Integer key, Runnable r) {
    typed.put(key, r);
  }

  /**
   * Adds a Runnable to our pressed hashmap
   *
   * @param key the key for our Runnable
   * @param r our Runnable
   */
  public void addPressedRunnable(Integer key, Runnable r) {
    pressed.put(key, r);
  }

  /**
   * Adds a Runnable to our released hashmap
   *
   * @param key the key for our Runnable
   * @param r our Runnable
   */
  public void addReleasedRunnable(Integer key, Runnable r) {
    released.put(key, r);
  }
}
