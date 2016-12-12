package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.model.GenericMusicEditor;

public interface GuiView extends IView, ActionListener {

  /**
   * adds a mouse listener to our main panel
   *
   * @param m the mouse listener to be added
   */
  void addMouseListener(MouseListener m);

  /**
   * adds a key listener to our content pane
   *
   * @param k the key listener to be added
   */
  void addKeyListener(KeyListener k);

  /**
   * Returns the vertical scroll bar of our scroll pane
   *
   * @return the vertical scroll bar
   */
  JScrollBar getVerticalScroll();

  /**
   * Returns the horizontal scroll bar of our scroll pane
   *
   * @return the horizontal scroll bar
   */
  JScrollBar getHorizontalScroll();

  /**
   * Updates our window to changes of our editor's song
   */
  void updateWindow();

  /**
   * removes a given note from our editor using the mouse's x and y
   * coordinates to find it. If given invalid coordinates nothing will be
   * removed.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  void removeNote(int x, int y);

  /**
   * adds a note to our song using input dialogs. If given bad input it will
   * catch the error and display a message indicating the offending input
   */
  void addNote();
}
