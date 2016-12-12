package cs3500.music.view;

import javax.swing.*;

import cs3500.music.model.GenericMusicEditor;

/**
 * The implementation of our composite view. It will instantiate its Gui and
 * midi views with the given GenericMusicEditor.
 */
public class CompositeView implements IView {
  private GuiView gui;
  private MidiViewImpl midi;
  private Timer time;

  public CompositeView(GenericMusicEditor e) {
    this.gui = new GuiViewFrame(e);
    this.midi = new MidiViewImpl(e);
    this.time = midi.getTimer();
    time.setInitialDelay(500);
    time.addActionListener(gui);

  }

  /**
   * Returns the GuiView of this composite view
   *
   * @return our gui view
   */
  public GuiView getGui() {
    return gui;
  }

  /**
   * Pauses/resumes playback of the song.
   */
  public void pause() {
    if (time.isRunning()) {
      time.stop();
    } else {
      time.start();
    }
  }

  /**
   * renders our composite view by starting the timer and rendering our gui view
   */
  public void outputView() {
    gui.outputView();
    time.start();
    while (time.isRunning()) {}
  }
}
