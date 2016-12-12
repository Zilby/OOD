package cs3500.music.view;

import java.util.ArrayList;

import cs3500.music.model.GenericMusicEditor;
import cs3500.music.model.Song;

/**
 * The implementation of our text/console view. The output view for this
 * function will print our song as a console output.
 */
public class TextualView implements IView {
  private GenericMusicEditor editor;

  public TextualView(GenericMusicEditor e) {
    editor = e;
  }

  @Override
  public String toString() {
    Song s = editor.getSong();
    if (s.length() == 0) {
      return "Nothing here yet, let's make some music!";
    } else {
      String output = "    ";
      int padding = ((Integer) s.getLast().getBeat()).toString().length() + 1;
      for (int i = 1; i < padding - 1; i++) {
        output += " ";
      }
      int beat = s.getFirst().getBeat();
      ArrayList<String> pitches = s.getPitches();
      for (String st : pitches) {
        int i = st.length();
        output += st;
        while (i < 5) {
          output += " ";
          i++;
        }
      }
      int index = 0;
      while (beat <= s.getLast().getBeat()) {
        output += "\n" + String.format("%" + padding + "d", beat);
        for (String st : pitches) {
          if (!(index == s.length()) && s.getNote(index).toString().equals
                  (st) && s.getNote(index).getBeat() == beat) {
            if (s.getNote(index).held()) {
              output += "   | ";
            } else {
              output += "   X ";
            }
            index++;
          } else {
            output += "     ";
          }
        }
        beat++;
      }
      return output;
    }
  }

  /**
   * displays the song in a console view. The view will contain only the pitches
   * and beats that are used in the song, and represent the notes by an 'X' for
   * a newly struck note and an '|' for a sustained note.
   */
  public void outputView() {
    System.out.println(this.toString());
  }
}
