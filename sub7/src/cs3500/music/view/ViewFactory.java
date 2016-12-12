package cs3500.music.view;

import cs3500.music.model.GenericMusicEditor;

/**
 * The factory class for our views. ViewFactory will simply create a view given
 * a string and a MusicEditor for the view to use.
 */
public class ViewFactory {

  /**
   * createView will take in a string and an editor and return a new view of the
   * corresponding type. If given a bad string, it will throw an
   * IllegalArgumentError
   *
   * @param s the string representing our desired view
   * @param e the editor for the view to use
   * @return IView the view that is returned
   */
  public IView createView(String s, GenericMusicEditor e) {
    if (s.equals("console")) {
      return new TextualView(e);
    } else if (s.equals("midi")) {
      return new MidiViewImpl(e);
    } else if (s.equals("gui")) {
      return new GuiViewFrame(e);
    } else if (s.equals("composite")) {
      return new CompositeView(e);
    } else {
      throw new IllegalArgumentException("Bad input");
    }
  }
}
