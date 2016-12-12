package cs3500.music.view;

/**
 * This is the interface for all of our views. Its only implementing method is
 * outputView, which requires its implementing classes to output whatever view
 * they choose.
 */
public interface IView {

  /**
   * Outputs whatever view is implementing this function, in this case either
   * the audible notes of the song being played for our MidiViewImpl, or the
   * visual appearance of our song in our gui and console views
   */
  void outputView();
}
