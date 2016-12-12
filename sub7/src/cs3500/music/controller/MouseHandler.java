package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is our handler for keyboard inputs it overrides the default
 * methods so that it can add them to its hashmap of MouseRunnable functions.
 */
public class MouseHandler implements MouseListener {
  Map<Integer, MouseRunnable> mevent = new HashMap<Integer, MouseRunnable>();

  @Override
  public void mouseClicked(MouseEvent e) {
    MouseRunnable r = mevent.get(MouseEvent.MOUSE_CLICKED);
    if (r != null) {
      r.run(e);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    MouseRunnable r = mevent.get(MouseEvent.MOUSE_PRESSED);
    if (r != null) {
      r.run(e);
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    MouseRunnable r = mevent.get(MouseEvent.MOUSE_RELEASED);
    if (r != null) {
      r.run(e);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  /**
   * Adds a MouseRunnable to our mevent hashmap
   *
   * @param key the key for our Runnable
   * @param r our MouseRunnable
   */
  public void addMouseRunnable(Integer key, MouseRunnable r) {
    mevent.put(key, r);
  }
}
