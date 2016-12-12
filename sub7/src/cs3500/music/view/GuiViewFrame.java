package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.GenericMusicEditor;
import cs3500.music.model.Note;
import cs3500.music.model.Song;

/**
 * The frame for our gui view. This controls the size of our window, its
 * scrolling and displaying the actual view.
 */
public class GuiViewFrame extends JFrame implements GuiView,
        ActionListener {

  private ConcreteGuiViewPanel displayPanel;
  private JScrollPane scrPane;
  private GenericMusicEditor editor;
  private boolean moving;

  public GuiViewFrame(GenericMusicEditor e) {
    this.editor = e;
    this.moving = false;
    this.displayPanel = new ConcreteGuiViewPanel(editor);
    displayPanel.revalidate();
    this.scrPane = new JScrollPane(this.displayPanel);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.scrPane.setFocusable(true);
    this.getContentPane().add(this.scrPane);
    this.getContentPane().setFocusable(true);
    this.pack();
  }

  /**
   * the preferred size of our panel. This is determined by the number of
   * pitches and beats in the song, but will cap at 1600x900 and instead allow
   * scrolling at max size.
   *
   * @return Dimension that we desire
   */
  @Override
  public Dimension getPreferredSize() {
    if (editor.getSong().length() == 0) {
      return new Dimension(100, 20);
    } else {
      int height = (editor.getSong().getPitches().size() + 4) * 20;
      int width = editor.getSong().getLast().getBeat();
      while (width % 4 != 0) {
        width++;
      }
      width = (width + 4) * 20;
      if (height > 900) {
        height = 900;
      }
      if (width > 1600) {
        width = 1600;
      }
      return new Dimension(width, height);
    }
  }

  /**
   * renders our gui view by making it visible
   */
  public void outputView() {
    this.setVisible(true);
  }

  /**
   * adds a mouse listener to our main panel
   *
   * @param m the mouse listener to be added
   */
  public void addMouseListener(MouseListener m) {
    this.displayPanel.addMouseListener(m);
  }

  /**
   * adds a key listener to our content pane
   *
   * @param k the key listener to be added
   */
  public void addKeyListener(KeyListener k) {
    this.getContentPane().addKeyListener(k);
    System.out.println(this.getListeners(KeyboardHandler.class));
  }

  /**
   * Returns the vertical scroll bar of our scroll pane
   *
   * @return the vertical scroll bar
   */
  public JScrollBar getVerticalScroll() {
    return this.scrPane.getVerticalScrollBar();
  }

  /**
   * Returns the horizontal scroll bar of our scroll pane
   *
   * @return the horizontal scroll bar
   */
  public JScrollBar getHorizontalScroll() {
    return this.scrPane.getHorizontalScrollBar();
  }

  /**
   * Moves either the measure line or the view to the right on each action
   * event
   *
   * @param e the action event being responded to
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (moving) {
      this.scrPane.getHorizontalScrollBar().setValue(this.scrPane
              .getHorizontalScrollBar().getValue() + 20);
      displayPanel.onAction();
    } else if (!(displayPanel.onAction() < this.getPreferredSize().width -
            20)) {
      moving = true;
    }
    this.revalidate();
  }

  /**
   * Updates our window to changes of our editor's song
   */
  public void updateWindow() {
    this.displayPanel.onUpdate(editor.getSong());
    this.setSize(this.getPreferredSize());
    this.revalidate();
    displayPanel.revalidate();
    this.getContentPane().revalidate();
  }

  /**
   * removes a given note from our editor using the mouse's x and y
   * coordinates to find it. If given invalid coordinates nothing will be
   * removed.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public void removeNote(int x, int y) {
    Song s = editor.getSong();
    int beat = s.getFirst().getBeat();
    int start = 60;
    int end = x;
    while (start < end) {
      start += 20;
      beat++;
    }
    start = 50;
    end = y;
    int pitch = 0;
    while (start < end) {
      start += 20;
      pitch++;
    }
    int index = 0;
    while (s.getNote(index).getBeat() < beat) {
      index++;
    }
    if (s.getNote(index).getBeat() != beat) {
      return;
    }
    String pstring = s.getPitches().get(pitch);
    while (!s.getNote(index).toString().equals(pstring)) {
      index++;
      if (index >= s.length()) {
        return;
      }
    }
    Note n = s.getNote(index);
    if (n.toString().equals(pstring)) {
      editor.removeNote(n.getPitch(), n.getOctave(), n.getBeat(), n
              .getInstrument(), n.getVolume());
      this.updateWindow();
    }
  }

  /**
   * adds a note to our song using input dialogs. If given bad input it will
   * catch the error and display a message indicating the offending input
   */
  public void addNote() {
    String input = JOptionPane.showInputDialog(this, "Input Midi Value");
    int octave = 0;
    int pitch = 0;
    int beat = 0;
    int instrument = 0;
    int volume = 0;
    int duration = 0;
    try {
      int midi = Integer.parseInt(input);
      octave = midi / 12;
      pitch = midi % 12;
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid midi value");
      return;
    }
    input = JOptionPane.showInputDialog(this, "Input Beat");
    try {
      beat = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid beat value");
      return;
    }
    input = JOptionPane.showInputDialog(this, "Input Duration");
    try {
      duration = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid duration value");
      return;
    }
    input = JOptionPane.showInputDialog(this, "Input Instrument");
    try {
      instrument = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid instrument value");
      return;
    }
    input = JOptionPane.showInputDialog(this, "Input Volume");
    try {
      volume = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid volume value");
      return;
    }
    try {
      this.editor.addNote(Note.pitch.values()[pitch], octave,
              beat, duration, instrument, volume);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Invalid Note Entered");
      return;
    }
    this.updateWindow();
  }
}